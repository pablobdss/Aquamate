document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('saveField').addEventListener('click', salvarDadosUsuario);
    document.getElementById('tipoMeta-input').addEventListener('change', toggleMeta);
});

function toggleMeta() {
    var metaInput = document.getElementById('metaManual-input');
    var checkbox = document.getElementById('tipoMeta-input');
    metaInput.disabled = checkbox.checked;
    if (checkbox.checked) {
        metaInput.value = '';
    }
}

async function salvarDadosUsuario() {
    const nome = document.getElementById('nome-input').value;
    const peso = document.getElementById('peso-input').value;
    const metaAutomatica = document.getElementById('tipoMeta-input').checked;
    const metaManual = document.getElementById('metaManual-input').value;

    const authToken = localStorage.getItem('authToken');
    if (!authToken) {
        alert('Usuário não autenticado. Por favor, faça login.');
        return;
    }

    const dadosUsuario = {
        nome: nome,
        peso: peso,
        meta: metaAutomatica ? null : metaManual
    };

    const idUsuario = obterIdUsuario();

    if (!idUsuario) {
        alert('ID do usuário não encontrado. Por favor, faça login novamente.');
        return;
    }

    const headers = new Headers({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${authToken}`
    });

    try {
        const response = await fetch(`/dadosUsuario/post?id_usuario=${idUsuario}`, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(dadosUsuario)
        });

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

function obterIdUsuario() {
    return sessionStorage.getItem('userId');
}