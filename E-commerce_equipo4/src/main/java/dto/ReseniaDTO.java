/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class ReseniaDTO {

    public String comentario;
    public Integer calificacion;
    public LocalDate fecha;
    public Long id;
    private UsuarioDTO usuario;
    private ProductoDTO producto;

    public ReseniaDTO() {
    }

    public ReseniaDTO(String comentario, Integer calificacion, LocalDate fecha, Long id, UsuarioDTO usuario, ProductoDTO producto) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.id = id;
        this.usuario = usuario;
        this.producto = producto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

}
