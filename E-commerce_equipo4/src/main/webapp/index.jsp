<%--
    Document   : index
    Created on : 30 mar 2026, 2:09:16 p.m.
    Author     : Camila Zubía
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.UsuarioDTO"%>
<%
    String rolUsuario = (String) session.getAttribute("rol");

    if ("ADMINISTRADOR".equals(rolUsuario)) {
        response.sendRedirect(request.getContextPath() + "/inventario");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Inicio - SoftFriends</title>
        <meta name="description" content="SoftFriends - Encuentra los mejores peluches">
        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body>
        <%@ include file="/fragments/navBar.jspf" %>

        <div class="contenedor-principal">

            <main>

                <section class="hero">
                    <h1>Bienvenidos a SoftFriends</h1>
                    <p>Encuentra tu nuevo mejor amigo. Los peluches mas suaves y adorables a un solo clic de distancia.</p>
                    <a href="${pageContext.request.contextPath}/vistas/app/catalogo.jsp" class="btn btn--primary btn-hero">
                        VER CATÁLOGO
                    </a>
                </section>

                <section class="seccion-destacados">
                    <h2>Productos Destacados</h2>
                    <div class="productos">

                        <article>
                            <figure>
                                <img src="${pageContext.request.contextPath}/assets/img/conejoRosa.png" alt="Peluche de Conejito Rosa">
                            </figure>
                            <div class="info-producto">
                                <h3>Conejito Rosa</h3>
                                <p class="categoria">Bosque • Mediano</p>
                                <div class="precio-carrito">
                                    <p class="precio">$299.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Conejito Rosa al carrito">
                                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                        <article>
                            <figure>
                                <img src="${pageContext.request.contextPath}/assets/img/unicornio.png" alt="Peluche de Unicornio Mágico">
                            </figure>
                            <div class="info-producto">
                                <h3>Unicornio Mágico</h3>
                                <p class="categoria">Místico • Mediano</p>
                                <div class="precio-carrito">
                                    <p class="precio">$349.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Unicornio Mágico al carrito">
                                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                        <article>
                            <figure>
                                <img src="${pageContext.request.contextPath}/assets/img/panda.png" alt="Peluche de Panda Gigante">
                            </figure>
                            <div class="info-producto">
                                <h3>Panda Gigante</h3>
                                <p class="categoria">Bosque • Extra Grande</p>
                                <div class="precio-carrito">
                                    <p class="precio">$799.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Panda Gigante al carrito">
                                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                    </div>
                </section>

            </main>
        </div>

    </body>
</html>