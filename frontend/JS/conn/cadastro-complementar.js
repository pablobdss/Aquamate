// No arquivo regcomplement.js

document.addEventListener('DOMContentLoaded', function() {
    // Adicione um evento de clique ao botão de salvar
    document.getElementById('saveField').addEventListener('click', salvarDadosUsuario);
    document.getElementById('tipoMeta-input').addEventListener('change', toggleMeta);
});

// Função para salvar os dados do usuário
async function salvarDadosUsuario() {
    // Obtenha os valores dos campos do formulário
    const nome = document.getElementById('nome-input').value;
    const peso = document.getElementById('peso-input').value;
    const metaAutomatica = document.getElementById('tipoMeta-input').checked;
    const metaManual = document.getElementById('metaManual-input').value;

    // Obtenha o token de autenticação do armazenamento local
    const authToken = localStorage.getItem('authToken');
    if (!authToken) {
        alert('Usuário não autenticado. Por favor, faça login.');
        return;
    }

    // Crie um objeto com os dados do usuário
    const dadosUsuario = {
        nome: nome,
        peso: peso,
        meta: metaAutomatica ? null : metaManual
    };

    // Obtenha o ID do usuário do armazenamento de sessão
    const id_usuario = sessionStorage.getItem('idUsuario');
    if (!id_usuario) {
        alert('ID do usuário não encontrado. Por favor, faça login novamente.');
        return;
    }

    // Configure os cabeçalhos da solicitação com o token de autenticação
    const headers = new Headers({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`
    });

    try {
        // Envie uma solicitação POST para salvar os dados do usuário
        const response = await fetch(`/dadosUsuario/post?id_usuario=${idUsuario}`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(dadosUsuario)
        });

        // Verifique se a resposta foi bem-sucedida
        if (response.ok) {
            const dadosSalvos = await response.json();
            alert('Dados salvos com sucesso!');
            console.log(dadosSalvos);
        } else {
            alert('Erro ao salvar os dados.');
            console.error('Erro:', response.statusText);
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao conectar com o servidor.');
    }
}

// Função para alternar a entrada de meta entre manual e automática
function toggleMeta() {
    var metaInput = document.getElementById('metaManual-input');
    var checkbox = document.getElementById('tipoMeta-input');
    metaInput.disabled = checkbox.checked;
    if (checkbox.checked) {
        metaInput.value = '';
    }
}