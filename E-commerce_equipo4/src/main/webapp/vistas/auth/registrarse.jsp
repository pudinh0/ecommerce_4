<%-- 
    Document   : registrarse
    Created on : 30 mar 2026, 2:17:57 p.m.
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Registrarse - SoftFriends</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="Crea una cuenta nueva en SoftFriends: ">

        <link rel="stylesheet" type="text/css" href="styles/base.css" />
        <link rel="stylesheet" type="text/css" href="styles/components.css" />
        <link rel="stylesheet" type="text/css" href="styles/layout.css" />
    </head>

    <body class="fondo-gris">

        <main class="auth-layout">

            <section class="tarjeta-auth" style="max-width: 500px;">
                <a class="logo" href="./index.html">
                    <img src="img/logo.png" alt="Logo SoftFriends">
                    <span>SoftFriends</span>
                </a>

                <form action="iniciar-sesion.html" method="GET">

                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Tu nombre" required autocomplete="given-name">
                        </div>

                        <div class="form-group">
                            <label for="apellido">Apellido</label>
                            <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Tu apellido" required autocomplete="family-name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="correo">Correo Electrónico</label>
                        <input type="email" id="correo" name="correo" class="form-control" placeholder="ejemplo@correo.com" required autocomplete="email">
                    </div>

                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" class="form-control" placeholder="Crea una contraseña" required minlength="8" autocomplete="new-password">
                    </div>

                    <div class="form-group">
                        <label for="confirm_password">Confirmar Contraseña</label>
                        <input type="password" id="confirm_password" name="confirm_password" class="form-control" placeholder="Repite tu contraseña" required minlength="8" autocomplete="new-password">
                    </div>

                    <button type="submit" class="btn btn--primary btn-auth">
                        CREAR CUENTA
                    </button>

                </form>

                <div class="auth-links">
                    <p>¿Ya tienes una cuenta? <a href="iniciar-sesion.html">Inicia sesion aqui</a></p>
                </div>

            </section>

        </main>

    </body>

</html>
