import { getDadosUsuario, atualizarDadosUsuario } from "./conn/dadosUsuario.js";
// import { atualizarConsumo } from "./conn/consumo.js";
import { atualizarMetaManual } from "./conn/metas.js";

document.addEventListener('DOMContentLoaded', async function() {
    // Verifica se id_dadosUsuario está na sessionStorage
    const id_dadosUsuario = 1;
    const id_usuario = 1
    if (!id_dadosUsuario) {
        console.error('ID do usuário não encontrado na sessionStorage.');
        return;
    }

    // Função para mostrar os dados do usuário
    function mostrarDadosUsuario(dadosUsuario) {
        console.log('Dados do usuário para mostrar:', dadosUsuario);

        document.getElementById('nome-input').value = dadosUsuario.apelido;
        document.getElementById('peso-input').value = dadosUsuario.peso;
        document.getElementById('metaManual-input').innerText = dadosUsuario.tipoMeta ? "Automática" : "Manual";
        document.getElementById('tipoMeta-input').checked = dadosUsuario.tipoMeta;
    }

    // Função para enviar atualizações do usuário
    async function enviarAtualizacao() {
        const apelido = document.getElementById('nome-input').value;
        const peso = document.getElementById('peso-input').value;
        const tipoMeta = document.getElementById('tipoMeta-input').checked;

        const dadosAtualizados = {
            id: id_dadosUsuario, // Corrigido para usar o id_dadosUsuario obtido da sessionStorage
            apelido,
            peso,
            tipoMeta
        };
        await atualizarDadosUsuario(dadosAtualizados);
    }

    // Função para salvar a meta manual
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

    // Obtém os dados do usuário e os mostra na página
    const dadosUsuario = await getDadosUsuario(id_dadosUsuario);
    if (dadosUsuario) {
        mostrarDadosUsuario(dadosUsuario);
    }

    // Event listener para o botão de salvar
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