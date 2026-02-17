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
import java.time.LocalDate;

@Entity
@Table(name = "resenias")
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idResenia")
    private Long idResenia;

    @Column(name = "comentario", nullable = false, length = 500)
    private String comentario;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "fechaPublicacion", nullable = false)
    private LocalDate fechaPublicacion;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    public Resenia() {
    }

    public Resenia(Long idResenia, String comentario, Integer calificacion, LocalDate fechaPublicacion, Usuario usuario, Producto producto) {
        this.idResenia = idResenia;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fechaPublicacion = fechaPublicacion;
        this.usuario = usuario;
        this.producto = producto;
    }

    public Long getIdResenia() {
        return idResenia;
    }

    public void setIdResenia(Long idResenia) {
        this.idResenia = idResenia;
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

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
}
