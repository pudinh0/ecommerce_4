/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.CarritoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.CarritoService;
import service.ICarritoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/carrito"})
public class CarritoServlet extends HttpServlet {

    private final ICarritoService carritoService = new CarritoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String correoUsuario = (String) session.getAttribute("usuario");

            CarritoDTO carrito = carritoService.obtenerCarrito(correoUsuario);
            request.setAttribute("carrito", carrito);
            request.getRequestDispatcher("/vistas/app/carrito.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar el carrito: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String correoUsuario = (String) session.getAttribute("usuario");

            String accion = request.getParameter("accion");

            if ("agregar".equals(accion)) {
                Long idProducto = Long.valueOf(request.getParameter("idProducto"));
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));

                carritoService.agregarProducto(correoUsuario, idProducto, cantidad);
            } else if ("eliminar".equals(accion)) {
                Long idItem = Long.valueOf(request.getParameter("idItem"));

                carritoService.eliminarItem(correoUsuario, idItem);
            }

            response.sendRedirect(request.getContextPath() + "/carrito");

        } catch (IOException | NumberFormatException e) {
            request.setAttribute("error", "Ocurrio un problema con el carrito: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }
}
