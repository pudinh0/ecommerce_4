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
 * @author PC Gamer
 */
@WebServlet(name = "CatalogoServlet", urlPatterns = {"/catalogo"})
public class CatalogoServlet extends HttpServlet {

    private final IProductoServicio productoServicio = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<ProductoDTO> productos = productoServicio.listarProductosPublicos();

            request.setAttribute("listaProductos", productos);
            request.getRequestDispatcher("/vistas/app/catalogo.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar los productos: " + e.getMessage());
            request.getRequestDispatcher("/vistas/app/catalogo.jsp").forward(request, response);
        }
    }

}
