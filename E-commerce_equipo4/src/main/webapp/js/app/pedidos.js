document.addEventListener('DOMContentLoaded', async () => {
    const contenedor = document.getElementById('contenedor-pedidos');
    const token = localStorage.getItem('jwt_token');

    if (!token) {
        window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
        return;
    }

    const formatearPrecio = (valor) => `$${Number(valor || 0).toFixed(2)}`;
    const formatearFecha = (fecha) => fecha ? new Date(fecha).toLocaleString('es-MX') : 'Sin fecha';

    try {
        const respuesta = await fetch(`${window.CONTEXT_PATH}/api/pedidos/historial`, {
            headers: { 'Authorization': `Bearer ${token}`, 'Accept': 'application/json' }
        });

        if (!respuesta.ok) {
            throw new Error('No se pudo cargar el historial');
        }

        const pedidos = await respuesta.json();

        if (!pedidos.length) {
            contenedor.innerHTML = '<p class="msg-vacio">Aun no tienes pedidos.</p>';
            return;
        }

        contenedor.innerHTML = pedidos.map(pedido => `
            <article class="pedido-card">
                <header>
                    <div>
                        <strong>Pedido #${pedido.id}</strong>
                        <span>${formatearFecha(pedido.fecha)}</span>
                    </div>
                    <span class="badge-estado">${pedido.estado}</span>
                </header>
                <div class="pedido-detalles">
                    ${(pedido.detalles || []).map(detalle => `
                        <div>
                            <span>${detalle.cantidad} x ${detalle.nombreProducto}</span>
                            <strong>${formatearPrecio(detalle.subtotal)}</strong>
                        </div>
                    `).join('')}
                </div>
                <footer>
                    <span>Total</span>
                    <strong>${formatearPrecio(pedido.total)}</strong>
                </footer>
            </article>
        `).join('');
    } catch (error) {
        console.error(error);
        contenedor.innerHTML = '<p class="msg-vacio">No se pudieron cargar tus pedidos.</p>';
    }
});
