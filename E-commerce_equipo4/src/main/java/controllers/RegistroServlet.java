/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import service.IUsuarioService;
import service.UsuarioService;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {

    private final IUsuarioService usuarioService = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombres = request.getParameter("txt_nombres");
        String primerApellido = request.getParameter("txt_primerApellido");
        String segundoApellido = request.getParameter("txt_segundoApellido");
        String correo = request.getParameter("txt_correo");
        String contra = request.getParameter("txt_contrasenia");

        try {
            usuarioService.registrar(nombres, primerApellido, segundoApellido, correo, contra);
            request.setAttribute("mensaje", "Registro exitoso. Ahora puedes iniciar sesion");
            request.getRequestDispatcher("/vistas/auth/iniciar-sesion.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/vistas/auth/registrarse.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ServletException("Error al registrar usuario", e);
        }
    }

}
