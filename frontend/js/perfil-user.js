import {getDadosUsuario} from "./conn/dadosUsuario.js";
import {obterMetaAutomatica, obterMetaManual} from "./conn/metas.js";

var id_usuario = 1;
function mostrarDadosUsuario(response) {
    console.log('Dados do usuário para mostrar:', response);

    const { apelido, peso, tipoMeta } = response;
    document.getElementById('nome').innerText = `Nome: ${apelido}`;
    document.getElementById('peso').innerText = `Peso: ${peso}`;
    document.getElementById('tipoMeta').innerText = tipoMeta ? "Meta: Automática" : "Meta: Manual";
}

document.addEventListener('DOMContentLoaded', async function() {
    const dadosUsuario = await getDadosUsuario(id_usuario);
    mostrarDadosUsuario(dadosUsuario);
    obterMeta()
});

async function obterMeta() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto

    try {
        const dadosUsuario = await getDadosUsuario(id_dadosUsuario); // Chama a função para obter os dados do usuário
        console.log('Dados do usuário recebidos:', dadosUsuario);
        const metaMlElement = document.getElementById("metaMl");

        const tipoMeta = dadosUsuario.tipoMeta;

        if (tipoMeta) {
            // Se o tipo de meta for verdadeiro (automática), obtenha a meta automática
            const metaAutomatica = await obterMetaAutomatica(id_dadosUsuario);
            const valorMetaAutomatica = metaAutomatica.metaAutomatica;
            metaMlElement.innerText = `Meta ML: ${valorMetaAutomatica}`;
        } else {
            // Se o tipo de meta for falso (manual), obtenha a meta manual
            const metaManual = await obterMetaManual(id_dadosUsuario);
            const valorMetaManual = metaManual.metaManual;
            metaMlElement.innerText = `Meta ML: ${valorMetaManual}`;
        }
    } catch (error) {
        console.error('Erro ao obter dados do usuário:', error);
    }
}
