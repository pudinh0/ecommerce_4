<%-- 
    Document   : pago
    Created on : 30 mar 2026, 2:17:24 p.m.
    Author     : Camila Zubía 
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Pago - SoftFriends</title>

        <%@ include file="/fragments/styles.jspf" %>
    </head>

    <body class="fondo-gris">
        
        <nav>
            <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo SoftFriends">
                <span>SoftFriends</span>
            </a>

            <ul>
                <li>
                    <a href="${pageContext.request.contextPath}/vistas/app/perfil.jsp"><img src="${pageContext.request.contextPath}/assets/img/configuracion.png" alt="Icono de configuracion"></a>
                </li>
                <li>
                    <a href="#" id="btn-logout-pago"><img src="${pageContext.request.contextPath}/assets/img/iconoUsuario.png" alt="Icono de usuario"></a>
                </li>
            </ul>
        </nav>

        <div class="contenedor-principal pago-layout">

            <main class="seccion-carrito-detalle">
                <header class="encabezado-carrito">
                    <h1>Carrito</h1>
                    <button class="btn-agregar-mas" onclick="window.location.href = '${pageContext.request.contextPath}/catalogo'">+ Agregar</button>
                </header>

                <div class="contenedor-articulos">
                    <div class="encabezado-lista">
                        <span>Artículos</span>
                        <span>Precio</span>
                    </div>

                    <div class="lista-articulos-vacia" id="lista-articulos-pago">
                        <p>Revisa tus artículos antes de pagar.</p>
                    </div>
                </div>
            </main>

            <aside class="seccion-pago-resumen">

                <div class="tarjeta-blanca metodo-pago">
                    <h3>Método de Pago</h3>
                    <div class="opciones-pago">

                        <div class="opcion-pago seleccionada">
                            <div class="radio-custom"></div>
                            <img src="${pageContext.request.contextPath}/assets/img/iconoTarjeta.png" alt="Tarjeta">
                            <span>Tarjeta de Crédito/Débito</span>
                            <img src="${pageContext.request.contextPath}/assets/img/palomitaRosa.png" class="icono-check" alt="Check">
                        </div>

                        <div class="opcion-pago">
                            <div class="radio-custom"></div>
                            <img src="${pageContext.request.contextPath}/assets/img/iconoEfectivo.png" alt="Efectivo">
                            <span>Efectivo</span>
                        </div>

                        <div class="opcion-pago">
                            <div class="radio-custom"></div>
                            <img src="${pageContext.request.contextPath}/assets/img/iconoWallet.png" alt="Wallet">
                            <span>Wallet Digital</span>
                        </div>

                    </div>
                </div>

                <div class="tarjeta-blanca resumen-compra">
                    <h3>Resumen de Compra</h3>

                    <div class="fila">
                        <span>Subtotal</span>
                        <span id="subtotal-pago">Confirmar en servidor</span>
                    </div>

                    <div class="fila-total">
                        <strong>Total</strong>
                        <strong class="total-precio" id="total-pago">Procesando...</strong>
                    </div>

                    <button id="btn-procesar-pago" class="btn btn--primary" type="button">
                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="" aria-hidden="true">
                        <span id="texto-boton-pago">CONFIRMAR PAGO</span>
                    </button>

                    <script>
                        window.CONTEXT_PATH = '${pageContext.request.contextPath}';
                        
                        if(!localStorage.getItem('jwt_token')){
                            window.location.href = '${pageContext.request.contextPath}/vistas/auth/iniciar-sesion.jsp';
                        }
                    </script>
                    <script type="module" src="${pageContext.request.contextPath}/js/app/pago.js"></script>
                    <script type="module" src="${pageContext.request.contextPath}/js/app/logout.js"></script>
                </div>

            </aside>

        </div>

    </body>
</html>
