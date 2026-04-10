<%-- 
    Document   : pedidos
    Created on : 7 abr 2026, 3:17:55 p.m.
    Author     : Abraham Coronel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Pedidos - SoftFriends Admin</title>
        <meta name="description" content="Gestión de pedidos de clientes.">
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
                    <li class="active">
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
                    <h1>Gestión de Pedidos</h1>
                </header>
                
                <div class="contenedor-inventario">
                    
                    <c:if test="${param.exito == 'true'}">
                        <div class="alerta alerta-exito">
                            El estado del pedido se ha actualizado correctamente.
                        </div>
                    </c:if>
                    <c:if test="${not empty param.error}">
                        <div class="alerta alerta-error">
                            ${param.error}
                        </div>
                    </c:if>

                    <div class="contenedor-tabla-scroll"> 
                        <table class="tabla-inventario">
                            <thead>
                                <tr>
                                    <th>ID Pedido</th>
                                    <th>Cliente</th>
                                    <th>Fecha de Compra</th>
                                    <th>Total</th>
                                    <th>Estado Actual</th>
                                    <th class="celda-centrada">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaPedidos}" var="pedido">
                                    <tr>
                                        <td><strong>#${pedido.id}</strong></td>
                                        <td>${pedido.usuario.correo}</td>
                                        <td>
                                            ${fn:replace(pedido.fecha, 'T', ' ')}
                                        </td>
                                        <td class="celda-precio-pedido">
                                            $${pedido.total}
                                        </td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/pedidos-admin" method="POST" class="form-actualizar-estado">
                                                <input type="hidden" name="idPedido" value="${pedido.id}">
                                                
                                                <select name="estado" class="select-estado">
                                                    <option value="PENDIENTE" ${pedido.estado == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                                                    <option value="ENVIADO" ${pedido.estado == 'ENVIADO' ? 'selected' : ''}>Enviado</option>
                                                    <option value="ENTREGADO" ${pedido.estado == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
                                                </select>
                                                
                                                <button type="submit" class="btn-guardar-estado">
                                                    Guardar
                                                </button>
                                            </form>
                                        </td>
                                        <td class="celda-centrada">
                                            <a href="${pageContext.request.contextPath}/pedidos-admin/detalle?id=${pedido.id}" class="link-detalles">
                                                Ver Detalles
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <c:if test="${empty requestScope.listaPedidos}">
                                    <tr>
                                        <td colspan="6" class="celda-vacia">
                                            No hay pedidos registrados en el sistema.
                                        </td>
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