document.addEventListener('DOMContentLoaded', () => {
    const btnProcesarPago = document.getElementById('btn-procesar-pago');
    const textoBoton = document.getElementById('texto-boton-pago');

    if (btnProcesarPago) {
        btnProcesarPago.addEventListener('click', async () => {
            const token = localStorage.getItem('jwt_token');
            if (!token) return alert('Debes iniciar sesión');

            btnProcesarPago.disabled = true;
            textoBoton.innerText = "Validando tarjeta...";

            await new Promise(resolve => setTimeout(resolve, 2000));

            try {
                textoBoton.innerText = "Creando pedido...";

                const respuesta = await fetch(`${window.CONTEXT_PATH}/api/pedidos`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (respuesta.ok) {
                    alert('¡Pago aprobado! Tu pedido se generó con éxito.');
                    window.location.href = `${window.CONTEXT_PATH}/inicio`; 
                } else {
                    const error = await respuesta.json();
                    alert('Error en la compra: ' + error.error);
                }
            } catch (err) {
                console.error(err);
            } finally {
                // Restauramos el botón
                btnProcesarPago.disabled = false;
                textoBoton.innerText = "CONFIRMAR PAGO";
            }
        });
    }
});

