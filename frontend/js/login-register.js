document.addEventListener('DOMContentLoaded', function() {
    const signUpForm = document.getElementById('sign-up-form');

    signUpForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const email = document.getElementById('signup-email').value;
        const password = document.getElementById('signup-password').value;

        const userData = {
            email: email,
            password: password
        };

        try {
            const response = await fetch('http://localhost:8080/usuario/registro', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            });

            if (response.ok) {
                const responseData = await response.json();
                console.log('Usuário cadastrado com sucesso:', responseData);
                alert('Usuário cadastrado com sucesso!');
                // Redirecionar ou limpar formulário
            } else {
                const errorData = await response.json();
                console.error('Erro ao cadastrar usuário:', errorData);
                alert('Erro ao cadastrar usuário. Tente novamente.');
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Erro na requisição. Tente novamente.');
        }
    });
});