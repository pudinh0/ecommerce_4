<%--
    Document   : index
    Created on : 30 mar 2026, 2:09:16 p.m.
    Author     : Camila Zubía
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <p>Encuentra tu nuevo mejor amigo. Los peluches más suaves y adorables a un solo clic de distancia.</p>
                    <a href="${pageContext.request.contextPath}/catalogo" class="btn btn--primary btn-hero">
                        VER CATÁLOGO
                    </a>
                </section>

                <section class="seccion-destacados">
                    <h2>Productos Destacados</h2>
                    
                    <div class="productos">
                        
                        <c:choose>
                            <c:when test="${empty requestScope.destacados}">
                                <p class="msg-vacio">No hay productos destacados por el momento.</p>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${requestScope.destacados}" var="producto">
                                    <article>
                                        
                                        <c:choose>
                                            <c:when test="${fn:startsWith(producto.rutaImagen, 'http')}">
                                                <img src="${producto.rutaImagen}" alt="${producto.nombre}" class="img-producto-estandar">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="${pageContext.request.contextPath}/${producto.rutaImagen}" alt="${producto.nombre}" class="img-producto-estandar">
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="info-producto">
                                            <h3>${producto.nombre}</h3>
                                            <p class="categoria">
                                                ${producto.descripcion}
                                            </p>
                                            
                                            <div class="precio-carrito">
                                                <p class="precio">$${producto.precio}</p>
                                                
                                                <form action="${pageContext.request.contextPath}/api/carrito" method="POST">
                                                    <input type="hidden" name="accion" value="agregar">
                                                    <input type="hidden" name="idProducto" value="${producto.id}">
                                                    <button type="submit" class="btn-agregar" aria-label="Agregar al carrito">
                                                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="">
                                                    </button>
                                                </form>
                                            </div>
                                        </div>
                                    </article>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </section>

            </main>
        </div>

    </body>
</html>