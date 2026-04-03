<%-- 
    Document   : inventario
    Created on : 2 abr 2026, 1:15:16 p.m.
    Author     : Camila Zubía
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Inventario - SoftFriends</title>
        <meta name="description" content="Inventario de stock.">
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body>
        <%@ include file="/fragments/navBar.jspf" %>
        <div class="contenedor-principal">
            <nav class="menu-lateral">
                <ul>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoCatalago.png" alt="Icono opciones">
                        <a href="${pageContext.request.contextPath}/catalogo">Opciones</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono inventario">
                        <a href="#">Inventario</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono pedidos">
                        <a href="#">Pedidos</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono reseñas">
                        <a href="#">Reseñas</a>
                    </li>
                </ul>
            </nav>

            <main>
                <div>
                    <div>
                        <h1>Gestión del Inventario</h1>
                    </div>
                    <button><span>agregar</span>Agregar producto</button>
                </div>
                <div>
                    <div>
                        <button>Todo</button>
                        <button>Bajo</button>
                        <button>Categorías</button>
                    </div>
                    <div>
                        <table>
                            <thead>
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
                                <!--
                                for (Object elem : col) {
                                        <tr>
                                            <td>
                                                <div>
                                                    <div>
                                                        <img/>
                                                    </div>
                                                    <span>Nombre del producto</span>
                                                </div>
                                            </td>
                                            <td>id</td>
                                            <td>categoria</td>
                                            <td>stock</td>
                                            <td>precio</td>
                                            <td><span>Estatus</span></td>
                                            <td><button>Editar</button></td>
                                        </tr>
                                    }
                                -->
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
