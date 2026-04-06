/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import dto.ProductoDTO;
import java.util.List;


/**
 *
 * @author Usuario
 */
public interface IProductoServicio {
    
    void crearProducto(ProductoDTO producto);
    
    void actualizarProducto(ProductoDTO producto, Long idAdmin);
    
    void eliminarProducto(Long idProducto, Long idAdmin);
    
    ProductoDTO obtenerProducto(Long idProducto);
    
    List<ProductoDTO> obtenerTodosProductos(Long idAdmin);
    
    List<ProductoDTO> listarProductosPublicos();
    
}
