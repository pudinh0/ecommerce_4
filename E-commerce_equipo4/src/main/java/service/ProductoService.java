/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.IProductoDAO;
import dao.ProductoDAO;
import dto.ProductoDTO;
import java.util.List;
import models.Producto;

/**
 *
 * @author Usuario
 */
public class ProductoService implements IProductoServicio{
    
    private final IProductoDAO productoDAO = new ProductoDAO();

    @Override
    public void crearProducto(Producto producto) {
        
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe de ser mayor a 0");
        }
        if (producto.getStock() <= 0) {
            throw new IllegalArgumentException("El sstock del producto debe de sser mayor a 0");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }

        if (producto.getRutaImagen() == null ||producto.getRutaImagen().trim().isEmpty()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }
        productoDAO.crearProducto(producto);
    }

    @Override
    public void actualizarProducto(Producto producto, Long idAdmin) {
        
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe de ser mayor a 0");
        }
        if (producto.getStock() <= 0) {
            throw new IllegalArgumentException("El sstock del producto debe de sser mayor a 0");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }

        if (producto.getRutaImagen() == null || producto.getRutaImagen().trim().isEmpty()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }
        productoDAO.actualizarProducto(producto);
    }

    @Override
    public void eliminarProducto(Long idProducto, Long idAdmin) {
        if (idProducto == null) {
            throw new IllegalArgumentException("ID inválido");
        }
        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe");
        }
        productoDAO.eliminarProducto(idProducto);
    }

    @Override
    public ProductoDTO obtenerProducto(Long idProducto) {
        if (idProducto == null) {
            throw new IllegalArgumentException("ID inválido");
        }
        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe");
        }
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setPrecio(producto.getPrecio());
        productoDTO.setDescripcion(producto.getDescripcion());
        return productoDTO;
    }

    @Override
    public List<ProductoDTO> obtenerTodosProductos(Long idAdmin) {
        if (idAdmin == null) {
            throw new IllegalArgumentException("Usuario inválido");
        }
        List<ProductoDTO> productos = (List<ProductoDTO>) productoDAO.obtenerTodosProductos().stream().map((p) -> {
            ProductoDTO productoDTO = new ProductoDTO();
            productoDTO.setNombre(p.getNombre());
            productoDTO.setPrecio(p.getPrecio());
            productoDTO.setDescripcion(p.getDescripcion());
            return productoDTO;
        });
        return productos;
    }
    
}
