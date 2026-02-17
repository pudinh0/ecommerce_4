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
@Table(name = "categorias")
public class Categoria {
    @Id
    @Column(name = "idCategoria")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idCategoria;
    
    @Column(name = "nombre", length = 100)
    private String nombre;
    
    @Column (name = "descripcion", length = 255)
    private String descripcion;
    
    @OneToMany(mappedBy = "categoria")
    private List<ProductoCategoria> productosCategorias;

    public Categoria() {
    }

    public Categoria(Long idCategoria, String nombre, String descripcion, List<ProductoCategoria> productosCategorias) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productosCategorias = productosCategorias;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoCategoria> getProductosCategorias() {
        return productosCategorias;
    }

    public void setProductosCategorias(List<ProductoCategoria> productosCategorias) {
        this.productosCategorias = productosCategorias;
    }
    
}