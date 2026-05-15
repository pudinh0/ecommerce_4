export const obtenerProductosPublicos = async () => {
    const respuesta = await fetch('/api/productos');
    
    if (!respuesta.ok) {
        throw new Error('No se pudieron cargar los productos');
    }
    
    return await respuesta.json(); // Retorna la lista de ProductoDTO
};