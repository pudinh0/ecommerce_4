/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package apiRest;

import dto.PedidoDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.IPedidoService;
import service.PedidoService;
import util.JSONMapper;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/api/pedidos/*"})
public class PedidoServlet extends HttpServlet {

    private final IPedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();
            
            if (pathInfo == null || pathInfo.equals("/")) {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Falta especificar la ruta del pedido o del usuario.");
                return;
            }

            if (pathInfo.startsWith("/usuario/")) {
                String correoUsuario = pathInfo.substring("/usuario/".length());
                List<PedidoDTO> historial = pedidoService.obtenerHistorialUsuario(correoUsuario);
                
                response.setStatus(HttpServletResponse.SC_OK);
                JSONMapper.mapper.writeValue(response.getWriter(), historial);
                
            } 
            else {
                String idParam = pathInfo.substring(1); 
                try {
                    Long idPedido = Long.valueOf(idParam);
                    PedidoDTO pedido = pedidoService.obtenerPedidoPorId(idPedido);
                    
                    response.setStatus(HttpServletResponse.SC_OK);
                    JSONMapper.mapper.writeValue(response.getWriter(), pedido);
                } catch (NumberFormatException e) {
                    enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El ID del pedido debe ser numerico.");
                } catch (IllegalArgumentException e) {
                    enviarError(response, HttpServletResponse.SC_NOT_FOUND, e.getMessage());
                }
            }

        } catch (IOException e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al consultar pedidos: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            HttpSession session = request.getSession(false);
            
            if (session == null || session.getAttribute("usuario") == null) {
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesion para procesar una compra.");
                return;
            }
            
            String correoUsuario = (String) session.getAttribute("usuario");
            
            // Procesamos la compra llamando al service
            pedidoService.procesarCompra(correoUsuario);
            
            // Devolvemos respuesta de éxito 201 (Created)
            response.setStatus(HttpServletResponse.SC_CREATED);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Compra procesada y pedido creado con éxito");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (IllegalArgumentException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IOException e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrio un problema con el pago: " + e.getMessage());
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
