<%-- 
    Document   : error
    Created on : 1 abr 2026, 5:23:42 p.m.
    Author     : PC Gamer
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <title>¡Ups! Algo salió mal - SoftFriends</title>
    <%@ include file="/fragments/styles.jspf" %>
</head>
<body class="fondo-gris">
    <main class="auth-layout">
        <section class="tarjeta-auth tarjeta-error">
            <figure>
                <img src="${pageContext.request.contextPath}/assets/img/oso.png" 
                     alt="Osito de peluche" loading="lazy">
            </figure>
            
            <header>
                <h1>¡Uy! Algo no salió como esperábamos.</h1>
            </header>
            
            <p>
                Parece que uno de nuestros peluches tropezó con un cable. 
                No te preocupes, ya estamos trabajando en ello.
            </p>

            <c:if test="${not empty requestScope.error}">
                <div class="alerta alerta-error">
                    <strong>Detalle técnico:</strong> ${requestScope.error}
                </div>
            </c:if>

            <nav>
                <a href="${pageContext.request.contextPath}/inicio" class="btn btn--primary btn-auth">
                    VOLVER AL INICIO
                </a>
            </nav>
        </section>
    </main>
</body>
</html>