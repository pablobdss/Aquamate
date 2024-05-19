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
    console.log('JSON enviado:', JSON.stringify({ ...dadosAtualizados}));

    try {
        const response = await fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ ...dadosAtualizados})
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
    console.log('Dados do usuário:', dadosUsuario.id);
    
    document.getElementById('email').innerText = dadosUsuario.usuario.email;
    document.getElementById('email-input').value = dadosUsuario.usuario.email;

    document.getElementById('dataNascimento').innerText = dadosUsuario.dataNascimento;
    document.getElementById('dataNascimento-input').value = dadosUsuario.dataNascimento;

    document.getElementById('apelido').innerText = dadosUsuario.apelido;
    document.getElementById('apelido-input').value = dadosUsuario.apelido;

    document.getElementById('peso').innerText = dadosUsuario.peso;
    document.getElementById('peso-input').value = dadosUsuario.peso;

    document.getElementById('idade').innerText = dadosUsuario.idade;
    document.getElementById('idade-input').value = dadosUsuario.idade;

    document.getElementById('altura').innerText = dadosUsuario.altura;
    document.getElementById('altura-input').value = dadosUsuario.altura;
    
    document.getElementById('tipoMeta').innerText = dadosUsuario.tipoMeta ? "Automática" : "Manual";
    document.getElementById('tipoMeta-input').checked = dadosUsuario.tipoMeta;

}

async function enviarAtualizacao(dadosUsuario) {
    console.log('Dados do usuário:', dadosUsuario.id);
    const id =  id_usuario;
    const email = document.getElementById('email-input').value;
    const dataNascimento = document.getElementById('dataNascimento-input').value;
    const apelido = document.getElementById('apelido-input').value;
    const peso = document.getElementById('peso-input').value;
    const idade = document.getElementById('idade-input').value;
    const altura = document.getElementById('altura-input').value;
    const tipoMeta = document.getElementById('tipoMeta-input').checked;

    const dadosAtualizados = {
        id,
        email,
        dataNascimento,
        apelido,
        peso,
        idade,
        altura,
        tipoMeta
    };

    atualizarDadosUsuario(dadosAtualizados);
}

function toggleEditField(fieldId) {
    const container = document.getElementById(`${fieldId}-container`);
    container.classList.toggle('editable');
    updateButtonVisibility(container);
}

function updateButtonVisibility(container) {
    const editBtn = container.querySelector('.edit-btn');
    const saveBtn = container.querySelector('.save-btn');
    if (container.classList.contains('editable')) {
        editBtn.style.display = 'none';
        saveBtn.style.display = 'inline';
    } else {
        editBtn.style.display = 'inline';
        saveBtn.style.display = 'none';
    }
}

function saveField(fieldId) {
    const container = document.getElementById(`${fieldId}-container`);
    const input = container.querySelector(`#${fieldId}-input`);
    const p = container.querySelector(`#${fieldId}`);
    if (fieldId === 'tipoMeta') {
        p.innerText = input.checked ? "Automática" : "Manual";
    } else {
        p.innerText = input.value;
    }
    toggleEditField(fieldId);
}

document.addEventListener('DOMContentLoaded', async function() {
    const dadosUsuario = await getDadosUsuario(id_usuario);
    if (dadosUsuario) {
        mostrarDadosUsuario(dadosUsuario);
    }

    const saveBtns = document.querySelectorAll('.save-btn');
    saveBtns.forEach(saveBtn => {
        saveBtn.addEventListener('click', enviarAtualizacao);
    });
});



