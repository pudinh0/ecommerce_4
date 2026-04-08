<%-- 
    Document   : catalogo
    Created on : 30 mar 2026, 2:07:34 p.m.
    Author     : Camila Zubía
--%>
<%@page import="models.TipoUsuarioEnum"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                        <a href="${pageContext.request.contextPath}/catalogo">Catálogo</a>
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

                    <c:choose>
                        <c:when test="${empty requestScope.listaProductos}">
                            <div class="msg-vacio">
                                <h2>No hay productos disponibles por el momento.</h2>
                                <p>Vuelve más tarde para ver nuestras novedades.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.listaProductos}" var="producto">
                                <article>
                                    <figure>
                                        <c:choose>
                                            <c:when test="${fn:startsWith(producto.rutaImagen, 'http')}">
                                                <img src="${producto.rutaImagen}" alt="${producto.nombre}" loading="lazy" decoding="async" class="img-producto-estandar">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/${producto.rutaImagen}" alt="${producto.nombre}" loading="lazy" decoding="async" class="img-producto-estandar">
                                            </c:otherwise>
                                        </c:choose>
                                    </figure>

                                    <div class="info-producto">
                                        <h3>${producto.nombre}</h3>

                                        <p class="categoria">
                                            ${producto.descripcion}
                                        </p>

                                        <div class="precio-carrito">
                                            <p class="precio">$${producto.precio}</p>

                                            <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST">
                                                <input type="hidden" name="accion" value="agregar">
                                                <input type="hidden" name="idProducto" value="${producto.id}">
                                                <button type="submit" class="btn-agregar" aria-label="Agregar al carrito">
                                                    <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </article>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </section>
            </main>

            <section class="carrito">
                <header>
                    <h3>Carrito</h3>

                    <c:if test="${not empty requestScope.carrito and not empty requestScope.carrito.itemsCarrito}">
                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST">
                            <input type="hidden" name="accion" value="vaciar">
                            <button type="submit" class="btn-secondary">BORRAR TODO</button>
                        </form>
                    </c:if>

                    <c:choose>
                        <c:when test="${empty requestScope.carrito or empty requestScope.carrito.itemsCarrito}">
                            <p>0 artículos</p>
                        </c:when>
                        <c:otherwise>
                            <p>${requestScope.carrito.itemsCarrito.size()} artículos</p>
                        </c:otherwise>
                    </c:choose>
                </header>

                <div class="lista-carrito">
                    <c:choose>
                        <c:when test="${empty requestScope.carrito or empty requestScope.carrito.itemsCarrito}">
                            <p class="msg-vacio">
                                Tu carrito está vacío. ¡Agrega algunos peluches!
                            </p>
                        </c:when>

                        <c:otherwise>
                            <c:forEach items="${requestScope.carrito.itemsCarrito}" var="item">
                                <article>

                                    <c:choose>
                                        <c:when test="${fn:startsWith(item.producto.rutaImagen, 'http')}">
                                            <img src="${item.producto.rutaImagen}" alt="${item.producto.nombre}">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${pageContext.request.contextPath}/${item.producto.rutaImagen}" alt="${item.producto.nombre}">
                                        </c:otherwise>
                                    </c:choose>

                                    <div class="info-item-carrito">
                                        <strong>${item.producto.nombre}</strong>
                                        <p>$${item.producto.precio}</p>
                                    </div>

                                    <div class="controles-cantidad">
                                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST">
                                            <input type="hidden" name="accion" value="disminuir">
                                            <input type="hidden" name="idProducto" value="${item.producto.id}">
                                            <button type="submit">-</button>
                                        </form>

                                        <span>${item.cantidad}</span>

                                        <form action="${pageContext.request.contextPath}/carrito-mvc" method="POST">
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
                    <div class="fila-subtotal">
                        <span>Subtotal</span>
                        <span>$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</span>
                    </div>
                    <div class="fila-total">
                        <strong>Total</strong>
                        <strong class="total-precio">$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</strong>
                    </div>

                    <c:if test="${not empty requestScope.carrito and not empty requestScope.carrito.itemsCarrito}">
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