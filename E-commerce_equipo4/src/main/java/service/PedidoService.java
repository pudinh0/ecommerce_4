/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CarritoDAO;
import dao.ICarritoDAO;
import dao.IPedidoDAO;
import dao.IProductoDAO;
import dao.PedidoDAO;
import dao.ProductoDAO;
import dto.PedidoDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mapper.PedidoMapper;
import models.Carrito;
import models.DetallePedido;
import models.EstadoPedidoEnum;
import models.ItemCarrito;
import models.Pedido;
import models.Producto;

/**
 *
 * @author Abraham Coronel
 */
public class PedidoService implements IPedidoService {

    private final IPedidoDAO pedidoDAO = new PedidoDAO();
    private final ICarritoDAO carritoDAO = new CarritoDAO();
    private final IProductoDAO productoDAO = new ProductoDAO(); // Lo usaremos para descontar stock
    private final PedidoMapper pedidoMapper = new PedidoMapper();

    @Override
    public void procesarCompra(String correoUsuario) {
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo del usuario es obligatorio.");
        }

        Carrito carrito = carritoDAO.buscarPorCorreoUsuario(correoUsuario);

        if (carrito == null || carrito.getItemsCarrito() == null || carrito.getItemsCarrito().isEmpty()) {
            throw new IllegalArgumentException("No puedes procesar una compra con el carrito vacío.");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(carrito.getUsuario());
        pedido.setFechaCompra(LocalDateTime.now());
        pedido.setEstado(models.EstadoPedidoEnum.PENDIENTE);

        List<DetallePedido> detalles = new ArrayList<>();

        for (ItemCarrito item : carrito.getItemsCarrito()) {
            Producto producto = item.getProducto();

            if (producto.getStock() < item.getCantidad()) {
                throw new IllegalArgumentException("No hay stock suficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - item.getCantidad());
            productoDAO.actualizarProducto(producto);

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());

            detalle.setPrecioVenta(producto.getPrecio());

            detalles.add(detalle);
        }

        pedido.setDetalles(detalles);

        pedido.setTotal(pedido.getTotal());

        pedidoDAO.guardar(pedido);

        carritoDAO.vaciarCarrito(carrito.getIdCarrito());
    }

    @Override
    public PedidoDTO obtenerPedidoPorId(Long idPedido) {
        if (idPedido == null || idPedido <= 0) {
            throw new IllegalArgumentException("ID de pedido inválido.");
        }

        Pedido pedido = pedidoDAO.buscarPorId(idPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no existe.");
        }

        return pedidoMapper.toDTO(pedido);
    }

    @Override
    public List<PedidoDTO> obtenerHistorialUsuario(String correoUsuario) {
        if (correoUsuario == null || correoUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        List<Pedido> pedidosBD = pedidoDAO.buscarPorCorreoUsuario(correoUsuario);

        return pedidosBD.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<PedidoDTO> obtenerTodosLosPedidos() {
        List<Pedido> todosLosPedidos = pedidoDAO.obtenerTodos();
        
        return todosLosPedidos.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarEstadoPedido(Long idPedido, String nuevoEstado, Long idAdmin) {
        if (idAdmin == null) {
            throw new IllegalArgumentException("Acceso denegado: Se requiere ser administrador.");
        }
        if (idPedido == null || idPedido <= 0) {
            throw new IllegalArgumentException("ID de pedido inválido.");
        }
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo estado no puede estar vacío.");
        }

        Pedido pedido = pedidoDAO.buscarPorId(idPedido);
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido solicitado no existe.");
        }

        try {
            EstadoPedidoEnum estadoEnum = EstadoPedidoEnum.valueOf(nuevoEstado.toUpperCase());
            pedido.setEstado(estadoEnum);
            pedidoDAO.actualizar(pedido);
            
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("El estado proporcionado no es válido.");
        }
    }

}
