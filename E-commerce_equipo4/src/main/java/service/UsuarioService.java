package service;

import dao.IUsuarioDAO;
import dao.UsuarioDAO;
import dto.UsuarioDTO;
import java.util.List;
import java.util.stream.Collectors;
import mapper.UsuarioMapper;
import models.TipoUsuarioEnum;
import models.Usuario;

public class UsuarioService implements IUsuarioService {

    private final IUsuarioDAO usuarioDAO = new UsuarioDAO();
    private final UsuarioMapper usuarioMapper = new UsuarioMapper();

    @Override
    public void registrar(String nombres,
            String primerApellido,
            String segundoApellido,
            String correo,
            String contrasenia) {

        validarNombre(nombres);
        validarApellido(primerApellido);
        validarCorreo(correo);
        validarContrasenia(contrasenia);

        if (usuarioDAO.buscarPorCorreo(correo) != null) {
            throw new IllegalArgumentException("El correo ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setPrimerApellido(primerApellido);
        usuario.setSegundoApellido(segundoApellido);
        usuario.setCorreo(correo.trim().toLowerCase());
        usuario.setContrasenia(contrasenia);

        usuario.setTipoUsuario(TipoUsuarioEnum.CLIENTE);

        usuarioDAO.guardar(usuario);
    }

    @Override
    public UsuarioDTO autenticar(String correo, String contrasenia) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        Usuario usuario = usuarioDAO.buscarPorCorreoYContrasenia(
                correo.trim().toLowerCase(),
                contrasenia
        );

        if (usuario == null) {
            throw new IllegalArgumentException("Correo o contraseña incorrectos.");
        }

        return usuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }

        Usuario usuario = usuarioDAO.buscarPorId(id);
        return usuario != null ? usuarioMapper.toDTO(usuario) : null;
    }

    @Override
    public UsuarioDTO buscarPorCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        Usuario usuario = usuarioDAO.buscarPorCorreo(correo.trim().toLowerCase());
        return usuario != null ? usuarioMapper.toDTO(usuario) : null;
    }

    @Override
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioDAO.listarTodos();

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo.");
        }

        if (usuario.getIdUsuario() == null || usuario.getIdUsuario() <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }

        validarNombre(usuario.getNombres());
        validarCorreo(usuario.getCorreo());

        usuarioDAO.actualizar(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El id del usuario no es válido.");
        }
        usuarioDAO.eliminar(id);
    }

    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        String nombreLimpio = nombre.trim();

        if (nombreLimpio.length() < 2 || nombreLimpio.length() > 100) {
            throw new IllegalArgumentException("El nombre debe tener entre 2 y 100 caracteres.");
        }
    }

    private void validarApellido(String primerApellido) {
        if (primerApellido == null || primerApellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El primer apellido es obligatorio.");
        }

        String apellidoLimpio = primerApellido.trim();

        if (apellidoLimpio.length() < 2 || apellidoLimpio.length() > 100) {
            throw new IllegalArgumentException("El apellido debe tener entre 2 y 100 caracteres.");
        }
    }

    private void validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo es obligatorio.");
        }

        String correoLimpio = correo.trim();

        if (correoLimpio.length() < 5 || correoLimpio.length() > 100) {
            throw new IllegalArgumentException("El correo debe tener entre 5 y 100 caracteres.");
        }

        if (!correoLimpio.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El formato del correo no es válido.");
        }
    }

    private void validarContrasenia(String contrasenia) {
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria.");
        }

        if (contrasenia.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (contrasenia.length() > 100) {
            throw new IllegalArgumentException("La contraseña no puede exceder 100 caracteres.");
        }
    }

    private void validarTelefono(String telefono) {
        String regex = "^[7-9][0-9]{9}$";
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio.");
        }
        if (!telefono.matches(regex)) {
            throw new IllegalArgumentException("El telefono solo debe tener 10 digitos.");
        }
    }

    @Override
    public List<UsuarioDTO> listaTop(int limite) {
        if (limite < 0) {
            limite = 10;
        }
        List<Usuario> usuarios = usuarioDAO.listaTop(limite);

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> listarPaginado(int pagina, int tamañoPag) {
        if (pagina < 0) {
            pagina = 1;
        }
        if (tamañoPag < 0) {
            tamañoPag = 10;
        }
        List<Usuario> usuarios = usuarioDAO.listarPaginado(pagina, tamañoPag);

        return usuarios.stream()
                .map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public long contarUsuarios() {
        return usuarioDAO.contarUsuarios();
    }
}
