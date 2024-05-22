function verificarLogin() {
    const authToken = localStorage.getItem('authToken');
    return authToken !== null;
}

const currentPath = window.location.pathname;
const loginRegisterPath = '/frontend/pages/login-register/login-register.html';

if (!verificarLogin() && currentPath !== loginRegisterPath) {
    window.location.href = loginRegisterPath;
    return; // Para garantir que o restante do código não seja executado
}