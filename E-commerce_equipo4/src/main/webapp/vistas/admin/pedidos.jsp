<%-- 
    Document   : pedidos
    Created on : 7 abr 2026, 3:17:55 p.m.
    Author     : Abraham Coronel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

                    <div style="overflow-x: auto;"> <table class="tabla-admin">
                            <thead class="encabezado-tabla-inventario">
                                <tr>
                                    <th>ID Pedido</th>
                                    <th>Cliente</th>
                                    <th>Fecha de Compra</th>
                                    <th>Total</th>
                                    <th>Actualizar Estado</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listaPedidos}" var="pedido">
                                    <tr>
                                        <td>#${pedido.id}</td>
                                        <td>${pedido.usuario.correo}</td>
                                        <td>${pedido.fechaCompra}</td>
                                        <td class="precio-destacado">$${pedido.total}</td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/pedidos-admin" method="POST" class="form-en-tabla">
                                                <input type="hidden" name="idPedido" value="${pedido.id}">
                                                
                                                <select name="estado" class="select-admin">
                                                    <option value="PENDIENTE" ${pedido.estado == 'PENDIENTE' ? 'selected' : ''}>Pendiente</option>
                                                    <option value="ENVIADO" ${pedido.estado == 'ENVIADO' ? 'selected' : ''}>Enviado</option>
                                                    <option value="ENTREGADO" ${pedido.estado == 'ENTREGADO' ? 'selected' : ''}>Entregado</option>
                                                </select>
                                                
                                                <button type="submit" class="btn-accion btn-actualizar">
                                                    Actualizar
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                                
                                <c:if test="${empty requestScope.listaPedidos}">
                                    <tr>
                                        <td colspan="5">No hay pedidos registrados en el sistema.</td>
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