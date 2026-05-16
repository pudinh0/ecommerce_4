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
 * @author Abraham Coronel
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        boolean isStaticResource = path.startsWith("/assets/")
                || path.startsWith("/js/")
                || path.startsWith("/css/")
                || path.startsWith("/fragments/")
                || path.contains("styles")
                || path.contains("img");
        boolean isAuthRequest = path.startsWith("/vistas/auth/")
                || path.equals("/catalogo")
                || path.equals("/vistas/app/catalogo.jsp")
                || path.equals("/vistas/app/detalle-producto.jsp")
                || path.equals("/vistas/app/pago.jsp")
                || path.equals("/vistas/app/perfil.jsp")
                || path.equals("/vistas/app/pedidos.jsp")
                || path.equals("/inicio")
                || path.equals("/index.jsp")
                || path.equals("/");
        boolean isAuthPath = path.startsWith("/api/auth");
        boolean isPublicGet = path.equals("/api/productos")
                || (path.startsWith("/api/productos/")
                && req.getMethod().equals("GET"))
                || (path.startsWith("/api/resenas/producto/")
                && req.getMethod().equals("GET"));

        if (isStaticResource || isAuthRequest || isAuthPath || isPublicGet) {
            chain.doFilter(request, response);
            return;
        }

        if (path.startsWith("/api/")) {
            boolean tokenValido = false;
            String authHeader = req.getHeader("Authorization");

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

            //Tambien permitimos acceso a la API si tienen una sesión activa
            HttpSession sesion = req.getSession(false);
            boolean loggedIn = (sesion != null && sesion.getAttribute("usuario") != null);

            if (!tokenValido && !loggedIn) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.setContentType("application/json");
                res.getWriter().write("{\"error\": \"No autorizado para acceder a la API\"}");
                return;
            }

            chain.doFilter(request, response);
            return;
        }

        //Seguridad para el Panel de Administracion
        boolean isAdminRequest = path.startsWith("/vistas/admin/") || path.equals("/inventario") || path.equals("/pedidos-admin");

        HttpSession sesion = req.getSession(false);
        boolean isLoggedIn = (sesion != null && sesion.getAttribute("usuario") != null);

        if (isAdminRequest) {
            String rolUsuario = (sesion != null) ? (String) sesion.getAttribute("rol") : null;
            boolean isAdmin = "ADMINISTRADOR".equals(rolUsuario);

            if (!isLoggedIn || !isAdmin) {
                res.sendRedirect(req.getContextPath() + "/vistas/auth/iniciar-sesion.jsp?error=acceso-denegado");
                return;
            }
        } else if (!isLoggedIn) {
            res.sendRedirect(req.getContextPath() + "/vistas/auth/iniciar-sesion.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}
