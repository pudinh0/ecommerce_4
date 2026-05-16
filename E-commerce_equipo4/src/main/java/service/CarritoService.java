/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CarritoDAO;
import dao.ICarritoDAO;
import dao.IProductoDAO;
import dao.IUsuarioDAO;
import dao.ProductoDAO;
import dao.UsuarioDAO;
import dto.CarritoDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mapper.CarritoMapper;
import models.Carrito;
import models.ItemCarrito;
import models.Producto;
import models.Usuario;

/**
 *
 * @author Abraham Coronel
 */
public class CarritoService implements ICarritoService {

    private final ICarritoDAO carritoDAO = new CarritoDAO();
    private final IProductoDAO productoDAO = new ProductoDAO();
    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private final CarritoMapper carritoMapper = new CarritoMapper();

    @Override
    public CarritoDTO obtenerCarrito(String identificador) {
        if (identificador == null || identificador.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        Carrito carrito = null;
        Usuario usuario = null;

        try {
            Long id = Long.parseLong(identificador);
            carrito = carritoDAO.buscarPorIdUsuario(id);
            if (carrito == null) {
                usuario = usuarioDAO.buscarPorId(id);
            }
        } catch (NumberFormatException e) {
            carrito = carritoDAO.buscarPorCorreoUsuario(identificador);
            if (carrito == null) {
                usuario = usuarioDAO.buscarPorCorreo(identificador);
            }
        }

        if (carrito == null) {
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado.");
            }
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setItemsCarrito(new ArrayList<>());
            carritoDAO.guardar(carrito);
        }

        consolidarItemsDuplicados(carrito);
        return carritoMapper.toDTO(carrito);
    }

    @Override
    public void agregarProducto(String correoUsuario, Long idProducto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);

        if (carrito == null) {
            Usuario usuario = usuarioDAO.buscarPorCorreo(correoUsuario);
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setItemsCarrito(new ArrayList<>());
            carritoDAO.guardar(carrito);
        }

        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null || Boolean.FALSE.equals(producto.getActivo())) {
            throw new IllegalArgumentException("El producto no existe.");
        }

        consolidarItemsDuplicados(carrito);

        boolean encontrado = false;
        if (carrito.getItemsCarrito() != null) {
            for (ItemCarrito item : carrito.getItemsCarrito()) {
                if (item.getProducto().getIdProducto().equals(idProducto)) {
                    item.setCantidad(item.getCantidad() + cantidad);
                    encontrado = true;
                    break;
                }
            }
        }

        if (!encontrado) {
            ItemCarrito nuevoItem = new ItemCarrito();
            nuevoItem.setCarrito(carrito);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);

            if (carrito.getItemsCarrito() == null) {
                carrito.setItemsCarrito(new ArrayList<>());
            }
            carrito.getItemsCarrito().add(nuevoItem);
        }

        carritoDAO.actualizar(carrito);
    }

    @Override
    public void cambiarCantidadProducto(String correoUsuario, Long idProducto, int cambio) {
        if (idProducto == null || idProducto <= 0) {
            throw new IllegalArgumentException("ID de producto invalido.");
        }
        if (cambio == 0) {
            throw new IllegalArgumentException("El cambio de cantidad no puede ser 0.");
        }

        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);
        if (carrito == null || carrito.getItemsCarrito() == null) {
            throw new IllegalArgumentException("No se encontro el carrito.");
        }

        consolidarItemsDuplicados(carrito);

        ItemCarrito itemEncontrado = null;
        for (ItemCarrito item : carrito.getItemsCarrito()) {
            if (item.getProducto().getIdProducto().equals(idProducto)) {
                itemEncontrado = item;
                break;
            }
        }

        if (itemEncontrado == null) {
            if (cambio > 0) {
                agregarProducto(correoUsuario, idProducto, cambio);
                return;
            }
            throw new IllegalArgumentException("El producto no esta en el carrito.");
        }

        int nuevaCantidad = itemEncontrado.getCantidad() + cambio;
        if (nuevaCantidad <= 0) {
            carrito.getItemsCarrito().remove(itemEncontrado);
        } else {
            Producto producto = itemEncontrado.getProducto();
            if (producto.getStock() < nuevaCantidad) {
                throw new IllegalArgumentException("No hay stock suficiente para el producto: " + producto.getNombre());
            }
            itemEncontrado.setCantidad(nuevaCantidad);
        }

        carritoDAO.actualizar(carrito);
    }

    private void consolidarItemsDuplicados(Carrito carrito) {
        if (carrito == null || carrito.getItemsCarrito() == null || carrito.getItemsCarrito().isEmpty()) {
            return;
        }

        Map<Long, ItemCarrito> itemPorProducto = new HashMap<>();
        Iterator<ItemCarrito> iterator = carrito.getItemsCarrito().iterator();
        boolean huboDuplicados = false;

        while (iterator.hasNext()) {
            ItemCarrito item = iterator.next();
            Long idProducto = item.getProducto().getIdProducto();
            ItemCarrito existente = itemPorProducto.get(idProducto);

            if (existente == null) {
                itemPorProducto.put(idProducto, item);
            } else {
                existente.setCantidad(existente.getCantidad() + item.getCantidad());
                iterator.remove();
                huboDuplicados = true;
            }
        }

        if (huboDuplicados && carrito.getIdCarrito() != null) {
            carritoDAO.actualizar(carrito);
        }
    }

    @Override
    public void eliminarItem(String correoUsuario, Long idItem) {
        if (idItem == null || idItem <= 0) {
            throw new IllegalArgumentException("ID de item inválido.");
        }

        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);
        if (carrito == null) {
            throw new IllegalArgumentException("No se encontró el carrito.");
        }

        if (carrito.getItemsCarrito() != null) {
            carrito.getItemsCarrito().removeIf(item -> item.getIdItemCarrito().equals(idItem));

            carritoDAO.actualizar(carrito);
        }
    }

    @Override
    public void vaciarCarrito(String correoUsuario) {
        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);
        if (carrito != null && carrito.getIdCarrito() != null) {
            carritoDAO.vaciarCarrito(carrito.getIdCarrito());
        }
    }

}
