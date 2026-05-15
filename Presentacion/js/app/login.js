import { autenticarUsuario } from '../api/authApi.js';

document.addEventListener('DOMContentLoaded', () => {
    // Asegúrate de que tu etiqueta <form> en el HTML tenga id="form-login"
    const formLogin = document.getElementById('form-login');

    if (formLogin) {
        formLogin.addEventListener('submit', async (e) => {
            e.preventDefault();

            const correo = document.getElementById('correo').value;
            const contrasenia = document.getElementById('password').value; 

            try {
                // Le pasamos las variables a tu API
                const resultado = await autenticarUsuario(correo, contrasenia);
                
                if (resultado.success) {
                    localStorage.setItem('jwt_token', resultado.message);
                    window.location.href = 'catalago.html';
                }
            } catch (error) {
                alert(error.message); 
                console.error('Error de login:', error);
            }
        });
    }
});