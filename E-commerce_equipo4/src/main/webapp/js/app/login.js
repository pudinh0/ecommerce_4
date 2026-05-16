document.addEventListener('DOMContentLoaded', () => {
    const formLogin = document.getElementById('form-login');

    if (formLogin) {
        formLogin.addEventListener('submit', async (e) => {
            e.preventDefault();

            const correo = document.getElementById('correo').value.trim();
            const password = document.getElementById('password').value.trim();

            try {
                const respuesta = await fetch(`${window.CONTEXT_PATH}/api/auth/`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ correo, password }) 
                });

                const data = await respuesta.json();

                if (respuesta.ok && data.success) {
                    localStorage.setItem('jwt_token', data.message);
                    const rol = data.rol || data.usuario?.tipoUsuario;
                    const destino = data.destino || (rol === 'ADMINISTRADOR' ? '/inventario' : '/inicio');
                    window.location.href = `${window.CONTEXT_PATH}${destino}`;
                } else {
                    alert('Error: ' + (data.message || 'Credenciales incorrectas'));
                }
            } catch (error) {
                console.error('Error:', error);
                alert('Fallo en la comunicación con el servidor.');
            }
        });
    }
});
