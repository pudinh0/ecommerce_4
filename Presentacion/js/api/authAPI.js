export const autenticarUsuario = async (correo, contrasenia) => {
    const respuesta = await fetch('/api/auth/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ correo, contrasenia })
    });

    const data = await respuesta.json();

    if (!respuesta.ok) {
        throw new Error(data.message || 'Error en la autenticación');
    }

    return data; 
};