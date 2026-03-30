<%-- 
    Document   : index
    Created on : 30 mar 2026, 2:09:16 p.m.
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>Inicio - SoftFriends</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="SoftFriends - Encuentra los mejores peluches">

        <link rel="stylesheet" type="text/css" href="styles/base.css" />
        <link rel="stylesheet" type="text/css" href="styles/components.css" />
        <link rel="stylesheet" type="text/css" href="styles/layout.css" />
    </head>

    <body>

        <nav>
            <a class="logo" href="./index.html">
                <!-- <img src="img/logo.png" alt="Logo SoftFriends"> -->
                <span>SoftFriends</span>
            </a>

            <form name="form_busqueda" action="catalago.html" method="GET">
                <img src="img/iconoBuscar.png" alt="Icono de búsqueda" class="search-icon">
                <input id="txt_buscar" name="txt_buscar" type="text" placeholder="Search">
            </form>

            <ul>
                <li>
                    <a href="#"><img src="img/configuracion.png" alt="Configuracion de la cuenta"></a>
                </li>
                <li>
                    <a href="#"><img src="img/iconoUsuario.png" alt="Perfil de usuario"></a>
                </li>
            </ul>
        </nav>

        <div class="contenedor-principal">

            <main>

                <section class="hero">
                    <h1>Bienvenidos a SoftFriends</h1>
                    <p>Encuentra tu nuevo mejor amigo. Los peluches más suaves y adorables a un solo clic de distancia.</p>
                    <a href="catalago.html" class="btn btn--primary btn-hero">
                        VER CATÁLOGO
                    </a>
                </section>

                <section class="seccion-destacados">
                    <h2>Productos Destacados</h2>
                    <div class="productos">

                        <article>
                            <figure>
                                <img src="img/conejoRosa.png" alt="Peluche de Conejito Rosa">
                            </figure>
                            <div class="info-producto">
                                <h3>Conejito Rosa</h3>
                                <p class="categoria">Bosque • Mediano</p>
                                <div class="precio-carrito">
                                    <p class="precio">$299.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Conejito Rosa al carrito">
                                        <img src="img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                        <article>
                            <figure>
                                <img src="img/unicornio.png" alt="Peluche de Unicornio Mágico">
                            </figure>
                            <div class="info-producto">
                                <h3>Unicornio Mágico</h3>
                                <p class="categoria">Místico • Mediano</p>
                                <div class="precio-carrito">
                                    <p class="precio">$349.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Unicornio Mágico al carrito">
                                        <img src="img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                        <article>
                            <figure>
                                <img src="img/panda.png" alt="Peluche de Panda Gigante">
                            </figure>
                            <div class="info-producto">
                                <h3>Panda Gigante</h3>
                                <p class="categoria">Bosque • Extra Grande</p>
                                <div class="precio-carrito">
                                    <p class="precio">$799.99</p>
                                    <button class="btn-agregar" aria-label="Agregar Panda Gigante al carrito">
                                        <img src="img/IconoAgregarCarrito.png" alt="">
                                    </button>
                                </div>
                            </div>
                        </article>

                    </div>
                </section>

            </main>
        </div>

    </body>

</html>
