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
                const responseData = await response.json();
                const id_dadosUsuario = responseData.id_dadosUsuario;
                sessionStorage.setItem('id_dadosUsuario', id_dadosUsuario);
                mostrarMensagem(feedbackElement, successMessage, true);
                setTimeout(() => {
                    window.location.href = redirectUrl;
                }, 1000); // Redireciona após 1 segundo
            } else {
                if (response.headers.get('content-type')?.includes('application/json')) {
                    const errorData = await response.json();
                    const errorText = errorData.message || 'Erro desconhecido';
                    mostrarMensagem(feedbackElement, errorMessage + errorText, false);
                } else {
                    const errorText = await response.text();
                    mostrarMensagem(feedbackElement, errorMessage + errorText, false);
                }
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            window.location.href = redirectUrl;
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
                    'Erro ao cadastrar usuário: ',
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
                        const id_dadosUsuario = responseData.id_dadosUsuario;
                        console.log('AuthToken recebido:', authToken); 
                        localStorage.setItem('authToken', authToken);
                        sessionStorage.setItem('id_dadosUsuario', id_dadosUsuario); 
                        mostrarMensagem(feedbackElement, 'Login bem-sucedido! Redirecionando...', true);
                        setTimeout(() => {
                            window.location.href = '/frontend/pages/user-configs/user-configs.html';
                        }, 1000); // Redireciona após 1 segundo
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
});
