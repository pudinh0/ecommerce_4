document.addEventListener('DOMContentLoaded', () => {
    const btnLogout = document.getElementById('btn-logout') || document.getElementById('btn-logout-pago'); 
    
    if (btnLogout) {
        btnLogout.addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('jwt_token');
            window.location.href = `${window.CONTEXT_PATH}/logout`;
        });
    }
});
