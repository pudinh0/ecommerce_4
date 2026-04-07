/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.CarritoDTO;
import dto.ProductoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import service.CarritoService;
import service.ICarritoService;
import service.IProductoServicio;
import service.ProductoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "CatalogoWebServlet", urlPatterns = {"/catalogo"})
public class CatalogoWebServlet extends HttpServlet {

    private final IProductoServicio productoServicio = new ProductoService();
    // Instanciamos el servicio del carrito
    private final ICarritoService carritoService = new CarritoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<ProductoDTO> productos = productoServicio.listarProductosPublicos();
            request.setAttribute("listaProductos", productos);

            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("usuario") != null) {

                String correoUsuario = (String) session.getAttribute("usuario");

                CarritoDTO carrito = carritoService.obtenerCarrito(correoUsuario);
                request.setAttribute("carrito", carrito);
            }

            request.getRequestDispatcher("/vistas/app/catalogo.jsp").forward(request, response);

        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar el catálogo: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

}
