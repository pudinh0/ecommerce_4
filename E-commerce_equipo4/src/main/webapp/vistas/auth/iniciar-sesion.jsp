<%-- 
    Document   : iniciar-sesion
    Created on : 30 mar 2026, 2:16:46 p.m.
    Author     : Camila Zubía
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Iniciar Sesión - SoftFriends</title>
        <meta name="description" content="Inicia sesión en tu cuenta de SoftFriends.">

        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body>
        <main class="iniciarSesion">

            <div class="seccionIzquierda">
                <img src="https://i.pinimg.com/1200x/1e/15/69/1e1569b92ca23215289d100df2c517e0.jpg" alt="fotoPeluche"> 
                <h1> Bienvenido a SoftFriends </h1>
                <p> Donde cada peluche tiene su historia y cada amigo es para siempre </p>
            </div>       

            <div class="seccionDerecha">
                <div class="tarjetaIniciarSesion">           
                    <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
                        <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo SoftFriends">
                        <span>SoftFriends</span>
                    </a>

                    <div class="textoIniciarSesion">
                        <h2> Inicio de Sesión </h2>
                        <p> ¡Que alegría verte por aquí! </p>
                    </div>
                    
                    <c:if test="${not empty requestScope.error}">
                        <div class="mensaje-error-banner"> 
                            ${requestScope.error}
                        </div>
                    </c:if>

                    <form id="form-login">
                        <div class="form-group">
                            <label for="correo">Correo Electrónico</label>
                            <input type="email" id="correo" name="correo" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Contraseña</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>

                        <button type="submit" class="btn btn--primary btn-auth">Iniciar Sesión</button>
                    </form>

                    <script>
                        window.CONTEXT_PATH = '${pageContext.request.contextPath}';
                    </script>
                    <script type="module" src="${pageContext.request.contextPath}/js/app/login.js"></script>

                    <div class="auth-links">
                        <p>¿No tienes una cuenta? <a href="${pageContext.request.contextPath}/vistas/auth/registrarse.jsp">Regístrate aquí</a></p>
                    </div>
                </div>
            </div>

        </main>

    </body>
</html>
