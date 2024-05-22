document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM completamente carregado e analisado');

    function verificarLogin() {
        const authToken = localStorage.getItem('authToken');
        console.log('AuthToken:', authToken);
        return authToken !== null;
    }

    if (verificarLogin()) {
        console.log('Usuário está logado');
        const logoutContainer = document.getElementById('logout-button-container');
        if (logoutContainer) {
            logoutContainer.style.display = 'block';
        } else {
            console.error('Elemento #logout-button-container não encontrado no DOM');
        }
    } else {
        console.log('Usuário não está logado');
    }

    const logoutButton = document.getElementById('logout-button');
    if (logoutButton) {
        logoutButton.addEventListener('click', function() {
            console.log('Botão de logout clicado');
            fetch('http://localhost:8080/usuario/logout', {
                method: 'POST',
                credentials: 'include'
            })
            .then(response => {
                if (response.ok) {
                    console.log('Logout bem-sucedido');
                    localStorage.removeItem('authToken');
                    window.location.href = '/frontend/pages/login-register/login-register.html';
                } else {
                    console.error('Erro ao fazer logout', response.status, response.statusText);
                }
            })
            .catch(error => {
                console.error('Erro na requisição de logout:', error);
            });
        });
    } else {
        console.error('Botão de logout não encontrado no DOM');
    }
});