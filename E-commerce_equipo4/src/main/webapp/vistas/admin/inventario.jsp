<%-- 
    Document   : inventario
    Created on : 2 abr 2026, 1:15:16 p.m.
    Author     : Camila Zubía
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inventario - SoftFriends Admin</title>
        <meta name="description" content="Gestión del catálogo de productos.">
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body class="fondo-gris">
        <%@ include file="/fragments/navBar.jspf" %>
                    
        <div class="contenedor-principal inventario-layout">
            
            <nav class="menu-lateral">
                <ul>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoCatalogo.png" alt="Icono opciones">
                        <a href="#">Opciones</a>
                    </li>
                    <li class="active">
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono inventario">
                        <a href="${pageContext.request.contextPath}/inventario">Inventario</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/IconPedidos.png" alt="Icono pedidos">
                        <a href="${pageContext.request.contextPath}/pedidos-admin">Pedidos</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/IconReseñas.png" alt="Icono reseñas">
                        <a href="${pageContext.request.contextPath}/resenias-admin">Reseñas</a>
                    </li>
                </ul>
            </nav> 
            
            <main class="inventario-detalle">
                <header class="encabezado-inventario">
                    <h1>Gestión de Inventario</h1>
                </header>
                
                <div class="contenedor-inventario">
                    
                    <c:if test="${param.exito == 'true'}">
                        <div class="alerta alerta-exito">
                            La operación se ha realizado con éxito en el catálogo.
                        </div>
                    </c:if>
                    <c:if test="${not empty param.error}">
                        <div class="alerta alerta-error">
                            ${param.error}
                        </div>
                    </c:if>

                    <div class="panel-formulario">
                        <h2>Agregar Nuevo Producto</h2>
                        
                        <form action="${pageContext.request.contextPath}/inventario" method="POST" enctype="multipart/form-data" class="formulario-crear">
                            <input type="hidden" name="accion" value="crear">
                            
                            <div class="form-grupo">
                                <label>Nombre del Producto:</label>
                                <input type="text" name="nombre" required placeholder="Ej: Peluche Ajolote">
                            </div>
                            
                            <div class="form-grupo">
                                <label>Precio ($):</label>
                                <input type="number" name="precio" step="0.01" min="0.1" required placeholder="Ej: 250.50">
                            </div>
                            
                            <div class="form-grupo">
                                <label>Stock Inicial:</label>
                                <input type="number" name="stock" min="1" required placeholder="Ej: 15">
                            </div>
                            
                            <div class="form-grupo">
                                <label>Subir Imagen:</label>
                                <input type="file" name="imagen" accept="image/*" required>
                            </div>
                            
                            <div class="form-grupo completo">
                                <label>Descripción:</label>
                                <textarea name="descripcion" rows="3" required placeholder="Describe los detalles del producto..."></textarea>
                            </div>
                            
                            <button type="submit" class="btn-guardar">Guardar Producto en el Catálogo</button>
                        </form>
                    </div>

                    <div class="contenedor-tabla-scroll">
                        <table class="tabla-admin">
                            <thead class="encabezado-tabla-inventario">
                                <tr>
                                    <th>ID</th>
                                    <th>Foto</th>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Stock</th>
                                    <th>Descripción</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaProductos}" var="producto">
                                    <tr>
                                        <td>${producto.id}</td>
                                        
                                        <td>
                                            <img src="${pageContext.request.contextPath}/${producto.rutaImagen}" 
                                                 alt="${producto.nombre}" 
                                                 class="img-miniatura">
                                        </td>
                                        
                                        <td class="texto-nombre-prod">${producto.nombre}</td>
                                        <td class="precio-destacado">$${producto.precio}</td>
                                        <td>
                                            <span class="badge-stock ${producto.stock > 5 ? 'badge-stock-ok' : 'badge-stock-bajo'}">
                                                ${producto.stock} uds
                                            </span>
                                        </td>
                                        <td class="desc-corta">${producto.descripcion}</td>
                                        
                                        <td>
                                            <form action="${pageContext.request.contextPath}/inventario" method="POST" class="form-en-tabla">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="idProducto" value="${producto.id}">
                                                
                                                <button type="submit" class="btn-accion btn-eliminar"
                                                        onclick="return confirm('¿Seguro que deseas eliminar este producto permanentemente?');">
                                                    Eliminar
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <c:if test="${empty requestScope.listaProductos}">
                                    <tr>
                                        <td colspan="7" class="msg-vacio">No hay productos registrados en el inventario. Utiliza el formulario superior para agregar uno.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>