var id_usuario = 1;

async function getDadosUsuario(id_usuario) {
    const url = `http://localhost:8080/dadosUsuario?id_usuario=${id_usuario}`;

    try {
        const response = await fetch(url);
        if (response.ok) {
            const responseData = await response.json();
            console.log('Dados do usuário:', responseData);
            return responseData;
        } else {
            console.error('Erro ao buscar dados do usuário:', response.statusText);
        }
    } catch (error) {
        console.error('Erro na requisição: ', error);
    }
}

async function atualizarDadosUsuario(dadosAtualizados) {
    const url = `http://localhost:8080/dadosUsuario/atualizar?id_usuario=${id_usuario}`;
    console.log('JSON enviado:', JSON.stringify(dadosAtualizados));

    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosAtualizados)
        });

        if (response.ok) {
            console.log('Dados do usuário atualizados com sucesso.');
        } else {
            console.error('Erro ao atualizar dados do usuário:', response.statusText);
        }
    } catch (error) {
        console.error('Erro na requisição: ', error);
    }
}

function mostrarDadosUsuario(dadosUsuario) {
    console.log('Dados do usuário para mostrar:', dadosUsuario);

    document.getElementById('nome').innerText = `Nome: ${dadosUsuario.apelido}`;

    document.getElementById('peso').innerText = `Peso: ${dadosUsuario.peso}`;

    document.getElementById('tipoMeta').innerText = dadosUsuario.tipoMeta ? "Meta: Automática" : "Meta: Manual";
    
    document.getElementById('nome-input').value = dadosUsuario.apelido;

    document.getElementById('peso-input').value = dadosUsuario.peso;
    
    document.getElementById('tipoMeta-input').checked = dadosUsuario.tipoMeta;
}

async function enviarAtualizacao() {
    const id = 1
    const apelido = document.getElementById('nome-input').value;
    const peso = document.getElementById('peso-input').value;
    const tipoMeta = document.getElementById('tipoMeta-input').checked;

    const dadosAtualizados = {
        id,
        apelido,
        peso,
        tipoMeta
    };

    await atualizarDadosUsuario(dadosAtualizados);
}

document.addEventListener('DOMContentLoaded', async function() {
    const dadosUsuario = await getDadosUsuario(id_usuario);
    if (dadosUsuario) {
        mostrarDadosUsuario(dadosUsuario);
    }

    document.getElementById('saveField').addEventListener('click', enviarAtualizacao);
});
