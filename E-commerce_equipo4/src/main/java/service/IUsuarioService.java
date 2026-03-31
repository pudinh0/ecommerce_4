package service;

import dto.UsuarioDTO;
import java.util.List;
import models.Usuario;

public interface IUsuarioService {
    
     void registrar(String nombres,
                          String primerApellido,
                          String segundoApellido,
                          String correo,
                          String contrasenia);

    UsuarioDTO autenticar(String correo, String contrasenia);

    UsuarioDTO buscarPorId(Long id);

    UsuarioDTO buscarPorCorreo(String correo);

    List<UsuarioDTO> listarTodos();

    void actualizarUsuario(Usuario usuario);

    void eliminarUsuario(Long id);
    
    List<UsuarioDTO> listaTop(int limite);
    
    List<UsuarioDTO> listarPaginado(int pagina, int tamañoPag);
    
    long contarUsuarios();
}