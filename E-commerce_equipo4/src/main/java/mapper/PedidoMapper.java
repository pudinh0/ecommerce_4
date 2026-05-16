/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.DetallePedidoDTO;
import dto.PedidoDTO;
import java.util.ArrayList;
import java.util.List;
import models.DetallePedido;
import models.EstadoPedidoEnum;
import models.Pedido;
import models.Producto;

/**
 *
 * @author Abraham Coronel
 */
public class PedidoMapper {

    private final UsuarioMapper usuarioMapper = new UsuarioMapper();

    public PedidoDTO toDTO(Pedido pedido) {
        if (pedido == null) {
            return null;
        }

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getIdPedido());
        dto.setFecha(pedido.getFechaCompra());
        dto.setEstado(pedido.getEstado().name());
        dto.setTotal(pedido.getTotal());

        if (pedido.getUsuario() != null) {
            dto.setUsuario(usuarioMapper.toDTO(pedido.getUsuario()));
        }

        if (pedido.getDetalles() != null) {
            List<DetallePedidoDTO> detallesDTO = new ArrayList<>();
            for (DetallePedido detalle : pedido.getDetalles()) {
                detallesDTO.add(detalleToDTO(detalle));
            }
            dto.setDetalles(detallesDTO);
        }

        return dto;
    }

    private DetallePedidoDTO detalleToDTO(DetallePedido detalle) {
        if (detalle == null) {
            return null;
        }

        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detalle.getIdDetallePedido());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioVenta());

        if (detalle.getProducto() != null) {
            dto.setNombreProducto(detalle.getProducto().getNombre());
        }

        dto.setSubtotal(detalle.getSubtotal());

        return dto;
    }

    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        Pedido pedido = new Pedido();
        pedido.setIdPedido(dto.getId());
        pedido.setFechaCompra(dto.getFecha());

        if (dto.getEstado() != null) {
            pedido.setEstado(EstadoPedidoEnum.valueOf(dto.getEstado()));
        }

        pedido.setTotal(dto.getTotal());

        if (dto.getUsuario() != null) {
            pedido.setUsuario(usuarioMapper.toEntity(dto.getUsuario()));
        }

        if (dto.getDetalles() != null && !dto.getDetalles().isEmpty()) {
            List<DetallePedido> detalles = new ArrayList<>();

            for (DetallePedidoDTO detalleDTO : dto.getDetalles()) {
                DetallePedido detalle = detalleToEntity(detalleDTO);
                detalle.setPedido(pedido);           // ← Relación bidireccional importante
                detalles.add(detalle);
            }

            pedido.setDetalles(detalles);
        }

        return pedido;
    }

    private DetallePedido detalleToEntity(DetallePedidoDTO dto) {
        if (dto == null) {
            return null;
        }

        DetallePedido detalle = new DetallePedido();

        detalle.setIdDetallePedido(dto.getId());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioVenta(dto.getPrecioUnitario());

        if (dto.getNombreProducto() != null) {
            Producto producto = new Producto();
            producto.setNombre(dto.getNombreProducto());
            detalle.setProducto(producto);
        }

        return detalle;
    }

}
