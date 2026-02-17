package models;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


@Entity
@Table(name="productos")
public class Producto {

    @Id
    @Column(name="idProducto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    
    @Column(name="nombre", nullable = false, length = 150)
    private String nombre;
    
    @Column(name="precio", nullable = false)
    private Double precio;
    
    @Column(name="descripcion", nullable = false, length = 500)
    private String descripcion;
    
    @Column(name="stock", nullable = false)
    private Integer stock;
    
    @Column(name = "rutaImagen", nullable = false, length = 260)
    private String rutaImagen;
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ProductoCategoria> productosCategorias;
    
    @OneToMany(mappedBy = "producto")
    private List<Resenia> resenias;

    public Producto() {
    }

    public Producto(Long idProducto, String nombre, Double precio, String descripcion, Integer stock, String rutaImagen, List<ProductoCategoria> productosCategorias, List<Resenia> resenias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.rutaImagen = rutaImagen;
        this.productosCategorias = productosCategorias;
        this.resenias = resenias;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public List<ProductoCategoria> getProductosCategorias() {
        return productosCategorias;
    }

    public void setProductosCategorias(List<ProductoCategoria> productosCategorias) {
        this.productosCategorias = productosCategorias;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }
    
}
