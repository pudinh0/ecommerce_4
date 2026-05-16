import { mostrarAlerta } from '../ui/alertaUI.js';

const crearHeaders = (token, incluirJson = false) => {
    const headers = { 'Accept': 'application/json' };
    if (incluirJson) {
        headers['Content-Type'] = 'application/json';
    }
    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }
    return headers;
};

const obtenerCarritoServidor = async (token) => {
    const res = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
        method: 'GET',
        headers: crearHeaders(token)
    });
    if (!res.ok)
        throw new Error('Error al cargar carrito');
    return await res.json();
};

const cambiarCantidadAPI = async (idProducto, cantidad, token) => {
    const res = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
        method: 'POST',
        headers: crearHeaders(token, true),
        body: JSON.stringify({idProducto, cantidad})
    });
    return res.ok;
};

const eliminarItemAPI = async (idItem, token) => {
    const res = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
        method: 'DELETE',
        headers: crearHeaders(token, true),
        body: JSON.stringify({idItem})
    });
    return res.ok;
};

const agregarProductoDesdeBoton = async (btn) => {
    const tokenActual = localStorage.getItem('jwt_token');

    if (btn.dataset.enviando === 'true') {
        return;
    }

    btn.dataset.enviando = 'true';
    btn.disabled = true;

    try {
        const ok = await cambiarCantidadAPI(btn.getAttribute('data-id'), 1, tokenActual);
        if (!ok) {
            window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
            return;
        }
        window.dispatchEvent(new Event('carritoActualizado'));
    } catch (err) {
        console.error(err);
        mostrarAlerta('No se pudo agregar el producto', 'error');
    } finally {
        btn.dataset.enviando = 'false';
        btn.disabled = false;
    }
};

window.agregarProductoAlCarrito = agregarProductoDesdeBoton;

const actualizarContenedorDerecho = (carrito) => {
    const contenedor = document.getElementById('contenedor-items-carrito');
    const contador = document.getElementById('contador-articulos-carrito');
    const tot = document.getElementById('total-precio-carrito');
    const sub = document.getElementById('subtotal-carrito');
    const contenedorBotonPago = document.getElementById('contenedor-accion-carrito');

    if (!contenedor)
        return;
    contenedor.innerHTML = '';
    const items = carrito.itemsCarrito || [];

    if (items.length === 0) {
        contenedor.innerHTML = `<p class="msg-vacio">Tu carrito está vacío.<br>¡Agrega algunos peluches!</p>`;
        if (contador)
            contador.innerText = '0 artículos';
        if (tot)
            tot.innerText = '$0.00';
        if (sub)
            sub.innerText = '$0.00';
        if (contenedorBotonPago)
            contenedorBotonPago.innerHTML = '';
        return;
    }

    if (contador)
        contador.innerText = `${items.length} artículos`;
    if (tot)
        tot.innerText = `$${carrito.total.toFixed(2)}`;
    if (sub)
        sub.innerText = `$${carrito.total.toFixed(2)}`;

    if (contenedorBotonPago) {
        contenedorBotonPago.innerHTML = `
            <button class="btn btn--primary" type="button" onclick="window.location.href = '${window.CONTEXT_PATH}/vistas/app/pago.jsp'">
                <img src="${window.CONTEXT_PATH}/assets/img/IconoAgregarCarrito.png" alt="" aria-hidden="true">
                COMPRAR
            </button>`;
    }

    items.forEach(item => {
        const article = document.createElement('article');
        const img = item.producto.rutaImagen.startsWith('http') ? item.producto.rutaImagen : `${window.CONTEXT_PATH}/${item.producto.rutaImagen}`;

        article.innerHTML = `
            <img src="${img}" alt="${item.producto.nombre}">
            <div class="info-item-carrito">
                <strong>${item.producto.nombre}</strong>
                <p>$${item.producto.precio.toFixed(2)}</p>
            </div>
            <div class="controles-cantidad">
                <form method="POST">
                    <input type="hidden" name="accion" value="disminuir">
                    <input type="hidden" name="idProducto" value="${item.producto.id}">
                    <input type="hidden" name="idItem" value="${item.idItemCarrito || ''}">
                    <button type="submit">-</button>
                </form>
                <span>${item.cantidad}</span>
                <form method="POST">
                    <input type="hidden" name="accion" value="aumentar">
                    <input type="hidden" name="idProducto" value="${item.producto.id}">
                    <button type="submit">+</button>
                </form>
                <form method="POST">
                    <input type="hidden" name="accion" value="eliminar">
                    <input type="hidden" name="idProducto" value="${item.producto.id}">
                    <input type="hidden" name="idItem" value="${item.idItemCarrito || ''}">
                    <button type="submit" aria-label="Eliminar producto">x</button>
                </form>
            </div>
        `;
        contenedor.appendChild(article);
    });
};

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwt_token');

    try {
        actualizarContenedorDerecho(await obtenerCarritoServidor(token));
    } catch (e) {
        console.error(e);
    }

    window.addEventListener('carritoActualizado', async () => {
        try {
            actualizarContenedorDerecho(await obtenerCarritoServidor(localStorage.getItem('jwt_token')));
            mostrarAlerta('Producto agregado al carrito', 'success');
        } catch (e) {
            console.error(e);
        }
    });

    const contenedorItems = document.getElementById('contenedor-items-carrito');
    if (contenedorItems && !contenedorItems.dataset.eventosAsignados) {
        contenedorItems.dataset.eventosAsignados = "true";
        contenedorItems.addEventListener('submit', async (e) => {
            e.preventDefault();
            const form = e.target;
            const btn = form.querySelector('button');
            if (btn)
                btn.disabled = true;

            const accion = form.querySelector('input[name="accion"]')?.value;
            const idProducto = form.querySelector('input[name="idProducto"]')?.value;
            const idItem = form.querySelector('input[name="idItem"]')?.value;

            try {
                if (accion === 'aumentar') {
                    await cambiarCantidadAPI(idProducto, 1, token);
                } else if (accion === 'disminuir') {
                    await cambiarCantidadAPI(idProducto, -1, token);
                } else if (accion === 'eliminar') {
                    if (idItem && idItem !== '') {
                        await eliminarItemAPI(idItem, token);
                    }
                }
                actualizarContenedorDerecho(await obtenerCarritoServidor(token));
            } catch (err) {
                mostrarAlerta('Error en conexión', 'error');
            } finally {
                if (btn)
                    btn.disabled = false;
            }
        });
    }
});
