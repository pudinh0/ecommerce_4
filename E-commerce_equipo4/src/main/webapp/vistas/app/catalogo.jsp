<%-- 
    Document   : catalogo
    Created on : 30 mar 2026, 2:07:34 p.m.
    Author     : Camila Zubía
--%>
<%@page import="models.TipoUsuarioEnum"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Catálogo - SoftFriends</title>
        <meta name="description" content="Explora nuestro catálogo de peluches adorables.">

        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body>

        <%@ include file="/fragments/navBar.jspf" %>

        <div class="contenedor-principal">

            <nav class="menu-lateral">
                <ul>
                    <li class="active">
                        <img src="${pageContext.request.contextPath}/assets/img/iconoCatalogo.png" alt="Icono catalogo">
                        <a href="${pageContext.request.contextPath}/vistas/app/catalogo.jsp">Catálogo</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono filtros">
                        <a href="#">Filtros</a>
                    </li>
                </ul>
            </nav>

            <main>
                <section class="categorias">
                    <ul>
                        <li><button class="active">Todo</button></li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/gota.png" alt="Icono gota">
                                Marino
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/pinos.png" alt="Icono pinos">
                                Osos
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/brillos.png" alt="Icono brillos">
                                Místico
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/gota.png" alt="Icono dinosaurios">
                                Dinosaurios
                            </button>
                        </li>
                    </ul>
                </section>

                <section class="productos">
                    <c:forEach items="${requestScope.listaProductos}" var="producto">
                        <article>
                            <figure>
                                <img src="${pageContext.request.contextPath}/${producto.rutaImagen}" 
                                     alt="${producto.nombre}" 
                                     loading="lazy" 
                                     decoding="async" 
                                     style="width: 100%; height: 200px; object-fit: cover;">
                            </figure>
                            <div class="info-producto">
                                <h3>${producto.nombre}</h3>

                                <p class="categoria" style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                    ${producto.descripcion}
                                </p>

                                <div class="precio-carrito">
                                    <p class="precio">$${producto.precio}</p>

                                    <button class="btn-agregar">
                                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                                    </button>
                                </div>
                            </div>
                        </article>
                    </c:forEach>

                    <c:if test="${empty requestScope.listaProductos}">
                        <div style="grid-column: 1 / -1; text-align: center; padding: 50px;">
                            <h2>No hay productos disponibles por el momento.</h2>
                            <p>Vuelve más tarde para ver nuestras novedades.</p>
                        </div>
                    </c:if>
                </section>
            </main>

            <section class="carrito">
                <header>
                    <h3>Carrito</h3>

                    <c:if test="${not empty requestScope.carrito and not empty requestScope.carrito.items}">
                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST" style="margin: 0;">
                            <input type="hidden" name="accion" value="vaciar">
                            <button type="submit" class="btn-secondary" style="border: none; background: none; color: #ff6b6b; cursor: pointer; text-decoration: underline;">BORRAR TODO</button>
                        </form>
                    </c:if>

                    <p>${empty requestScope.carrito ? 0 : requestScope.carrito.items.size()} artículos</p>
                </header>

                <div class="lista-carrito">
                    <c:choose>
                        <c:when test="${empty requestScope.carrito or empty requestScope.carrito.items}">
                            <p style="text-align: center; padding: 20px; color: #888;">
                                Tu carrito está vacío. ¡Agrega algunos peluches!
                            </p>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.carrito.items}" var="item">
                                <article>
                                    <img src="${pageContext.request.contextPath}/${item.producto.rutaImagen}" alt="${item.producto.nombre} mini">
                                    <div>
                                        <strong>${item.producto.nombre}</strong>
                                        <p>$${item.producto.precio}</p>
                                    </div>
                                    <div style="display: flex; align-items: center; gap: 5px;">
                                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST" style="margin:0;">
                                            <input type="hidden" name="accion" value="disminuir">
                                            <input type="hidden" name="idProducto" value="${item.producto.id}">
                                            <button type="submit">-</button>
                                        </form>

                                        <span>${item.cantidad}</span>

                                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST" style="margin:0;">
                                            <input type="hidden" name="accion" value="aumentar">
                                            <input type="hidden" name="idProducto" value="${item.producto.id}">
                                            <button type="submit">+</button>
                                        </form>
                                    </div>
                                </article>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                <footer>
                    <div>
                        <span>Subtotal</span>
                        <span>$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</span>
                    </div>
                    <div>
                        <strong>Total</strong>
                        <strong class="total-precio">$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</strong>
                    </div>

                    <c:if test="${not empty requestScope.carrito and not empty requestScope.carrito.items}">
                        <button class="btn btn--primary" type="button" onclick="window.location.href = '${pageContext.request.contextPath}/vistas/app/pago.jsp'">
                            <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="" aria-hidden="true">
                            COMPRAR
                        </button>
                    </c:if>
                </footer>
            </section>

        </div>

    </body>
</html>