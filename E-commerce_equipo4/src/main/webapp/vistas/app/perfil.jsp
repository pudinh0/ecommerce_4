<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Perfil - SoftFriends</title>
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body>
        <%@ include file="/fragments/navBar.jspf" %>

        <main class="contenedor-principal pagina-simple">
            <section class="panel-usuario">
                <header class="encabezado-panel">
                    <h1>Mi perfil</h1>
                    <a href="${pageContext.request.contextPath}/vistas/app/pedidos.jsp">Ver pedidos</a>
                </header>

                <form id="form-perfil" class="formulario-cliente">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="input-nombres">Nombres</label>
                            <input id="input-nombres" class="form-control" type="text" required>
                        </div>
                        <div class="form-group">
                            <label for="input-primer-apellido">Primer apellido</label>
                            <input id="input-primer-apellido" class="form-control" type="text" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="input-segundo-apellido">Segundo apellido</label>
                            <input id="input-segundo-apellido" class="form-control" type="text">
                        </div>
                        <div class="form-group">
                            <label for="input-correo">Correo</label>
                            <input id="input-correo" class="form-control" type="email" readonly>
                        </div>
                    </div>

                    <button class="btn btn--primary" type="submit">Guardar cambios</button>
                </form>
            </section>
        </main>

        <script>
            window.CONTEXT_PATH = '${pageContext.request.contextPath}';
        </script>
        <script type="module" src="${pageContext.request.contextPath}/js/app/perfil.js"></script>
    </body>
</html>
