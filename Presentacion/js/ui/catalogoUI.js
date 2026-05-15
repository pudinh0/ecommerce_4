export const renderizarProductos = (productos, contenedor, callbackAgregar) => {
    contenedor.innerHTML = ''; // Limpiar el estado de carga

    if (productos.length === 0) {
        contenedor.innerHTML = '<p class="msg-vacio">No hay productos disponibles por el momento.</p>';
        return;
    }

    productos.forEach(producto => {
        const article = document.createElement('article');

        const rutaImagen = producto.rutaImagen.startsWith('http') 
            ? producto.rutaImagen 
            : `../${producto.rutaImagen}`;

        article.innerHTML = `
            <figure>
                <img src="${rutaImagen}" alt="${producto.nombre}" loading="lazy" decoding="async" class="img-producto-estandar">
            </figure>
            <div class="info-producto">
                <h3>${producto.nombre}</h3>
                <p class="categoria">${producto.descripcion}</p>
                <div class="precio-carrito">
                    <p class="precio">$${producto.precio.toFixed(2)}</p>
                    <button class="btn-agregar" aria-label="Agregar al carrito">
                        <img src="img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                    </button>
                </div>
            </div>
        `;

        const btnAgregar = article.querySelector('.btn-agregar');
        btnAgregar.addEventListener('click', () => callbackAgregar(producto.id));

        contenedor.appendChild(article);
    });
};