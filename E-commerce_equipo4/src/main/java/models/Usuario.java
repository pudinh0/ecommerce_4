package models;

//@author SAUL ISAAC APODACA BALDENEGRO 00000252020
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @Column(name = "idUsuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    
    @Column(name = "primerApellido", nullable = false, length = 100)
    private String primerApellido;
    
    @Column(name="segundoApellido", nullable = true, length = 100)
    private String segundoApellido;
    
    @Column(name = "nombres", nullable = false, length = 150)
    private String nombres;
    
    @Column(name = "correo", nullable = false, length = 100, unique = true)
    private String correo;
    
    @Column(name="contrasenia", nullable = false, length = 60)
    private String contrasenia;
    
    @Column(name="telefono", nullable = false, length = 10)
    private String telefono;
    
    @Enumerated(EnumType.STRING)
    @Column(name="tipoUsuario", nullable = false)
    private TipoUsuarioEnum tipoUsuario;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carrito carrito;
    
    @OneToMany(mappedBy = "usuario")
    private List<Direccion> direcciones;

    @OneToMany(mappedBy = "usuario")
    private List<Resenia> resenias;
    
    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;
    
    public Usuario() {
    }

    public Usuario(Long idUsuario, String primerApellido, String segundoApellido, String nombres, String correo, String contrasenia, String telefono, TipoUsuarioEnum tipoUsuario, Carrito carrito, List<Direccion> direcciones, List<Resenia> resenias, List<Pedido> pedidos) {
        this.idUsuario = idUsuario;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nombres = nombres;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.tipoUsuario = tipoUsuario;
        this.carrito = carrito;
        this.direcciones = direcciones;
        this.resenias = resenias;
        this.pedidos = pedidos;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoUsuarioEnum getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<Resenia> getResenias() {
        return resenias;
    }

    public void setResenias(List<Resenia> resenias) {
        this.resenias = resenias;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
   
}
