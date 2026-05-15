export const obtenerProductosPublicos = async () => {
    const respuesta = await fetch(`${window.CONTEXT_PATH}/api/productos`);
    
    if (!respuesta.ok) {
        throw new Error('No se pudieron cargar los productos');
    }
    
    return await respuesta.json(); // Retorna la lista de ProductoDTO
};