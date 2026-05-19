document.addEventListener('DOMContentLoaded', async () => {
    const contenedorDetalle = document.getElementById('detalle-producto');
    const listaResenas = document.getElementById('lista-resenas');
    const inputProducto = document.getElementById('id-producto-hidden');
    const params = new URLSearchParams(window.location.search);
    const idProducto = params.get('id');

    if (!idProducto) {
        contenedorDetalle.innerHTML = '<p class="msg-vacio">Producto no especificado.</p>';
        return;
    }

    inputProducto.value = idProducto;

    const formatearPrecio = (valor) => `$${Number(valor || 0).toFixed(2)}`;
    const imagenProducto = (producto) => {
        if (!producto.rutaImagen) {
            return `${window.CONTEXT_PATH}/assets/img/logo.png`;
        }
        return producto.rutaImagen.startsWith('http')
                ? producto.rutaImagen
                : `${window.CONTEXT_PATH}/${producto.rutaImagen}`;
    };

    const agregarAlCarrito = async () => {
        const token = localStorage.getItem('jwt_token');
        if (!token) {
            window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
            return;
        }

        const respuesta = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({idProducto, cantidad: 1})
        });

        if (respuesta.ok) {
            alert('Producto agregado al carrito.');
        } else {
            const error = await respuesta.json();
            alert(error.error || 'No se pudo agregar al carrito.');
        }
    };

    try {
        const respuesta = await fetch(`${window.CONTEXT_PATH}/api/productos/${idProducto}`);
        if (!respuesta.ok) {
            throw new Error('Producto no encontrado');
        }

        const producto = await respuesta.json();
        contenedorDetalle.innerHTML = `
            <article class="detalle-producto-card">
                <figure>
                    <img src="${imagenProducto(producto)}" alt="${producto.nombre}">
                </figure>

                <div class="detalle-producto-info">
                    <p class="categoria">${producto.categoria|| 'Peluche'}</p>
                    <h1>${producto.nombre}</h1>
                    <p class="detalle-producto-descripcion">${producto.descripcion}</p>

                    <strong class="precio-detalle">${formatearPrecio(producto.precio)}</strong>
                    <p class="stock-detalle">Stock disponible: <span>${producto.stock} piezas</span></p>

                    <div class="compra-contenedor">
                        <input type="number" class="cant-input" value="1" min="1" max="${producto.stock}" id="cantidadProducto">
                        <button id="btn-detalle-carrito" class="btn-detalle-carrito" type="button">
                            Agregar al carrito
                        </button>
                    </div>
                </div>
            </article>
        `;
        document.getElementById('btn-detalle-carrito').addEventListener('click', agregarAlCarrito);
    } catch (error) {
        console.error(error);
        contenedorDetalle.innerHTML = '<p class="msg-vacio">No se pudo cargar el producto.</p>';
    }

    try {
        const respuesta = await fetch(`${window.CONTEXT_PATH}/api/resenas/producto/${idProducto}`);
        if (!respuesta.ok) {
            throw new Error('No se pudieron cargar las resenas');
        }

        const resenas = await respuesta.json();
        listaResenas.innerHTML = resenas.length
                ? resenas.map(resena => `
                <article class="resena-card">
                    <strong>${resena.calificacion || 0}/5</strong>
                    <p>${resena.comentario || ''}</p>
                </article>
            `).join('')
                : '<p class="msg-vacio">Este producto aun no tiene resenas.</p>';
    } catch (error) {
        console.error(error);
        listaResenas.innerHTML = '<p class="msg-vacio">No se pudieron cargar las resenas.</p>';
    }
});
