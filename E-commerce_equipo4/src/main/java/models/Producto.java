package models;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020

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
    
    @Column(name = "tamano", nullable = false,  length = 150)
    private TamanoPeluche tamano;
    
    @Column(name = "categoria", nullable = false,  length = 150)
    private String categoria;
    
    @Column(name="precio", nullable = false)
    private Double precio;
    
    @Column(name="descripcion", nullable = false, length = 500)
    private String descripcion;
    
    @Column(name="stock", nullable = false)
    private Integer stock;
    
    @Column(name = "rutaImagen", nullable = false, length = 260)
    private String rutaImagen;

    @Column(name = "activo")
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "producto")
    private List<Resenia> resenias;

    public Producto() {
    }

    public Producto(Long idProducto, String nombre, TamanoPeluche tamano, String categoria, Double precio, String descripcion, Integer stock, String rutaImagen, List<Resenia> resenias) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.tamano = tamano;
        this.categoria = categoria;
        this.precio = precio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.rutaImagen = rutaImagen;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public TamanoPeluche getTamano() {
        return tamano;
    }

    public void setTamano(TamanoPeluche tamano) {
        this.tamano = tamano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }
    
}
