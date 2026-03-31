package dao;

import java.util.List;
import models.Usuario;

public interface IUsuarioDAO {

    void guardar(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario buscarPorCorreo(String correo);

    Usuario buscarPorCorreoYContrasenia(String correo, String contrasenia);

    Usuario buscarPorPseudonimo(String pseudonimo);

    List<Usuario> listarTodos();

    void actualizar(Usuario usuario);

    void eliminar(Long id);
    
    List<Usuario> listaTop(int limite);
    
    List<Usuario> listarPaginado(int pagina, int tamañoPag);
    
    long contarUsuarios();
}