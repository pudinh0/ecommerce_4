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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import models.Usuario;
import service.IUsuarioService;
import service.UsuarioService;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "AutenticacionServlet", urlPatterns = {"/autenticacion"})
public class AutenticacionServlet extends HttpServlet {
    private final IUsuarioService usuarioService = new UsuarioService();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String correo = request.getParameter("correo");
        String contra = request.getParameter("contrasenia");

        try{
            Usuario usuario = usuarioService.autenticar(correo, contra);
            HttpSession sesionAnterior = request.getSession(false);
            if (sesionAnterior != null) {
                sesionAnterior.invalidate();
            }
            HttpSession sesion = request.getSession(true);
            
            sesion.setAttribute("usuario", usuario);
            
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        }catch(IllegalArgumentException e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/auth/iniciar-sesion.jsp").forward(request, response);
        }catch(IOException e){
            throw new ServletException("Error al autenticar al usuario", e);
        }
    }
    
    

}
