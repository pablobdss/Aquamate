document.addEventListener('DOMContentLoaded', function() {
    const signUpForm = document.getElementById('sign-up-form');
    const signInForm = document.getElementById('sign-in-form');

    async function enviarFormulario(url, userData, successMessage, errorMessage, redirectUrl, feedbackElement) {
        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (response.ok) {
                mostrarMensagem(feedbackElement, successMessage, true);
                window.location.href = redirectUrl; // Redireciona imediatamente
            } else {
                const errorText = await response.text();
                console.error(errorMessage, errorText);
                mostrarMensagem(feedbackElement, 'Erro: ' + errorText, false);
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            mostrarMensagem(feedbackElement, 'Erro na requisição. Tente novamente.', false);
        }
    }

    function validarCampos(email, password, feedbackElement) {
        if (!email || !password) {
            mostrarMensagem(feedbackElement, 'Por favor, preencha todos os campos.', false);
            return false;
        }
        return true;
    }

    function mostrarMensagem(element, message, isSuccess) {
        element.textContent = message;
        element.className = 'feedback';
        if (isSuccess) {
            element.classList.add('success');
        } else {
            element.classList.add('error');
        }
    }

    if (signUpForm) {
        signUpForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const email = document.getElementById('signup-email').value;
            const password = document.getElementById('signup-password').value;
            const feedbackElement = document.getElementById('signup-feedback');

            if (validarCampos(email, password, feedbackElement)) {
                const userData = { email: email, senha: password };
                enviarFormulario(
                    'http://localhost:8080/usuario/registro',
                    userData,
                    'Usuário cadastrado com sucesso!',
                    'Erro ao cadastrar usuário:',
                    '/frontend/pages/user-configs/user-configs.html',
                    feedbackElement
                );
            }
        });
    }

    if (signInForm) {
        signInForm.addEventListener('submit', async function(event) {
            event.preventDefault();
            const email = document.getElementById('login-email').value;
            const password = document.getElementById('login-password').value;
            const feedbackElement = document.getElementById('signin-feedback');
        
            if (validarCampos(email, password, feedbackElement)) {
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
                        const responseData = await response.json(); 
                        const authToken = responseData.authToken;
                        console.log('AuthToken recebido:', authToken); // Log para verificar se o token foi recebido
                        localStorage.setItem('authToken', authToken);
                        mostrarMensagem(feedbackElement, 'Login bem-sucedido! Redirecionando...', true);
                        redirectToDashboard();
                    } else {
                        const errorText = await response.text();
                        console.error('Erro ao fazer login:', errorText);
                        mostrarMensagem(feedbackElement, 'Erro ao fazer login. Verifique suas credenciais e tente novamente.', false);
                    }
                } catch (error) {
                    console.error('Erro na requisição:', error);
                    mostrarMensagem(feedbackElement, 'Erro na requisição. Tente novamente.', false);
                }
            }
        });
    }

    function redirectToDashboard() {
        window.location.href = '/frontend/dashboard.html';
    }
});