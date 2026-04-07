/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.ReseniaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.IReseniaService;
import service.ReseniaService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "AdminReseniasServlet", urlPatterns = {"/resenias-admin"})
public class AdminReseniasServlet extends HttpServlet {

    private final IReseniaService reseniaService = new ReseniaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<ReseniaDTO> listaResenias = reseniaService.obtenerTodas();

            request.setAttribute("listaResenias", listaResenias);

            request.getRequestDispatcher("/vistas/admin/resenias.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar las reseñas: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            Long idAdmin = (Long) session.getAttribute("idUsuario");

            String accion = request.getParameter("accion");
            Long idResenia = Long.valueOf(request.getParameter("idResenia"));

            if ("eliminar".equals(accion)) {
                reseniaService.eliminarResenia(idResenia, idAdmin);

                response.sendRedirect(request.getContextPath() + "/resenias-admin?exito=true");
            } else {
                throw new IllegalArgumentException("Acción no reconocida.");
            }

        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/resenias-admin?error=" + e.getMessage());
        } catch (IOException e) {
            response.sendRedirect(request.getContextPath() + "/resenias-admin?error=Ocurrió un error interno al intentar eliminar la reseña.");
        }
    }

}
