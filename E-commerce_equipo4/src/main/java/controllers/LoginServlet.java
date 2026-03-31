/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.UsuarioDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.IUsuarioService;
import service.UsuarioService;

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

        String correo = request.getParameter("txt_correo");
        String contra = request.getParameter("txt_contrasenia");

        if (correo == null || correo.trim().isEmpty() || contra == null || contra.trim().isEmpty()) {
            request.setAttribute("error", "El correo y la contraseña son obligatorios.");
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
            return;
        }

        try {
            UsuarioDTO usuarioLogueado = usuarioService.autenticar(correo, contra);

            if (usuarioLogueado != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSession", usuarioLogueado);
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                request.setAttribute("error", "Correo o contraseña incorrectos.");
                request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
            }

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error interno en el servidor. Intenta más tarde.");
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);
        }
    }

}
