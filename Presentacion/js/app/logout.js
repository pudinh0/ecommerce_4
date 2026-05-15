document.addEventListener('DOMContentLoaded', () => {
    const btnLogout = document.getElementById('btn-logout'); 
    
    if (btnLogout) {
        btnLogout.addEventListener('click', (e) => {
            e.preventDefault();
            localStorage.removeItem('jwt_token');
            window.location.href = 'index.html'; 
        });
    }
});