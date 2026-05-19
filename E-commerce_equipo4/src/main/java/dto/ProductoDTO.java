/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.List;
import models.TamanoPeluche;

/**
 *
 * @author Usuario
 */
public class ProductoDTO {

    public Long id;
    public String nombre;
    public double precio;
    public String descripcion;
    public TamanoPeluche tamano;
    public String rutaImagen;
    public String categoria;
    public Integer stock;
    public List<ReseniaDTO> resenias;

    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, double precio, String descripcion, TamanoPeluche tamano, String rutaImagen, String categoria, Integer stock, List<ReseniaDTO> resenias) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tamano = tamano;
        this.rutaImagen = rutaImagen;
        this.categoria = categoria;
        this.stock = stock;
        this.resenias = resenias;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public TamanoPeluche getTamano() {
        return tamano;
    }

    public void setTamano(TamanoPeluche tamano) {
        this.tamano = tamano;
    }

    public List<ReseniaDTO> getResenias() {
        return resenias;
    }

    public void setResenias(List<ReseniaDTO> resenias) {
        this.resenias = resenias;
    }
    
}
