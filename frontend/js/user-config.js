import {getDadosUsuario, atualizarDadosUsuario} from "./conn/dadosUsuario.js"
// import {atualizarConsumo} from "./conn/consumo.js"
import {atualizarMetaManual} from "./conn/metas.js";

var id_dadosUsuario = 1;
function mostrarDadosUsuario(dadosUsuario) {
    console.log('Dados do usuário para mostrar:', dadosUsuario);

    // document.getElementById('nome').innerText = `${dadosUsuario.apelido}`;
    document.getElementById('nome-input').value = dadosUsuario.apelido;

    // document.getElementById('peso').innerText = `${dadosUsuario.peso}`;
    document.getElementById('peso-input').value = dadosUsuario.peso;

    document.getElementById('metaManual-input').innerText = dadosUsuario.tipoMeta ? "Automática" : "Manual";
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
async function salvarMetaManual() {
    const id_dadosUsuario = 1
    const novaMetaManual = document.getElementById('metaManual-input').value;
    
    try {
        const sucesso = await atualizarMetaManual(id_dadosUsuario, novaMetaManual);
        if (sucesso) {
            console.log('Passei JS');
        } else {
            console.error('Erro ao atualizar meta manual.');
        }
    } catch (error) {
        console.error('Erro ao atualizar meta manual:', error);
    }
}

document.addEventListener('DOMContentLoaded', async function() {
    const dadosUsuario = await getDadosUsuario(id_dadosUsuario);
    if (dadosUsuario) {
        mostrarDadosUsuario(dadosUsuario);
    }

    document.getElementById('saveField').addEventListener('click', async function() {
        await enviarAtualizacao();
        await salvarMetaManual();
    });
});

// function adicionarAgua() {
//     const quantidade = document.getElementById("metaManual-input").value;
//     const data = {
//         quantidadeConsumida: quantidade
//     };

//     // Chama a função atualizarConsumo() passando os dados
//     atualizarConsumo(data)
//         .then(response => {
//             if (response.ok) {
//                 console.log('Consumo de água adicionado com sucesso.');
//                 obterConsumoAtual();
//             } else {
//                 console.error('Erro ao adicionar consumo de água:', response.statusText);
//             }
//         })
//         .catch(error => {
//             console.error('Erro ao adicionar consumo de água:', error);
//         });
// }