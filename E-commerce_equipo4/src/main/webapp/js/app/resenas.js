document.addEventListener('DOMContentLoaded', () => {
    const formResenia = document.getElementById('form-resenia');
    
    if (formResenia) {
        formResenia.addEventListener('submit', async (e) => {
            e.preventDefault();
            const token = localStorage.getItem('jwt_token');
            
            if (!token) {
                alert('Inicia sesión para dejar una reseña.');
                window.location.href = `${window.CONTEXT_PATH}/vistas/auth/iniciar-sesion.jsp`;
                return;
            }

            const idProducto = document.getElementById('id-producto-hidden').value;
            const calificacion = document.getElementById('select-calificacion').value;
            const comentario = document.getElementById('txt-comentario').value;

            try {
                const res = await fetch(`${window.CONTEXT_PATH}/api/resenas/`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSON.stringify({
                        productoId: idProducto,
                        calificacion: parseInt(calificacion),
                        comentario: comentario
                    })
                });

                if (res.ok) {
                    alert('¡Gracias por tu reseña!');
                    window.location.reload(); // Recargar para ver la nueva reseña
                } else {
                    const err = await res.json();
                    alert(err.error);
                }
            } catch (error) {
                console.error('Error al enviar reseña:', error);
            }
        });
    }
});

