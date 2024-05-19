
id_usuario = 1
obterConsumoAtual()
document.addEventListener('DOMContentLoaded', () => {
    obterConsumoAtual();
    obterMeta();
});

function adicionarAgua() {
    const quantidade = document.getElementById("quantidade").value;
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `http://localhost:8080/consumo/atualizar?id_dadosUsuario=${id_dadosUsuario}`;

    const data = {
        quantidadeConsumida: quantidade
    };

    console.log('Enviando solicitação PUT para:', url);
    console.log('Dados a serem enviados:', JSON.stringify(data));

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            console.log('Consumo de água adicionado com sucesso.');
            obterConsumoAtual();
        } else {
            console.error('Erro ao adicionar consumo de água:', response.statusText);
        }
    })
    .catch(error => {
        console.error('Erro ao adicionar consumo de água:', error);
    });
}


function obterConsumoAtual() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `http://localhost:8080/consumo/resgatar?id_dadosUsuario=${id_dadosUsuario}`;

    console.log('Enviando solicitação GET para:', url);

    fetch(url)
    .then(response => {
        if (response.ok) {
            console.log('Resposta recebida com sucesso.');
            return response.json();
        } else {
            throw new Error('Erro ao obter consumo atual de água');
        }
    })
    .then(data => {
        console.log('Dados recebidos:', data);
        const consumoAtualElement = document.getElementById("consumoAtual");
        const quantidadeConsumida = data.quantidadeConsumida;
        const percentualAtingido = data.percentualAtingido;
    
        console.log('Quantidade Consumida:', quantidadeConsumida);
        console.log('Percentual Atingido:', percentualAtingido);
    
        consumoAtualElement.textContent = `Consumo Atual: ${quantidadeConsumida} ml (${percentualAtingido}%)`;
    })
    .catch(error => {
        console.error('Erro ao obter consumo atual de água:', error);
    });
}

function adicionarAgua() {
    const quantidade = document.getElementById("quantidade").value;
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `http://localhost:8080/consumo/atualizar?id_dadosUsuario=${id_dadosUsuario}`;

    const data = {
        quantidadeConsumida: quantidade
    };

    console.log('Enviando solicitação POST para:', url);
    console.log('Dados a serem enviados:', JSON.stringify(data));

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            console.log('Consumo de água adicionado com sucesso.');
            obterConsumoAtual();
        } else {
            console.error('Erro ao adicionar consumo de água:', response.statusText);
        }
    })
    .catch(error => {
        console.error('Erro ao adicionar consumo de água:', error);
    });
}

function obterConsumoAtual() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `http://localhost:8080/consumo/resgatar?id_dadosUsuario=${id_dadosUsuario}`;

    console.log('Enviando solicitação GET para:', url);

    fetch(url)
    .then(response => {
        if (response.ok) {
            console.log('Resposta recebida com sucesso.');
            return response.json();
        } else {
            throw new Error('Erro ao obter consumo atual de água');
        }
    })
    .then(data => {
        console.log('Dados recebidos:', data);
        const consumoAtualElement = document.getElementById("consumoAtual");
        const quantidadeConsumida = data.quantidadeConsumida;
        const percentualAtingido = data.percentualAtingido;

        console.log('Quantidade Consumida:', quantidadeConsumida);
        console.log('Percentual Atingido:', percentualAtingido);

        consumoAtualElement.textContent = `Consumo Atual: ${quantidadeConsumida} ml (${percentualAtingido}%)`;
    })
    .catch(error => {
        console.error('Erro ao obter consumo atual de água:', error);
    });
}

function obterMeta() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `http://localhost:8080/dadosUsuario?id_usuario=${id_usuario}`;

    console.log('Enviando solicitação GET para:', url);

    fetch(url)
    .then(response => {
        if (response.ok) {
            console.log('Resposta recebida com sucesso.');
            return response.json();
        } else {
            throw new Error('Erro ao obter dados do usuário');
        }
    })
    .then(data => {
        console.log('Dados do usuário recebidos:', data);
        const tipoMetaElement = document.getElementById("tipoMeta");
        const metaMlElement = document.getElementById("metaMl");

        const tipoMeta = data.tipoMeta;
        let metaMl;

        if (tipoMeta === "AUTO") {
            const urlMetaAuto = `http://localhost:8080/meta/auto/${id_dadosUsuario}`;
            fetch(urlMetaAuto)
            .then(response => response.json())
            .then(metaData => {
                metaMl = metaData.metaAuto; // Supondo que 'meta' seja a propriedade contendo a meta em mililitros
                tipoMetaElement.textContent = `Tipo de Meta: Automática`;
                metaMlElement.textContent = `Meta em ML: ${metaMl}`;
            })
            .catch(error => console.error('Erro ao obter meta automática:', error));
        } else {
            const urlMetaManual = `http://localhost:8080/meta/manual/${id_dadosUsuario}`;
            fetch(urlMetaManual)
            .then(response => response.json())
            .then(metaData => {
                metaMl = metaData.metaManual; // Supondo que 'meta' seja a propriedade contendo a meta em mililitros
                tipoMetaElement.textContent = `Tipo de Meta: Manual`;
                metaMlElement.textContent = `Meta em ML: ${metaMl}`;
            })
            .catch(error => console.error('Erro ao obter meta manual:', error));
        }
    })
    .catch(error => {
        console.error('Erro ao obter dados do usuário:', error);
    });
}
