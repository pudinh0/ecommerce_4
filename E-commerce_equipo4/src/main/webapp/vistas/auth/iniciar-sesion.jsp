<%-- 
    Document   : iniciar-sesion
    Created on : 30 mar 2026, 2:16:46 p.m.
    Author     : Usuario
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
                        <p> Que alegría verte por aquí! </p>
                    </div>

                    <form action="${pageContext.request.contextPath}/index.jsp" method="POST">

                        <div class="form-group">
                            <label for="correo">Correo Electronico</label>
                            <input type="email" id="correo" name="correo" class="form-control" placeholder="ejemplo@correo.com" required autocomplete="email">
                        </div>

                        <div class="form-group">
                            <div class="lblPassword">             
                                <label for="password">Contraseña</label>
                                <a href="#">¿Olvidaste tu contraseña?</a>
                            </div>
                            <input type="password" id="password" name="password" class="form-control" placeholder="Ingresa tu contraseña" required autocomplete="current-password">
                        </div>

                        <button type="submit" class="btn btn--primary btn-auth">
                            Iniciar Sesión
                        </button>

                    </form>

                    <div class="auth-links">
                        <p>¿No tienes una cuenta? <a href="${pageContext.request.contextPath}/vistas/auth/registrarse.jsp">Regístrate aqui</a></p>
                    </div>
                </div>
            </div>

        </main>

    </body>
</html>
