/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.IProductoDAO;
import dao.ProductoDAO;
import dto.ProductoDTO;
import java.util.List;
import java.util.stream.Collectors;
import mapper.ProductoMapper;
import models.Producto;

/**
 *
 * @author Usuario
 */
public class ProductoService implements IProductoServicio {

    private final IProductoDAO productoDAO = new ProductoDAO();
    private final ProductoMapper productoMapper = new ProductoMapper();

    @Override
    public void crearProducto(ProductoDTO productoDTO) {

        if (productoDTO == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (productoDTO.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe de ser mayor a 0");
        }
        if (productoDTO.getStock() <= 0) {
            throw new IllegalArgumentException("El stock del producto debe de ser mayor a 0");
        }
        if (productoDTO.getDescripcion() == null || productoDTO.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (productoDTO.getRutaImagen() == null || productoDTO.getRutaImagen().trim().isEmpty()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }

        Producto producto = productoMapper.toEntity(productoDTO);
        
        productoDAO.crearProducto(producto);
    }

    @Override
    public void actualizarProducto(ProductoDTO productoDTO, Long idAdmin) {

        if (productoDTO == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (productoDTO.getId() == null || productoDTO.getId() <= 0) {
            throw new IllegalArgumentException("El ID del producto es inválido o no fue proporcionado");
        }
        if (productoDTO.getNombre() == null || productoDTO.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (productoDTO.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe de ser mayor a 0");
        }
        if (productoDTO.getStock() <= 0) {
            throw new IllegalArgumentException("El stock del producto debe de ser mayor a 0");
        }
        if (productoDTO.getDescripcion() == null || productoDTO.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (productoDTO.getRutaImagen() == null || productoDTO.getRutaImagen().trim().isEmpty()) {
            throw new IllegalArgumentException("La imagen es obligatoria");
        }

        Producto producto = productoMapper.toEntity(productoDTO);
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
        
        return productoMapper.toDTO(producto);
    }

    @Override
    public List<ProductoDTO> obtenerTodosProductos(Long idAdmin) {
        if (idAdmin == null) {
            throw new IllegalArgumentException("Usuario inválido");
        }
        
        List<Producto> productosBD = productoDAO.obtenerTodosProductos();
        
        return productosBD.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> listarProductosPublicos() {
        
        List<Producto> productosBD = productoDAO.obtenerTodosProductos();

        return productosBD.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

}
