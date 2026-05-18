export const renderizarProductos = (productos, contenedor, callbackAgregar) => {
    contenedor.innerHTML = ''; // Limpiar el estado de carga

    if (productos.length === 0) {
        contenedor.innerHTML = '<p class="msg-vacio">No hay productos disponibles por el momento.</p>';
        return;
    }

    productos.forEach(producto => {
        const article = document.createElement('article');
        article.className = 'card-producto';
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

export function renderizarFiltrosCategorias(contenedorFiltros, productos, callbackFiltro) {
    if (!contenedorFiltros) return;

    const categoriasUnicas = new Set();
    
    productos.forEach(producto => {
        if (producto.categoria) {
            const categoriaNormalizada = producto.categoria.trim().toLowerCase();
            categoriasUnicas.add(categoriaNormalizada);
        }
    });

    contenedorFiltros.innerHTML = `
        <button class="btn-filtro activo" data-categoria="todos">Todos</button>
    `;

    categoriasUnicas.forEach(categoria => {
        const categoriaParaMostrar = categoria.charAt(0).toUpperCase() + categoria.slice(1);
        const boton = document.createElement('button');
        boton.className = 'btn-filtro';
        boton.dataset.categoria = categoria;
        boton.textContent = categoriaParaMostrar;
        contenedorFiltros.appendChild(boton);
    });

    if (callbackFiltro) {
        callbackFiltro();
    }
}