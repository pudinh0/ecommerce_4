package apiRest;

import dto.UsuarioDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import service.IUsuarioService;
import service.UsuarioService;
import util.JSONMapper;

@WebServlet(name = "PerfilServlet", urlPatterns = {"/api/usuarios/*"})
public class PerfilServlet extends HttpServlet {

    private final IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo != null && pathInfo.equals("/perfil")) {
                String correoUsuario = obtenerCorreoUsuario(request);

                if (correoUsuario == null) {
                    enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesion para ver tu perfil.");
                    return;
                }

                UsuarioDTO perfil = usuarioService.buscarPorCorreo(correoUsuario);

                if (perfil != null) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    JSONMapper.mapper.writeValue(response.getWriter(), perfil);
                } else {
                    enviarError(response, HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado en el sistema.");
                }
            } else {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Ruta de usuario no valida.");
            }
        } catch (Exception e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el perfil: " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String correoUsuario = obtenerCorreoUsuario(request);

            if (correoUsuario == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesion para editar tu perfil.");
                return;
            }

            UsuarioDTO perfilActualizado = JSONMapper.mapper.readValue(request.getInputStream(), UsuarioDTO.class);

            if (!correoUsuario.equals(perfilActualizado.getCorreo())) {
                enviarError(response, HttpServletResponse.SC_FORBIDDEN, "Accion denegada. Solo puedes editar tu propio perfil.");
                return;
            }

            usuarioService.actualizarPerfil(perfilActualizado);

            response.setStatus(HttpServletResponse.SC_OK);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Perfil actualizado con exito.");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (IllegalArgumentException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar perfil: " + e.getMessage());
        }
    }

    private String obtenerCorreoUsuario(HttpServletRequest request) {
        String correoUsuario = (String) request.getAttribute("usuario");
        HttpSession session = request.getSession(false);

        if (correoUsuario == null && session != null) {
            correoUsuario = (String) session.getAttribute("usuario");
        }

        return correoUsuario;
    }

    private void enviarError(HttpServletResponse response, int statusCode, String mensaje) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        JSONMapper.mapper.writeValue(response.getWriter(), error);
    }
}
