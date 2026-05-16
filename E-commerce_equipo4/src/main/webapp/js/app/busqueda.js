document.addEventListener('DOMContentLoaded', () => {
    const formBusqueda = document.getElementById('form-busqueda');
    const inputBusqueda = document.getElementById('input-busqueda');
    const selectCategoria = document.getElementById('select-categoria');
    const contenedorProductos = document.getElementById('contenedor-productos');
    const btnBuscar = document.getElementById('btn-buscar');

    const cargarProductos = async () => {
        const query = inputBusqueda ? inputBusqueda.value.trim() : '';
        const categoria = selectCategoria ? selectCategoria.value : '';

        try {
            const params = new URLSearchParams();
            if (query) params.append('q', query);
            if (categoria) params.append('categoria', categoria);

            const respuesta = await fetch(`${window.CONTEXT_PATH}/api/productos?${params.toString()}`);
            if (!respuesta.ok) {
                throw new Error('Error al obtener catalogo');
            }

            renderizarProductos(await respuesta.json(), contenedorProductos);
        } catch (error) {
            console.error('Error de busqueda:', error);
            if (contenedorProductos) {
                contenedorProductos.innerHTML = `
                    <div class="msg-vacio" style="grid-column: 1 / -1; text-align: center;">
                        <h2>Error de conexion con el servidor.</h2>
                    </div>`;
            }
        }
    };

    if (formBusqueda && !formBusqueda.dataset.asignado) {
        formBusqueda.dataset.asignado = 'true';
        formBusqueda.addEventListener('submit', (e) => {
            if (contenedorProductos) {
                e.preventDefault();
                cargarProductos();
            }
        });
    }

    if (btnBuscar && !btnBuscar.dataset.asignado) {
        btnBuscar.dataset.asignado = 'true';
        btnBuscar.addEventListener('click', (e) => {
            if (contenedorProductos) {
                e.preventDefault();
                cargarProductos();
            } else if (formBusqueda) {
                formBusqueda.submit();
            }
        });
    }
});

function renderizarProductos(productos, contenedor) {
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
