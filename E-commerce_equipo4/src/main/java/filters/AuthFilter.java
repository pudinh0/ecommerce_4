/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import util.JWTUtil;

/**
 *
 * @author Usuario
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();

        String authHeader = req.getHeader("Autorization");
        boolean tokenValido = false;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String usuario = JWTUtil.validarToken(token);
                req.setAttribute("usuario", usuario);
                tokenValido = true;
            } catch (Exception e) {
                tokenValido = false;
            }
        }

        HttpSession sesion = req.getSession(false);
        boolean loggedIn = (sesion != null && sesion.getAttribute("usuario") != null);
        boolean loginRequest = path.contains("iniciar-sesion.jsp")
                || path.contains("registrarse.jsp")
                || path.contains("autenticacion")
                || path.contains("/registro")
                || path.contains("error.jsp");
        boolean apiRequest = path.startsWith("/api/");
        boolean resourceStaticRequest = path.contains("/assets/") || path.contains("styles") || path.contains("img");
        if (path.startsWith(req.getContextPath() + "/api/")) {
            chain.doFilter(request, response);
            return;
        }
        if (loginRequest || resourceStaticRequest) {
            chain.doFilter(request, response);
            return;
        }
        if (apiRequest) {
            if (!tokenValido) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("No autorizado");
                return;
            }
        }
        if (loggedIn) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/vistas/auth/iniciar-sesion.jsp");
        }
    }
}
