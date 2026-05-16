/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package apiRest;

import dto.ProductoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.IProductoServicio;
import service.ProductoService;
import util.JSONMapper;

/**
 *
 * @author Abraham Coronel Bringas
 */
@WebServlet(name = "CatalogoServlet", urlPatterns = {"/api/productos/*"})
public class CatalogoServlet extends HttpServlet {

    private final IProductoServicio productoServicio = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                String busqueda = request.getParameter("q");
                String categoria = request.getParameter("categoria");

                List<ProductoDTO> productos;

                if ((busqueda != null && !busqueda.trim().isEmpty())
                        || (categoria != null && !categoria.trim().isEmpty())) {
                    productos = productoServicio.buscarYFiltrarProductos(busqueda, categoria);
                } else {
                    productos = productoServicio.listarProductosPublicos();
                }

                response.setStatus(HttpServletResponse.SC_OK);
                JSONMapper.mapper.writeValue(response.getWriter(), productos);

            } else {
                String[] partesRuta = pathInfo.split("/");

                if (partesRuta.length == 2) {
                    try {
                        Long idParam = Long.valueOf(partesRuta[1]);
                        ProductoDTO producto = productoServicio.obtenerProducto(idParam);

                        response.setStatus(HttpServletResponse.SC_OK);
                        JSONMapper.mapper.writeValue(response.getWriter(), producto);

                    } catch (NumberFormatException e) {
                        enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El ID del producto debe ser un numero valido.");
                    } catch (IllegalArgumentException e) {
                        enviarError(response, HttpServletResponse.SC_NOT_FOUND, e.getMessage());
                    }
                } else {
                    enviarError(response, HttpServletResponse.SC_NOT_FOUND, "Ruta no encontrada.");
                }
            }

        } catch (Exception e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error interno del servidor: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para estructurar y enviar los mensajes de error en
     * formato JSON
     */
    private void enviarError(HttpServletResponse response, int statusCode, String mensaje) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        JSONMapper.mapper.writeValue(response.getWriter(), error);
    }

}
