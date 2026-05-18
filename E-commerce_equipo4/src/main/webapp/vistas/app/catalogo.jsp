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
        <title>Catalogo - SoftFriends</title>
        <meta name="description" content="Explora nuestro catálogo de peluches adorables.">

        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body>

        <%@ include file="/fragments/navBar.jspf" %>

        <div class="contenedor-principal">

            <main>
                <div class= "categorias" id="categorias">

                </div>

                <section class="productos" id="contenedor-productos">

                    <c:choose>
                        <c:when test="${empty requestScope.listaProductos}">
                            <div class="msg-vacio">
                                <h2>No hay productos disponibles por el momento.</h2>
                                <p>Vuelve más tarde para ver nuestras novedades.</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.listaProductos}" var="producto">
                                <article class="card-producto">
                                    <a href="${pageContext.request.contextPath}/vistas/app/detalle-producto.jsp?id=${producto.id}" class="producto-link">
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
                                    </a>

                                    <div style="padding: 10px 0; text-align: center;">
                                        <h3 style="margin: 0; font-size: 1.1rem; color: #333;">${producto.nombre}</h3>
                                        <p class="categoria">${producto.categoria} - ${producto.tamano}</p>
                                    </div>

                                    <div class="precio-carrito">
                                        <p class="precio">$${producto.precio}</p>

                                        <button type="button" class="btn-agregar js-add-carrito" data-id="${producto.id}" aria-label="Agregar al carrito" onclick="window.agregarProductoAlCarrito(this)">
                                            <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                                        </button>
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
                    <p id="contador-articulos-carrito">
                        <c:choose>
                            <c:when test="${empty requestScope.carrito or empty requestScope.carrito.itemsCarrito}">
                                0 artículos
                            </c:when>
                            <c:otherwise>
                                ${requestScope.carrito.itemsCarrito.size()} artículos
                            </c:otherwise>
                        </c:choose>
                    </p>
                </header>

                <div class="lista-carrito" id="contenedor-items-carrito">
                </div>

                <footer>
                    <div class="fila-subtotal">
                        <span>Subtotal</span>
                        <span id="subtotal-carrito">$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</span>
                    </div>
                    <div class="fila-total">
                        <strong>Total</strong>
                        <strong class="total-precio" id="total-precio-carrito">$${empty requestScope.carrito ? '0.00' : requestScope.carrito.total}</strong>
                    </div>

                    <div id="contenedor-accion-carrito">
                        <c:if test="${not empty requestScope.carrito and not empty requestScope.carrito.itemsCarrito}">
                            <button class="btn btn--primary" type="button" onclick="window.location.href = '${pageContext.request.contextPath}/vistas/app/pago.jsp'">
                                <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="" aria-hidden="true">
                                COMPRAR
                            </button>
                        </c:if>
                    </div>
                </footer>
            </section>

        </div>

        <script>
            window.CONTEXT_PATH = '${pageContext.request.contextPath}';

            document.querySelectorAll('.btn-categoria').forEach(btn => {
                btn.addEventListener('click', (e) => {
                    document.querySelectorAll('.btn-categoria').forEach(b => b.classList.remove('active'));
                    e.currentTarget.classList.add('active');
                    document.getElementById('select-categoria').value = e.currentTarget.getAttribute('data-cat');
                    document.getElementById('btn-buscar').click();
                });
            });
        </script>

        <script type="module" src="${pageContext.request.contextPath}/js/app/busqueda.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/app/catalogo.js"></script>
    </body>
</html>
