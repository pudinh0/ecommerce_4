document.addEventListener('DOMContentLoaded', () => {
    const btnProcesarPago = document.getElementById('btn-procesar-pago');
    const textoBoton = document.getElementById('texto-boton-pago');
    const listaArticulos = document.getElementById('lista-articulos-pago');
    const subtotalPago = document.getElementById('subtotal-pago');
    const totalPago = document.getElementById('total-pago');
    const opcionesPago = document.querySelectorAll('.opcion-pago');
    let metodoPago = 'Tarjeta';

    const token = localStorage.getItem('jwt_token');
    if (!token) {
        window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
        return;
    }

    const formatearPrecio = (valor) => `$${Number(valor || 0).toFixed(2)}`;

    const cargarCarrito = async () => {
        const respuesta = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
            headers: { 'Authorization': `Bearer ${token}`, 'Accept': 'application/json' }
        });

        if (!respuesta.ok) {
            throw new Error('No se pudo cargar el carrito');
        }

        const carrito = await respuesta.json();
        const items = carrito.itemsCarrito || [];

        if (items.length === 0) {
            listaArticulos.innerHTML = '<p>Tu carrito esta vacio.</p>';
            subtotalPago.textContent = formatearPrecio(0);
            totalPago.textContent = formatearPrecio(0);
            btnProcesarPago.disabled = true;
            return;
        }

        listaArticulos.innerHTML = items.map(item => `
            <div class="item-pago">
                <div>
                    <strong>${item.producto.nombre}</strong>
                    <span>${item.cantidad} x ${formatearPrecio(item.producto.precio)}</span>
                </div>
                <strong>${formatearPrecio(item.producto.precio * item.cantidad)}</strong>
            </div>
        `).join('');

        subtotalPago.textContent = formatearPrecio(carrito.total);
        totalPago.textContent = formatearPrecio(carrito.total);
        btnProcesarPago.disabled = false;
    };

    opcionesPago.forEach(opcion => {
        opcion.addEventListener('click', () => {
            opcionesPago.forEach(item => item.classList.remove('seleccionada'));
            opcion.classList.add('seleccionada');
            metodoPago = opcion.querySelector('span')?.textContent.trim() || 'Tarjeta';
        });
    });

    btnProcesarPago?.addEventListener('click', async () => {
        btnProcesarPago.disabled = true;
        textoBoton.innerText = 'Validando pago...';

        await new Promise(resolve => setTimeout(resolve, 1200));

        try {
            textoBoton.innerText = 'Creando pedido...';
            const respuesta = await fetch(`${window.CONTEXT_PATH}/api/pedidos`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ metodoPago })
            });

            const data = await respuesta.json();

            if (respuesta.ok) {
                alert('Pago aprobado. Tu pedido se genero con exito.');
                window.location.href = `${window.CONTEXT_PATH}/vistas/app/pedidos.jsp`;
            } else {
                alert('Error en la compra: ' + (data.error || 'No se pudo procesar el pedido'));
            }
        } catch (err) {
            console.error(err);
            alert('Fallo la comunicacion con el servidor.');
        } finally {
            btnProcesarPago.disabled = false;
            textoBoton.innerText = 'CONFIRMAR PAGO';
        }
    });

    cargarCarrito().catch(error => {
        console.error(error);
        listaArticulos.innerHTML = '<p>No se pudo cargar tu carrito.</p>';
        btnProcesarPago.disabled = true;
    });
});
