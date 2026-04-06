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
        <title>Inventario - SoftFriends</title>
        <meta name="description" content="Inventario de stock.">
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body class="fondo-gris">
        <%@ include file="/fragments/navBar.jspf" %>
                    
        <div class="contenedor-principal inventario-layout">
            <nav class="menu-lateral">
                <ul>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoCatalago.png" alt="Icono opciones">
                        <a href="#">Opciones</a>
                    </li>
                    <li class="active">
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono inventario">
                        <a href="#">Inventario</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/IconPedidos.png" alt="Icono pedidos">
                        <a href="#">Pedidos</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/IconReseñas.png" alt="Icono reseñas">
                        <a href="#">Reseñas</a>
                    </li>
                </ul>
            </nav> 
            <main class="inventario-detalle">
                <header class="encabezado-inventario">
                    <h1>Gestión del Inventario</h1>
                    <button class="btn-agregar-mas-productos" onclick="window.location.href='${pageContext.request.contextPath}">+ Agregar Producto</button>
                </header>
                <div class="contenedor-inventario">
                    <div class="opciones-inventario">
                        <button>Todo</button>
                        <button>Bajo</button>
                        <button>Categorías</button>
                    </div>
                    <div>
                        <table>
                            <thead class="encabezado-tabla-inventario">
                                <tr>
                                    <th>ID</th>
                                    <th>Producto</th>
                                    <th>Categoría</th>
                                    <th>Stock</th>
                                    <th>Precio</th>
                                    <th>Estatus</th>
                                    <th>Editar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.productos}" var="producto">
                                    <tr>
                                        <td>${producto.id}</td>
                                        <td>
                                            <div>
                                                <div>
                                                    <img src="${pageContext.request.contextPath}${producto.rutaImagen}" alt="Imagen" width="40"/>
                                                </div>
                                                <span>${producto.nombre}</span>
                                            </div>
                                        </td>
                                        <td>${producto.categoria.nombre}</td>
                                        <td>${producto.stock}</td>
                                        <td>$${producto.precio}</td>
                                        <td><span>Activo</span></td>
                                        <td><button><img src="${pageContext.request.contextPath}/assets/img/IconEditar.png" alt="Editar"/></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <p>Productos mostrados</p>
                        <div>
                            <button>Anterior</button>
                            <!-- Aqui va la logica de las paginas -->
                            <button>Siguiente</button>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>
