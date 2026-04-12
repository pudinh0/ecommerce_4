/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package apiRest;

import dto.CarritoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import service.CarritoService;
import service.ICarritoService;
import util.JSONMapper;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "CarritoServlet", urlPatterns = {"/api/carrito/*"})
public class CarritoServlet extends HttpServlet {

    private final ICarritoService carritoService = new CarritoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo != null && pathInfo.length() > 1) {
                String correoUsuario = pathInfo.substring(1);

                CarritoDTO carrito = carritoService.obtenerCarrito(correoUsuario);

                response.setStatus(HttpServletResponse.SC_OK);
                JSONMapper.mapper.writeValue(response.getWriter(), carrito);
            } else {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Falta el ID (correo) del usuario en la URL.");
            }

        } catch (IOException e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar el carrito: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, Object> body = JSONMapper.mapper.readValue(request.getInputStream(), Map.class);

            Long idProducto = Long.valueOf(body.get("idProducto").toString());
            int cantidad = Integer.parseInt(body.get("cantidad").toString());

            HttpSession session = request.getSession(false);

            String correoUsuario = (String) request.getAttribute("usuario");

            if (correoUsuario == null) {
                if (session != null) {
                    correoUsuario = (String) session.getAttribute("usuario");
                }
            }

            if (correoUsuario == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión para agregar al carrito.");
                return;
            }

            carritoService.agregarProducto(correoUsuario, idProducto, cantidad);

            response.setStatus(HttpServletResponse.SC_CREATED);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Producto agregado al carrito con exito");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (IOException | NumberFormatException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Ocurrió un problema con el carrito: " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, Object> body = JSONMapper.mapper.readValue(request.getInputStream(), Map.class);
            Long idItem = Long.valueOf(body.get("idItem").toString());

            HttpSession session = request.getSession(false);

            String correoUsuario = (String) request.getAttribute("usuario");

            if (correoUsuario == null) {
                if (session != null) {
                    correoUsuario = (String) session.getAttribute("usuario");
                }
            }

            if (correoUsuario == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión para editar el carrito.");
                return;
            }

            carritoService.eliminarItem(correoUsuario, idItem);

            response.setStatus(HttpServletResponse.SC_OK);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Item eliminado del carrito correctamente");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (IOException | NumberFormatException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Error al eliminar el item: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para mandar los errores limpios en JSON
     */
    private void enviarError(HttpServletResponse response, int statusCode, String mensaje) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        JSONMapper.mapper.writeValue(response.getWriter(), error);
    }
}
