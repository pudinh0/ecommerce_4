document.addEventListener('DOMContentLoaded', async () => {
    const formPerfil = document.getElementById('form-perfil');
    const token = localStorage.getItem('jwt_token');

    if (!token) {
        window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
        return;
    }

    const setValue = (id, value) => {
        const input = document.getElementById(id);
        if (input) {
            input.value = value || '';
        }
    };

    try {
        const res = await fetch(`${window.CONTEXT_PATH}/api/usuarios/perfil`, {
            headers: { 'Authorization': `Bearer ${token}` }
        });

        if (!res.ok) {
            throw new Error('No se pudo cargar el perfil');
        }

        const perfil = await res.json();
        setValue('input-nombres', perfil.nombres);
        setValue('input-primer-apellido', perfil.primerApellido);
        setValue('input-segundo-apellido', perfil.segundoApellido);
        setValue('input-correo', perfil.correo);
    } catch (error) {
        console.error('Error al cargar perfil', error);
        alert('No se pudo cargar tu perfil.');
    }

    if (formPerfil) {
        formPerfil.addEventListener('submit', async (e) => {
            e.preventDefault();
            const btnGuardar = formPerfil.querySelector('button[type="submit"]');
            btnGuardar.disabled = true;
            btnGuardar.innerText = 'Guardando...';

            const perfilActualizado = {
                nombres: document.getElementById('input-nombres').value.trim(),
                primerApellido: document.getElementById('input-primer-apellido').value.trim(),
                segundoApellido: document.getElementById('input-segundo-apellido').value.trim(),
                correo: document.getElementById('input-correo').value.trim()
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

                const data = await res.json();

                if (res.ok) {
                    alert(data.mensaje || 'Perfil actualizado con exito');
                } else {
                    alert(data.error || 'No se pudo actualizar el perfil');
                }
            } catch (error) {
                console.error(error);
                alert('Fallo la comunicacion con el servidor.');
            } finally {
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar cambios';
            }
        });
    }
});
