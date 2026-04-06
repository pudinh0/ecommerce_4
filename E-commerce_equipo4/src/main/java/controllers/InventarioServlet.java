/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.ProductoDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import service.IProductoServicio;
import service.ProductoService;

/**
 *
 * @author Abraham Coronel
 */
@WebServlet(name = "InventarioServlet", urlPatterns = {"/inventario"})
public class InventarioServlet extends HttpServlet {

    private final IProductoServicio productoServicio = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            Long idAdmin = (Long) session.getAttribute("idUsuario");
            
            List<ProductoDTO> productos = productoServicio.obtenerTodosProductos(idAdmin); 
            
            request.setAttribute("listaProductos", productos);
            request.getRequestDispatcher("/vistas/admin/inventario.jsp").forward(request, response);
            
        } catch (ServletException | IOException e) {
            request.setAttribute("error", "Error al cargar el inventario: " + e.getMessage());
            request.getRequestDispatcher("/vistas/errores/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        try {
            HttpSession session = request.getSession(false);
            Long idAdmin = (Long) session.getAttribute("idUsuario");

            if ("eliminar".equals(accion)) {
                
                Long idProducto = Long.valueOf(request.getParameter("idProducto"));
                productoServicio.eliminarProducto(idProducto, idAdmin);
                
            } else {
                

                ProductoDTO dto = new ProductoDTO();
                dto.setNombre(request.getParameter("nombre"));
                dto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                dto.setDescripcion(request.getParameter("descripcion"));
                dto.setStock(Integer.parseInt(request.getParameter("stock")));
                dto.setRutaImagen(request.getParameter("rutaImagen"));

                if ("crear".equals(accion)) {
                    productoServicio.crearProducto(dto);
                    
                } else if ("actualizar".equals(accion)) {
                    dto.setId(Long.valueOf(request.getParameter("idProducto")));
                    productoServicio.actualizarProducto(dto, idAdmin);
                    
                } else {
                    throw new IllegalArgumentException("Acción no reconocida.");
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/inventario?exito=true");
            
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/inventario?error=Datos numéricos invalidos en el formulario.");
        } catch (IOException | IllegalArgumentException e) {
            response.sendRedirect(request.getContextPath() + "/inventario?error=" + e.getMessage());
        }
    }
    
    
}
