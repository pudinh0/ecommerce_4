<%-- 
    Document   : resenias
    Created on : 7 abr 2026, 3:18:51 p.m.
    Author     : Abraham Coronel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Reseñas - SoftFriends Admin</title>
        <meta name="description" content="Moderación de reseñas de clientes.">
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
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono inventario">
                        <a href="${pageContext.request.contextPath}/inventario">Inventario</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/IconPedidos.png" alt="Icono pedidos">
                        <a href="${pageContext.request.contextPath}/pedidos-admin">Pedidos</a>
                    </li>
                    <li class="active">
                        <img src="${pageContext.request.contextPath}/assets/img/IconReseñas.png" alt="Icono reseñas">
                        <a href="${pageContext.request.contextPath}/resenias-admin">Reseñas</a>
                    </li>
                </ul>
            </nav> 
            
            <main class="inventario-detalle">
                <header class="encabezado-inventario">
                    <h1>Moderación de Reseñas</h1>
                </header>
                
                <div class="contenedor-inventario">
                    
                    <c:if test="${param.exito == 'true'}">
                        <div class="alerta alerta-exito">
                            La reseña ha sido eliminada permanentemente del sistema.
                        </div>
                    </c:if>
                    <c:if test="${not empty param.error}">
                        <div class="alerta alerta-error">
                            ${param.error}
                        </div>
                    </c:if>

                    <div style="overflow-x: auto;">
                        <table class="tabla-admin">
                            <thead class="encabezado-tabla-inventario">
                                <tr>
                                    <th>ID</th>
                                    <th>Producto</th>
                                    <th>Cliente</th>
                                    <th>Calificación</th>
                                    <th>Comentario</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaResenias}" var="resenia">
                                    <tr>
                                        <td>${resenia.id}</td>
                                        
                                        <td>${resenia.producto.nombre}</td>
                                        <td>${resenia.usuario.correo}</td>
                                        
                                        <td class="precio-destacado">
                                            ${resenia.calificacion} / 5
                                        </td>
                                        
                                        <td>
                                            "${resenia.comentario}"
                                        </td>
                                        
                                        <td>
                                            <form action="${pageContext.request.contextPath}/resenias-admin" method="POST" class="form-en-tabla">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="idResenia" value="${resenia.id}">
                                                
                                                <button type="submit" class="btn-accion btn-eliminar"
                                                        onclick="return confirm('¿Estás seguro de que deseas eliminar esta reseña? Esta acción no se puede deshacer.');">
                                                    Eliminar
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <c:if test="${empty requestScope.listaResenias}">
                                    <tr>
                                        <td colspan="6">No hay reseñas registradas en el sistema.</td>
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
