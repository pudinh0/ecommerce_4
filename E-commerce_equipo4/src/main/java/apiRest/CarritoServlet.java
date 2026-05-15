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
            HttpSession session = request.getSession(false);
            String correoUsuario = (String) request.getAttribute("usuario");

            if (correoUsuario == null && session != null) {
                correoUsuario = (String) session.getAttribute("usuario");
            }

            if (correoUsuario != null) {
                CarritoDTO carrito = carritoService.obtenerCarrito(correoUsuario);
                response.setStatus(HttpServletResponse.SC_OK);
                JSONMapper.mapper.writeValue(response.getWriter(), carrito);
            } else {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión para ver el carrito.");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
            if (body == null || !body.containsKey("idProducto") || !body.containsKey("cantidad")) {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Faltan datos en la petición.");
                return;
            }

            Long idProducto = Long.valueOf(body.get("idProducto").toString());
            int cantidad = Integer.parseInt(body.get("cantidad").toString());

            HttpSession session = request.getSession(false);
            String correoUsuario = (String) request.getAttribute("usuario");

            if (correoUsuario == null && session != null) {
                correoUsuario = (String) session.getAttribute("usuario");
            }
            if (correoUsuario == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión.");
                return;
            }

            carritoService.agregarProducto(correoUsuario, idProducto, cantidad);

            response.setStatus(HttpServletResponse.SC_CREATED);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Producto agregado con éxito");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (Exception e) {
            e.printStackTrace();
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            Map<String, Object> body = JSONMapper.mapper.readValue(request.getInputStream(), Map.class);
            if (body == null || !body.containsKey("idItem")) {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Falta el id del item a eliminar.");
                return;
            }

            Long idItem = Long.valueOf(body.get("idItem").toString());

            HttpSession session = request.getSession(false);
            String correoUsuario = (String) request.getAttribute("usuario");

            if (correoUsuario == null && session != null) {
                correoUsuario = (String) session.getAttribute("usuario");
            }
            if (correoUsuario == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesión.");
                return;
            }

            carritoService.eliminarItem(correoUsuario, idItem);

            response.setStatus(HttpServletResponse.SC_OK);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Item eliminado del carrito correctamente");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (Exception e) {
            e.printStackTrace();
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error: " + e.getMessage());
        }
    }

    private void enviarError(HttpServletResponse response, int statusCode, String mensaje) throws IOException {
        response.setStatus(statusCode);
        Map<String, String> error = new HashMap<>();
        error.put("error", mensaje);
        JSONMapper.mapper.writeValue(response.getWriter(), error);
    }
}
