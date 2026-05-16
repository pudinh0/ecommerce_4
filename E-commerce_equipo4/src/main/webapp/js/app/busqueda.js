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
            if (query)
                params.append('q', query);
            if (categoria)
                params.append('categoria', categoria);

            const url = `${window.CONTEXT_PATH}/api/productos?${params.toString()}`;
            const respuesta = await fetch(url);

            if (!respuesta.ok)
                throw new Error('Error al obtener catálogo');
            const productos = await respuesta.json();

            renderizarProductos(productos, contenedorProductos);
        } catch (error) {
            console.error('Error de búsqueda:', error);
            if (contenedorProductos) {
                contenedorProductos.innerHTML = `<div class="msg-vacio" style="grid-column: 1 / -1; text-align: center;"><h2>Error de conexión con el servidor.</h2></div>`;
            }
        }
    };

    if (formBusqueda) {
        formBusqueda.addEventListener('submit', (e) => {
            if (contenedorProductos) {
                e.preventDefault();
                cargarProductos();
            }
        });
    }

    if (btnBuscar) {
        btnBuscar.addEventListener('click', (e) => {
            if (contenedorProductos) {
                e.preventDefault();
                cargarProductos();
            } else {
                formBusqueda.submit();
            }
        });
    }

    if (contenedorProductos) {
        contenedorProductos.addEventListener('click', async (e) => {
            const btn = e.target.closest('.js-add-carrito');

            if (!btn)
                return;

            e.preventDefault();
            const token = localStorage.getItem('jwt_token');

            if (!token) {
                window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
                return;
            }

            btn.disabled = true;

            try {
                const res = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
                    method: 'POST',
                    headers: {'Content-Type': 'application/json', 'Authorization': `Bearer ${token}`},
                    body: JSON.stringify({idProducto: btn.getAttribute('data-id'), cantidad: 1})
                });

                if (res.ok) {
                    window.dispatchEvent(new Event('carritoActualizado'));
                } else {
                    console.error('Error del servidor al agregar al carrito');
                }
            } catch (err) {
                console.error("Error de conexión:", err);
            } finally {
                btn.disabled = false;
            }
        });
    }
});

function renderizarProductos(productos, contenedor) {
    contenedor.innerHTML = '';

    if (productos.length === 0) {
        contenedor.innerHTML = `
            <div class="msg-vacio" style="grid-column: 1 / -1; text-align: center;">
                <h2>No se encontraron productos.</h2>
                <p>Intenta con otra palabra clave o filtro.</p>
            </div>`;
        return;
    }

    productos.forEach(prod => {
        let imgSrc = `${window.CONTEXT_PATH}/assets/img/logo.png`;
        if (prod.rutaImagen) {
            imgSrc = prod.rutaImagen.startsWith('http') ? prod.rutaImagen : `${window.CONTEXT_PATH}/${prod.rutaImagen}`;
        }

        contenedor.innerHTML += `
            <article class="card-producto">
                <figure>
                    <img src="${imgSrc}" alt="${prod.nombre}" loading="lazy" decoding="async" class="img-producto-estandar">
                </figure>
                
                <div style="padding: 10px 0; text-align: center;">
                    <h3 style="margin: 0; font-size: 1.1rem; color: #333;">${prod.nombre}</h3>
                </div>

                <div class="precio-carrito">
                    <p class="precio">$${prod.precio.toFixed(2)}</p>
                    <button type="button" class="btn-agregar js-add-carrito" data-id="${prod.id}" aria-label="Agregar al carrito">
                        <img src="${window.CONTEXT_PATH}/assets/img/IconoAgregarCarrito.png" alt="Agregar al carrito">
                    </button>
                </div>
            </article>
        `;
    });
}

