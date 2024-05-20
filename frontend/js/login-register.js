document.addEventListener('DOMContentLoaded', function() {
    const signUpForm = document.querySelector('.sign-up-form'); // Corrigido para selecionar corretamente o formulário de inscrição

    signUpForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const email = document.querySelector('.sign-up-form input[type="text"]').value; // Corrigido para selecionar corretamente o campo de email
        const password = document.querySelector('.sign-up-form input[type="password"]').value; // Corrigido para selecionar corretamente o campo de senha

        const userData = {
            email: email,
            senha: password
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
                const responseText = await response.text(); // Mudança aqui para lidar com a resposta de texto
                console.log('Usuário cadastrado com sucesso:', responseText);
                alert('Usuário cadastrado com sucesso!');
                // Redirecionar para a página de complementação de registro
                window.location.href = '/frontend/pages/reg-complement/reg-complement.html'; // Caminho ajustado
            } else {
                const errorText = await response.text(); // Mudança aqui para lidar com a resposta de texto
                console.error('Erro ao cadastrar usuário:', errorText);
                alert('Erro ao cadastrar usuário. Tente novamente.');
            }
        } catch (error) {
            console.error('Erro na requisição:', error);
            alert('Erro na requisição. Tente novamente.');
        }
    });
});
