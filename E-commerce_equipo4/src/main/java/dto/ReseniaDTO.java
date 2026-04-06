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
    public Long idProducto;
    public UsuarioDTO usuario;

    public ReseniaDTO() {
    }

    public ReseniaDTO(String comentario, Integer calificacion, LocalDate fecha, Long id, Long idProducto, UsuarioDTO usuario) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.id = id;
        this.idProducto = idProducto;
        this.usuario = usuario;
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

}
