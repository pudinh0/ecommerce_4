import { mostrarAlerta } from '../ui/alertaUI.js';

document.addEventListener('DOMContentLoaded', () => {
    const botonesAgregar = document.querySelectorAll('.js-add-carrito');

    botonesAgregar.forEach(btn => {
        btn.addEventListener('click', async (e) => {
            const idProducto = e.currentTarget.getAttribute('data-id');
            const token = localStorage.getItem('jwt_token');

            if (!token) {
                mostrarAlerta('Por favor, inicia sesión para agregar al carrito.', 'warning');
                setTimeout(() => {
                    window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
                }, 1500);
                return;
            }
            const btnEl = e.currentTarget;
            btnEl.disabled = true;
            btnEl.style.opacity = '0.6';

            try {
                const respuesta = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({ idProducto: idProducto, cantidad: 1 })
                });

                if (respuesta.ok) {
                    mostrarAlerta('¡Producto agregado al carrito!', 'exito');
                    // Recarga la página para reflejar el carrito actualizado
                    setTimeout(() => window.location.reload(), 1000);
                } else if (respuesta.status === 401) {
                    mostrarAlerta('Tu sesión expiró. Inicia sesión de nuevo.', 'error');
                    localStorage.removeItem('jwt_token');
                    setTimeout(() => {
                        window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
                    }, 1500);
                } else {
                    mostrarAlerta('Hubo un error al agregar el producto.', 'error');
                }
            } catch (error) {
                console.error(error);
                mostrarAlerta('Error de conexión con el servidor.', 'error');
            } finally {
                btnEl.disabled = false;
                btnEl.style.opacity = '1';
            }
        });
    });
});