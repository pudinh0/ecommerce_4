<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Mis pedidos - SoftFriends</title>
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body>
        <%@ include file="/fragments/navBar.jspf" %>

        <main class="contenedor-principal pagina-simple">
            <section class="panel-usuario">
                <header class="encabezado-panel">
                    <h1>Mis pedidos</h1>
                    <a href="${pageContext.request.contextPath}/catalogo">Seguir comprando</a>
                </header>

                <div id="contenedor-pedidos" class="lista-pedidos">
                    <p class="msg-vacio">Cargando tus pedidos...</p>
                </div>
            </section>
        </main>

        <script>
            window.CONTEXT_PATH = '${pageContext.request.contextPath}';
        </script>
        <script type="module" src="${pageContext.request.contextPath}/js/app/pedidos.js"></script>
    </body>
</html>
