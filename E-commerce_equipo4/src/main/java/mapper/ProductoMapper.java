/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import dto.ProductoDTO;
import models.Producto;

/**
 *
 * @author Abraham Coronel
 */
public class ProductoMapper {

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;
        
        Producto producto = new Producto();
        producto.setIdProducto(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setDescripcion(dto.getDescripcion());
        producto.setRutaImagen(dto.getRutaImagen());
        producto.setStock(dto.getStock()); 
        
        return producto;
    }

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setDescripcion(producto.getDescripcion());
        dto.setRutaImagen(producto.getRutaImagen());
        dto.setStock(producto.getStock());
        return dto;
    }
}
