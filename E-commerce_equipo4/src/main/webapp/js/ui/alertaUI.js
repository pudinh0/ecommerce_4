export const mostrarAlerta = (mensaje, tipo = 'exito') => {
    let contenedor = document.getElementById('toast-contenedor');
    if (!contenedor) {
        contenedor = document.createElement('div');
        contenedor.id = 'toast-contenedor';
        document.body.appendChild(contenedor);
    }

    const iconos = { exito: '✓', error: '✗', info: 'ℹ', warning: '⚠' };

    const toast = document.createElement('div');
    toast.className = `toast toast--${tipo}`;
    toast.innerHTML = `<span class="toast__icono">${iconos[tipo] ?? 'ℹ'}</span>
                       <span class="toast__mensaje">${mensaje}</span>`;

    contenedor.appendChild(toast);
    setTimeout(() => toast.remove(), 3500);
};