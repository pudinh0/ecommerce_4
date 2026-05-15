import { obtenerProductosPublicos } from '../api/productosApi.js';
import { agregarProductoAlCarrito } from '../api/carritoApi.js';
import { renderizarProductos } from '../ui/catalogoUI.js';

document.addEventListener('DOMContentLoaded', async () => {
    const contenedorProductos = document.querySelector('.productos');

    const manejarAgregarAlCarrito = async (idProducto) => {
        try {
            await agregarProductoAlCarrito(idProducto);
            alert('¡Producto agregado al carrito exitosamente!');
        } catch (error) {
            if (error.message === 'NO_AUTENTICADO') {
                alert('Debes iniciar sesión para agregar productos a tu carrito.');
                window.location.href = 'iniciar-sesion.html';
            } else {
                console.error(error);
                alert('Hubo un problema al agregar el producto.');
            }
        }
    };

    try {
        contenedorProductos.innerHTML = '<p>Cargando catálogo...</p>';

        const productos = await obtenerProductosPublicos();

        renderizarProductos(productos, contenedorProductos, manejarAgregarAlCarrito);

    } catch (error) {
        contenedorProductos.innerHTML = '<p class="msg-error">Error al conectar con el servidor.</p>';
        console.error('Error de inicio de catálogo:', error);
    }
});