/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.CarritoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CarritoService;
import service.ICarritoService;
import service.IPedidoService;
import service.PedidoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "PagoServlet", urlPatterns = {"/pago"})
public class PagoServlet extends HttpServlet {

    private final ICarritoService carritoService = new CarritoService();
    private final IPedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession(false);
            String correoUsuario = (String) session.getAttribute("usuario");

            CarritoDTO carrito = carritoService.obtenerCarrito(correoUsuario);

            if (carrito == null || carrito.getItemsCarrito() == null || carrito.getItemsCarrito().isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/catalogo");
                return;
            }

            request.setAttribute("carrito", carrito);
            request.getRequestDispatcher("/vistas/app/pago.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar la página de pago: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String correoUsuario = (String) session.getAttribute("usuario");
            
            pedidoService.procesarCompra(correoUsuario);
            
            request.getRequestDispatcher("/vistas/app/catalogo.jsp").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/catalogo?pago=exito");

        } catch (ServletException | IOException | NumberFormatException e) {
            request.setAttribute("error", "Ocurrió un problema con el pago: " + e.getMessage());
            request.getRequestDispatcher("/vistas/app/pago.jsp").forward(request, response);
        }
    }
}
