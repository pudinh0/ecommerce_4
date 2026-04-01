<%-- 
    Document   : registrarse
    Created on : 30 mar 2026, 2:17:57 p.m.
    Author     : Camila Zubía
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Registrarse - SoftFriends</title>
        <meta name="description" content="Crea una cuenta nueva en SoftFriends.">

        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body class="fondo-gris">
        <main class="auth-layout">

            <section class="tarjeta-auth" style="max-width: 500px;">
                <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
                    <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo SoftFriends">
                    <span>SoftFriends</span>
                </a>

                <c:if test="${not empty error}">
                    <div class="mensaje-error-banner">
                        ${error}
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/registro" method="POST">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" id="nombre" name="txt_nombres" class="form-control" placeholder="Tu nombre" required autocomplete="given-name">
                        </div>

                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <input type="text" id="apellido" name="txt_primerApellido" class="form-control" placeholder="Tu apellido" required autocomplete="family-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="segundo_apellido">Segundo Apellido</label>
                        <input type="text" id="segundo_apellido" name="txt_segundoApellido" class="form-control" placeholder="Opcional">
                    </div>

                    <div class="form-group">
                        <label for="correo">Correo Electrónico</label>
                        <input type="email" id="correo" name="txt_correo" class="form-control" placeholder="ejemplo@correo.com" required autocomplete="email">
                    </div>

                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="txt_contrasenia" class="form-control" placeholder="Crea una contraseña" required minlength="8" autocomplete="new-password">
                    </div>

                    <button type="submit" class="btn btn--primary btn-auth">CREAR CUENTA</button>
                </form>

                <div class="auth-links">
                    <p>¿Ya tienes una cuenta? <a href="${pageContext.request.contextPath}/vistas/auth/iniciar-sesion.jsp">Inicia sesion aqui</a></p>
                </div>

            </section>

        </main>

    </body>
</html>