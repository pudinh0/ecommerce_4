<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Detalle de producto - SoftFriends</title>
        <%@ include file="/fragments/styles.jspf" %>
    </head>
    <body>
        <%@ include file="/fragments/navBar.jspf" %>

        <main class="contenedor-principal pagina-simple">
            <section id="detalle-producto" class="detalle-producto">
                <p class="msg-vacio">Cargando producto...</p>
            </section>

            <section class="panel-usuario">
                <header class="encabezado-panel">
                    <h2>Reseñas</h2>
                </header>

                <div id="lista-resenas" class="lista-resenas">
                    <p class="msg-vacio">Cargando reseñas...</p>
                </div>

                <form id="form-resenia" class="formulario-cliente">
                    <input id="id-producto-hidden" type="hidden">
                    <div class="form-group">
                        <label for="select-calificacion">Calificación</label>
                        <select id="select-calificacion" class="form-control" required>
                            <option value="5">5 estrellas</option>
                            <option value="4">4 estrellas</option>
                            <option value="3">3 estrellas</option>
                            <option value="2">2 estrellas</option>
                            <option value="1">1 estrella</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="txt-comentario">Comentario</label>
                        <textarea id="txt-comentario" class="form-control" rows="4" required></textarea>
                    </div>
                    <button class="btn btn--primary" type="submit">Agregar reseña</button>
                </form>
            </section>
        </main>

        <script>
            window.CONTEXT_PATH = '${pageContext.request.contextPath}';
        </script>
        <script type="module" src="${pageContext.request.contextPath}/js/app/detalle-producto.js"></script>
        <script type="module" src="${pageContext.request.contextPath}/js/app/resenas.js"></script>
    </body>
</html>
