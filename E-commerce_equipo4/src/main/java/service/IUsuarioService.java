package service;

import java.util.List;
import models.Usuario;

public interface IUsuarioService {
    
     void registrar(String nombres,
                          String primerApellido,
                          String segundoApellido,
                          String correo,
                          String contrasenia);

    Usuario autenticar(String correo, String contrasenia);

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    List<Usuario> listarTodos();

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);
    
    List<Usuario> listaTop(int limite);
    
    List<Usuario> listarPaginado(int pagina, int tamañoPag);
    
    long contarUsuarios();
}