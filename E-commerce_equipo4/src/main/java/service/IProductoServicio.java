/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dto.ProductoDTO;
import java.util.List;
import models.Producto;

/**
 *
 * @author Usuario
 */
public interface IProductoServicio {
    
    void crearProducto(Producto producto);
    
    void actualizarProducto(Producto producto, Long idAdmin);
    
    void eliminarProducto(Long idProducto, Long idAdmin);
    
    ProductoDTO obtenerProducto(Long idProducto);
    
    List<ProductoDTO> obtenerTodosProductos(Long idAdmin);
    
    List<ProductoDTO> listarProductosPublicos();
    
}
