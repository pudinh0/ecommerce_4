import { renderizarFiltrosCategorias, renderizarProductos } from '../ui/catalogoUI.js';

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

