export const agregarProductoAlCarrito = async (idProducto) => {
    const token = localStorage.getItem('jwt_token');

    if (!token) throw new Error('NO_AUTENTICADO');

    const respuesta = await fetch(`${window.CONTEXT_PATH}/api/carrito`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({ idProducto, cantidad: 1 })
    });

    if (!respuesta.ok) throw new Error('Error al agregar el producto al carrito');

    return await respuesta.json();
};