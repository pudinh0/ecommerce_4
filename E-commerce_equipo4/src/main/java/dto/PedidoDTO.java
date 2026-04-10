/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class PedidoDTO {

    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private String estado;
    private List<DetallePedidoDTO> detalles;
    private UsuarioDTO usuario;

    public PedidoDTO() {
    }

    public PedidoDTO(Long id, LocalDateTime fecha, Double total, String estado, List<DetallePedidoDTO> detalles, UsuarioDTO usuario) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.detalles = detalles;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetallePedidoDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoDTO> detalles) {
        this.detalles = detalles;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
