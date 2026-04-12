/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package apiRest;

import dto.ResponseMessageDTO;
import dto.UsuarioAuthDTO;
import dto.UsuarioDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Usuario;
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

        UsuarioAuthDTO req = JSONMapper.mapper.readValue(request.getInputStream(), UsuarioAuthDTO.class);
        UsuarioDTO user = usuarioService.autenticar(req.getCorreo(), req.getContrasenia());
        ResponseMessageDTO mensaje = new ResponseMessageDTO();

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            mensaje.setSuccess(false);
            mensaje.setMessage("Credenciales incorrectas para el correo: " + req.getCorreo());
            JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
            return;
        }

        String token = JWTUtil.generarToken(user.getCorreo());

        mensaje.setSuccess(true);
        mensaje.setMessage(token);
        response.setContentType("application/json");
        JSONMapper.mapper.writeValue(response.getWriter(), mensaje);
    }
}
