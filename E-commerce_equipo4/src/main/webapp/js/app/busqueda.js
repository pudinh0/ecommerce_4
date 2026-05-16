document.addEventListener('DOMContentLoaded', () => {
    const inputBusqueda = document.getElementById('input-busqueda');
    const selectCategoria = document.getElementById('select-categoria');
    const btnBuscar = document.getElementById('btn-buscar');
    const contenedorProductos = document.getElementById('contenedor-productos'); // El DOM donde renderizas

    const cargarProductos = async () => {
        const query = inputBusqueda ? inputBusqueda.value.trim() : '';
        const categoria = selectCategoria ? selectCategoria.value : '';
        
        try {
            const params = new URLSearchParams();
            if (query) params.append('q', query);
            if (categoria) params.append('categoria', categoria);

            const url = `${window.CONTEXT_PATH}/api/productos?${params.toString()}`;
            const respuesta = await fetch(url);
            
            if (!respuesta.ok) throw new Error('Error al obtener catálogo');
            const productos = await respuesta.json();

            renderizarProductos(productos, contenedorProductos);
        } catch (error) {
            console.error('Error de búsqueda:', error);
            contenedorProductos.innerHTML = `<p class="error">No pudimos cargar los productos. Intenta más tarde.</p>`;
        }
    };

    if (btnBuscar) {
        btnBuscar.addEventListener('click', (e) => {
            e.preventDefault();
            cargarProductos();
        });
    }

    if (inputBusqueda) {
        inputBusqueda.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                e.preventDefault();
                cargarProductos();
            }
        });
    }
});

function renderizarProductos(productos, contenedor) {
    contenedor.innerHTML = ''; 
    if (productos.length === 0) {
        contenedor.innerHTML = '<p>No se encontraron productos con esos filtros.</p>';
        return;
    }
    productos.forEach(prod => {
        contenedor.innerHTML += `
            <div class="card-producto">
                <img src="${window.CONTEXT_PATH}/${prod.rutaImagen}" alt="${prod.nombre}">
                <h3>${prod.nombre}</h3>
                <p>$${prod.precio.toFixed(2)}</p>
                <button class="js-add-carrito" data-id="${prod.id}">Agregar al carrito</button>
            </div>
        `;
    });
}
