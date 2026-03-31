/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import models.Producto;

/**
 *
 * @author Usuario
 */
public interface IProductoDAO {
    
    void crearProducto(Producto producto);
    
    Producto buscarPorId(Long id);
    
    void actualizarProducto(Producto producto);
    
    void eliminarProducto(Long idProducto);
    
    List<Producto> obtenerTodosProductos();
    
    List<Producto> listaTop(int limite);

    List<Producto> listarPaginado(int pagina, int tamañoPag);

    long contarProductos();
    
}
