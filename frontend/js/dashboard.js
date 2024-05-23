document.addEventListener('DOMContentLoaded', (event) => {
    const elements = document.querySelectorAll('.shrink');
    let interval;

    elements.forEach(element => {
        let scale = 1;

        const shrink = () => {
            clearInterval(interval);
            interval = setInterval(() => {
                if (scale > 0.95) {
                    scale -= 0.005;
                    element.style.transform = `scale(${scale})`;
                } else {
                    clearInterval(interval);
                }
            }, 16); 
        };

        const grow = () => {
            clearInterval(interval);
            interval = setInterval(() => {
                if (scale < 1) {
                    scale += 0.01;
                    element.style.transform = `scale(${scale})`;
                } else {
                    clearInterval(interval);
                }
            }, 16);
        };

        element.addEventListener('mouseover', shrink);
        element.addEventListener('mouseout', grow);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    console.log('DOM completamente carregado e analisado');

    function verificarLogin() {
        const authToken = localStorage.getItem('authToken');
        console.log('AuthToken:', authToken);
        return authToken !== null;
    }

    if (verificarLogin()) {
        console.log('Usuário está logado');
        const logoutLink = document.getElementById('link2');
        if (logoutLink) {
            logoutLink.addEventListener('click', function(event) {
                event.preventDefault();
                console.log('Link de logout clicado');
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
            console.error('Link de logout não encontrado no DOM');
        }
    } else {
        console.log('Usuário não está logado');
    }
});
