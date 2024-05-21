document.addEventListener('DOMContentLoaded', function() {
    const signUpForm = document.getElementById('sign-up-form');
    const signInForm = document.getElementById('sign-in-form');

    async function enviarFormulario(url, userData, successMessage, errorMessage) {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (response.ok) {
                alert(successMessage);
                window.location.href = '/frontend/pages/reg-complement/reg-complement.html';
            } else {
                const errorText = await response.text();
                console.error(errorMessage, errorText);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
        }
    }

    signUpForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const email = document.getElementById('signup-email').value;
        const password = document.getElementById('signup-password').value;
        const userData = { email: email, senha: password };
        enviarFormulario(
            'http://localhost:8080/usuario/registro',
            userData,
            'Usuário cadastrado com sucesso!',
            'Erro ao cadastrar usuário:'
        );
    });

    signInForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        const email = document.getElementById('login-email').value;
        const password = document.getElementById('login-password').value;
        const userData = { email: email, senha: password };
    
        try {
            const response = await fetch('http://localhost:8080/usuario/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });
    
            if (response.ok) {
                redirectToDashboard();
            } else {
                const errorText = await response.text();
                console.error('Erro ao fazer login:', errorText);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
        }
    });
    
    function redirectToDashboard() {
        window.location.href = '/frontend/dashboard.html';
    }
});