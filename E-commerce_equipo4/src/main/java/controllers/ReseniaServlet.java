/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.ReseniaDTO;
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
import service.IReseniaService;
import service.ReseniaService;
import util.JSONMapper;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "ReseniaServlet", urlPatterns = {"/api/resenas/*"})
public class ReseniaServlet extends HttpServlet {

    private final IReseniaService reseniaService = new ReseniaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo != null && pathInfo.startsWith("/producto/")) {

                String idParam = pathInfo.substring("/producto/".length());
                Long idProducto = Long.valueOf(idParam);

                List<ReseniaDTO> resenias = reseniaService.obtenerReseniasPorProducto(idProducto);

                response.setStatus(HttpServletResponse.SC_OK);
                JSONMapper.mapper.writeValue(response.getWriter(), resenias);

            } else {
                enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "Ruta invalida");
            }

        } catch (NumberFormatException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, "El ID del producto debe ser numerico.");
        } catch (IOException e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener reseñas: " + e.getMessage());
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
                enviarError(response, HttpServletResponse.SC_UNAUTHORIZED, "Debes iniciar sesion para dejar una reseña.");
                return;
            }

            String correoUsuario = (String) session.getAttribute("usuario");

            ReseniaDTO nuevaResenia = JSONMapper.mapper.readValue(request.getInputStream(), ReseniaDTO.class);

            reseniaService.crearResenia(nuevaResenia, correoUsuario);

            response.setStatus(HttpServletResponse.SC_CREATED);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", "Reseña creada con éxito");
            JSONMapper.mapper.writeValue(response.getWriter(), exito);

        } catch (IllegalArgumentException e) {
            enviarError(response, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IOException e) {
            enviarError(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrio un problema al guardar la reseña: " + e.getMessage());
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
