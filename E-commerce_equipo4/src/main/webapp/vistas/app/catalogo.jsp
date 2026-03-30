<%-- 
    Document   : catalogo
    Created on : 30 mar 2026, 2:07:34 p.m.
    Author     : Usuario
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <title>SoftFriends</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/base.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/components.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/styles/layout.css" />
    </head>

    <body>

        <nav>
            <a class="logo" href="./index.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="Logo SoftFriends">
                <span>SoftFriends</span>
            </a>

            <form name="form_busqueda">
                <img src="${pageContext.request.contextPath}/assets/img/iconoBuscar.png" alt="Icono de búsqueda" class="search-icon">
                <input id="txt_buscar" name="txt_buscar" type="text" placeholder="Search">
            </form>

            <ul>
                <li>
                    <img src="${pageContext.request.contextPath}/assets/img/configuracion.png" alt="Icono de configuracion">
                </li>
                <li>
                    <img src="${pageContext.request.contextPath}/assets/img/iconoUsuario.png" alt="Icono de usuario">
                </li>
            </ul>
        </nav>

        <div class="contenedor-principal">

            <nav class="menu-lateral">
                <ul>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoCatalago.png" alt="Icono catalogo">
                        <a href="#">Catálogo</a>
                    </li>
                    <li>
                        <img src="${pageContext.request.contextPath}/assets/img/iconoFiltro.png" alt="Icono filtros">
                        <a href="#">Filtros</a>
                    </li>
                </ul>
            </nav>

            <main>
                <section class="categorias">
                    <ul>
                        <li><button class="active">Todo</button></li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/gota.png" alt="Icono gota">
                                Marino
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/pinos.png" alt="Icono pinos">
                                Osos
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/brillos.png" alt="Icono brillos">
                                Místico
                            </button>
                        </li>
                        <li>
                            <button>
                                <img src="${pageContext.request.contextPath}/assets/img/gota.png" alt="Icono dinosaurios">
                                Dinosaurios
                            </button>
                        </li>
                    </ul>
                </section>

                <section class="productos">

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/goldenRetriever.png" alt="Golden Retriever" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Golden Retriever</h3>
                            <p class="categoria">Perros • Grande</p>

                            <div class="precio-carrito">
                                <p class="precio">$499.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/conejoRosa.png" alt="Conejito Rosa" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Conejito Rosa</h3>
                            <p class="categoria">Bosque • Mediano</p>
                            <div class="precio-carrito">
                                <p class="precio">$299.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/panda.png" alt="Panda Gigante" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Panda Gigante</h3>
                            <p class="categoria">Bosque • Extra Grande</p>
                            <div class="precio-carrito">
                                <p class="precio">$799.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/ajolote.png" alt="Axolotl" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Axolotl</h3>
                            <p class="categoria">Marino • Mediano</p>
                            <div class="precio-carrito">
                                <p class="precio">$349.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/ballena.png" alt="Ballena Azul" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Ballena Azul</h3>
                            <p class="categoria">Marino • Grande</p>
                            <div class="precio-carrito">
                                <p class="precio">$499.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/oso.png" alt="Teddy Bear Clasico" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Teddy Bear Clasico</h3>
                            <p class="categoria">Osos • Pequeno</p>
                            <div class="precio-carrito">
                                <p class="precio">$199.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/unicornio.png" alt="Unicornio Magico" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Unicornio Magico</h3>
                            <p class="categoria">Mistico • Mediano</p>
                            <div class="precio-carrito">
                                <p class="precio">$349.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                    <article>
                        <figure>
                            <img src="${pageContext.request.contextPath}/assets/img/rex.png" alt="Tiranosaurio Rex" loading="lazy" decoding="async">
                        </figure>
                        <div class="info-producto">
                            <h3>Tiranosaurio Rex</h3>
                            <p class="categoria">Dinosaurios • Mediano</p>
                            <div class="precio-carrito">
                                <p class="precio">$349.99</p>
                                <button class="btn-agregar"><img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png"
                                                                 alt="Agregar al carrito"></button>
                            </div>
                        </div>
                    </article>

                </section>
            </main>

            <section class="carrito">
                <header>
                    <h3>Carrito</h3>
                    <button class="btn-secondary">BORRAR TODO</button>
                    <p>2 artículos</p>
                </header>

                <div class="lista-carrito">
                    <article>
                        <img src="${pageContext.request.contextPath}/assets/img/oso.png" alt="Teddy Bear Clásico mini">
                        <div>
                            <strong>Teddy Bear Clásico</strong>
                            <p>$199.99</p>
                        </div>
                        <div>
                            <button>-</button>
                            <span>1</span>
                            <button>+</button>
                        </div>
                    </article>

                    <article>
                        <img src="${pageContext.request.contextPath}/assets/img/ballena.png" alt="Ballena Azul mini">
                        <div>
                            <strong>Ballena Azul</strong>
                            <p>$499.99</p>
                        </div>
                        <div>
                            <button>-</button>
                            <span>1</span>
                            <button>+</button>
                        </div>
                    </article>
                </div>

                <footer>
                    <div>
                        <span>Subtotal</span>
                        <span>$699.98</span>
                    </div>
                    <div>
                        <strong>Total</strong>
                        <strong class="total-precio">$699.98</strong>
                    </div>
                    <button class="btn btn--primary" type="button">
                        <img src="${pageContext.request.contextPath}/assets/img/IconoAgregarCarrito.png" alt="" aria-hidden="true">
                        COMPRAR
                    </button>
                </footer>
            </section>

        </div>

    </body>

</html>