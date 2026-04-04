/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Abraham Coronel
 */
public class ItemCarritoDTO {

    private Long idItemCarrito;
    private Integer cantidad;
    private ProductoDTO producto;
    private double subtotal;

    public ItemCarritoDTO() {
    }

    public ItemCarritoDTO(Long idItemCarrito, Integer cantidad, ProductoDTO producto, double subtotal) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
        this.producto = producto;
        this.subtotal = subtotal;
    }

    public Long getIdItemCarrito() {
        return idItemCarrito;
    }

    public void setIdItemCarrito(Long idItemCarrito) {
        this.idItemCarrito = idItemCarrito;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
