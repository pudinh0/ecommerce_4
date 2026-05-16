document.addEventListener('DOMContentLoaded', async () => {
    const formPerfil = document.getElementById('form-perfil');
    const token = localStorage.getItem('jwt_token');

    if (!token) return; // Si no hay token, no es necesario ejecutar esto

    // 1. Cargar datos al iniciar
    try {
        const res = await fetch(`${window.CONTEXT_PATH}/api/usuarios/perfil`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });
        if (res.ok) {
            const perfil = await res.json();
            document.getElementById('input-nombre').value = perfil.nombre || '';
            document.getElementById('input-correo').value = perfil.correo || ''; // Debería ser readonly
            document.getElementById('input-telefono').value = perfil.telefono || '';
        }
    } catch (error) {
        console.error('Error al cargar perfil', error);
    }

    // 2. Guardar cambios (Editar)
    if (formPerfil) {
        formPerfil.addEventListener('submit', async (e) => {
            e.preventDefault();
            const btnGuardar = formPerfil.querySelector('button[type="submit"]');
            btnGuardar.disabled = true;
            btnGuardar.innerText = 'Guardando...';

            const perfilActualizado = {
                nombre: document.getElementById('input-nombre').value,
                correo: document.getElementById('input-correo').value,
                telefono: document.getElementById('input-telefono').value
            };

            try {
                const res = await fetch(`${window.CONTEXT_PATH}/api/usuarios/perfil`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify(perfilActualizado)
                });

                if (res.ok) {
                    alert('Perfil actualizado con éxito'); // Idealmente cambiar por un Toast/Alerta de UI
                } else {
                    const err = await res.json();
                    alert(err.error || 'No se pudo actualizar el perfil');
                }
            } catch (error) {
                console.error(error);
            } finally {
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar Cambios';
            }
        });
    }
});


