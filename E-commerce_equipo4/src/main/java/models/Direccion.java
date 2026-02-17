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
import java.io.Serializable;


@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {

    @Id
    @Column(name = "idDireccion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    @Column(name = "calle", nullable = false, length = 150)
    private String calle;

    @Column(name = "ciudad", nullable = false, length = 100)
    private String ciudad;

    @Column(name = "codigoPostal", nullable = false, length = 10)
    private String codigoPostal;

    @Column(name = "numeroExterior", nullable = false, length = 10)
    private String numeroExterior;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    public Direccion() {
    }

    public Direccion(Long idDireccion, String calle, String ciudad, String codigoPostal, String numeroExterior, Usuario usuario) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.numeroExterior = numeroExterior;
        this.usuario = usuario;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
