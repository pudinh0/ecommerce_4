/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Abraham Coronel
 */
public class CarritoDTO {

    private Long idCarrito;
    private LocalDate fechaCreacion;
    private List<ItemCarritoDTO> itemsCarrito;
    private double total;

    public CarritoDTO() {
    }

    public CarritoDTO(Long idCarrito, LocalDate fechaCreacion, List<ItemCarritoDTO> itemsCarrito, double total) {
        this.idCarrito = idCarrito;
        this.fechaCreacion = fechaCreacion;
        this.itemsCarrito = itemsCarrito;
        this.total = total;
    }

    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<ItemCarritoDTO> getItemsCarrito() {
        return itemsCarrito;
    }

    public void setItemsCarrito(List<ItemCarritoDTO> itemsCarrito) {
        this.itemsCarrito = itemsCarrito;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
