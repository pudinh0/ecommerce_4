/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.ProductoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import service.IProductoServicio;
import service.ProductoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "InicioServlet", urlPatterns = {"/inicio"})
public class InicioServlet extends HttpServlet {

    private final IProductoServicio productoServicio = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProductoDTO> todos = productoServicio.listarProductosPublicos();

            List<ProductoDTO> destacados = todos.size() > 3 ? todos.subList(0, 3) : todos;

            request.setAttribute("destacados", destacados);
            request.getRequestDispatcher("/inicio.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

}
