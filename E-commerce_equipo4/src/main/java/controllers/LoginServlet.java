/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import service.IUsuarioService;
import service.UsuarioService;
import util.JWTUtil;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String contra = request.getParameter("contrasenia");

        if (correo == null || correo.trim().isEmpty() || contra == null || contra.trim().isEmpty()) {
            request.setAttribute("error", "El correo y la contraseña son obligatorios.");
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
            return;
        }

        try {
            UsuarioDTO usuarioLogueado = usuarioService.autenticar(correo, contra);

            if (usuarioLogueado != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", usuarioLogueado.getCorreo());
                session.setAttribute("rol", usuarioLogueado.getTipoUsuario().name());
                session.setAttribute("idUsuario", usuarioLogueado.getId());
                session.setAttribute("nombreUsuario", usuarioLogueado.getNombres());

                if ("ADMINISTRADOR".equals(usuarioLogueado.getTipoUsuario().name())) {
                    response.sendRedirect(request.getContextPath() + "/inventario");
                } else {
                    response.sendRedirect(request.getContextPath() + "/inicio");
                }
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
        } catch (IOException e) {
            request.setAttribute("error", "Error interno en el servidor. Intenta más tarde.");
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
        }
    }

}
