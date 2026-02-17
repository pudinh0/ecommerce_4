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
@Table(name = "detallesPedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDetallePedido")
    private Long idDetallePedido;
    
    @Column(name="cantidad", nullable = false)
    private Integer cantidad;
    
    @Column(name = "precioVenta", nullable = false)
    private Double precioVenta;
    
    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    public DetallePedido() {
    }

    public DetallePedido(Long idDetallePedido, Integer cantidad, Double precioVenta, Pedido pedido, Producto producto) {
        this.idDetallePedido = idDetallePedido;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.pedido = pedido;
        this.producto = producto;
    }

    public Long getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(Long idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
