/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.PedidoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.IPedidoService;
import service.PedidoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "AdminPedidosServlet", urlPatterns = {"/pedidos-admin"})
public class AdminPedidosServlet extends HttpServlet {

    private final IPedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<PedidoDTO> todosLosPedidos = pedidoService.obtenerTodosLosPedidos();
            
            request.setAttribute("listaPedidos", todosLosPedidos);
            request.getRequestDispatcher("/vistas/admin/pedidos.jsp").forward(request, response);
            
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar los pedidos: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            HttpSession session = request.getSession(false);
            Long idAdmin = (Long) session.getAttribute("idUsuario");
            
            Long idPedido = Long.valueOf(request.getParameter("idPedido"));
            String nuevoEstado = request.getParameter("estado");
            
            pedidoService.actualizarEstadoPedido(idPedido, nuevoEstado, idAdmin);
            
            response.sendRedirect(request.getContextPath() + "/pedidos-admin?exito=true");
            
        } catch (IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/pedidos-admin?error=" + e.getMessage());
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/pedidos-admin?error=Error al actualizar el estado.");
        }
    }

}
