/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package apiRest;

import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import service.IUsuarioService;
import service.UsuarioService;
import util.JSONMapper;
import util.JWTUtil;

/**
 *
 * @author adell
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/api/auth/*"})
public class AuthServlet extends HttpServlet {

    private final IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Map<String, String> body = JSONMapper.mapper.readValue(
                    request.getReader(), Map.class
            );

            String correo = body.get("correo");
            String password = body.get("password");

            if (correo == null || correo.trim().isEmpty()
                    || password == null || password.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(JSONMapper.mapper.writeValueAsString(
                        Map.of("success", false, "message", "Correo y contraseña son obligatorios")
                ));
                return;
            }

            UsuarioDTO usuario = usuarioService.autenticar(correo.trim(), password.trim());

            if (usuario != null) {
                String token = JWTUtil.generarToken(usuario.getCorreo());
                String rol = usuario.getTipoUsuario().name();
                String destino = "ADMINISTRADOR".equals(rol) ? "/inventario" : "/inicio";
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuario.getCorreo());
                session.setAttribute("rol", rol);
                session.setAttribute("idUsuario", usuario.getId());
                session.setAttribute("nombreUsuario", usuario.getNombres());

                response.setStatus(HttpServletResponse.SC_OK);
                out.print(JSONMapper.mapper.writeValueAsString(
                        Map.of(
                                "success", true,
                                "message", token,
                                "usuario", usuario,
                                "rol", rol,
                                "destino", destino
                        )
                ));
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.print(JSONMapper.mapper.writeValueAsString(
                        Map.of("success", false, "message", "Credenciales incorrectas")
                ));
            }

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(JSONMapper.mapper.writeValueAsString(
                    Map.of("success", false, "message", e.getMessage())
            ));
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(JSONMapper.mapper.writeValueAsString(
                    Map.of("success", false, "message", "Error interno del servidor")
            ));
        }
    }
}
