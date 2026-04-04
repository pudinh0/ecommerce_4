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
    public CarritoDTO obtenerCarrito(String correoUsuario) {
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);

        if (carrito == null) {
            Usuario usuario = usuarioDAO.buscarPorCorreo(correoUsuario);
            if (usuario == null) {
                throw new IllegalArgumentException("Usuario no encontrado.");
            }
            carrito = new Carrito();
            carrito.setUsuario(usuario);
            carrito.setItemsCarrito(new ArrayList<>());
            carritoDAO.guardar(carrito);
        }

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
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe.");
        }

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
