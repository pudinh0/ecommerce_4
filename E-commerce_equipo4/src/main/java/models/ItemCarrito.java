package models;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "itemsCarrito")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItemCarrito")
    private Long idItemCarrito;

    @Column(name="cantidad", nullable = false)
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private Carrito carrito;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    public ItemCarrito() {
    }

    public ItemCarrito(Long idItemCarrito, Integer cantidad, Carrito carrito, Producto producto) {
        this.idItemCarrito = idItemCarrito;
        this.cantidad = cantidad;
        this.carrito = carrito;
        this.producto = producto;
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

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
