/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.CarritoDTO;
import dto.ItemCarritoDTO;
import dto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import models.Carrito;
import models.ItemCarrito;
import models.Producto;

/**
 *
 * @author Abraham Coronel
 */
public class CarritoMapper {

    public CarritoDTO toDTO(Carrito carrito) {
        if (carrito == null) {
            return null;
        }

        CarritoDTO dto = new CarritoDTO();
        dto.setIdCarrito(carrito.getIdCarrito());
        dto.setFechaCreacion(carrito.getFechaCreacion());

        dto.setTotal(carrito.getTotal());

        if (carrito.getItemsCarrito() != null) {
            List<ItemCarritoDTO> itemsDTO = new ArrayList<>();
            for (ItemCarrito item : carrito.getItemsCarrito()) {
                itemsDTO.add(itemToDTO(item));
            }
            dto.setItemsCarrito(itemsDTO);
        }
        return dto;
    }

    private ItemCarritoDTO itemToDTO(ItemCarrito item) {
        if (item == null) {
            return null;
        }

        ItemCarritoDTO dto = new ItemCarritoDTO();
        dto.setIdItemCarrito(item.getIdItemCarrito());
        dto.setCantidad(item.getCantidad());
        dto.setProducto(productoToDTO(item.getProducto()));

        dto.setSubtotal(item.getSubtotal());

        return dto;
    }

    private ProductoDTO productoToDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setRutaImagen(producto.getRutaImagen());
        return dto;
    }

}
