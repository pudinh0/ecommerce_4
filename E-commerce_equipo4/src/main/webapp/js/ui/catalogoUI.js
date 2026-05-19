export function renderizarProductos(productos, contenedor) {
    if (!contenedor) {
        return;
    }

    contenedor.innerHTML = '';

    if (productos.length === 0) {
        contenedor.innerHTML = `
            <div class="msg-vacio" style="grid-column: 1 / -1; text-align: center;">
                <h2>No se encontraron productos.</h2>
                <p>Intenta con otra palabra clave o filtro.</p>
            </div>`;
        return;
    }

    contenedor.innerHTML = productos.map(prod => {
        const imgSrc = prod.rutaImagen
            ? (prod.rutaImagen.startsWith('http') ? prod.rutaImagen : `${window.CONTEXT_PATH}/${prod.rutaImagen}`)
            : `${window.CONTEXT_PATH}/assets/img/logo.png`;

        return `
            <article class="card-producto">
                <a href="${window.CONTEXT_PATH}/vistas/app/detalle-producto.jsp?id=${prod.id}" class="producto-link">
                    <figure>
                        <img src="${imgSrc}" alt="${prod.nombre}" loading="lazy" decoding="async" class="img-producto-estandar">
                    </figure>
                </a>
                <div style="padding: 10px 0; text-align: center;">
                    <h3 style="margin: 0; font-size: 1.1rem; color: #333;">${prod.nombre}</h3>
                </div>
                <div class="precio-carrito">
                    <p class="precio">$${prod.precio.toFixed(2)}</p>
                    <button type="button" class="btn-agregar js-add-carrito" data-id="${prod.id}" aria-label="Agregar al carrito" onclick="window.agregarProductoAlCarrito(this)">
                        <img src="${window.CONTEXT_PATH}/assets/img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                    </button>
                </div>
            </article>
        `;
    }).join('');
}

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