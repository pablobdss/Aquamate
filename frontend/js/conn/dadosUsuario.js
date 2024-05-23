import { getIdUsuarioFromSessionStorage } from './usuario.js';

// Obtendo o ID do usuário armazenado em sessionStorage
const idUsuario = getIdUsuarioFromSessionStorage();
export async function getDadosUsuario() {
    const url = `http://localhost:8080/dadosUsuario?id_usuario=${idUsuario}`;
    console.log(`Enviando solicitação para ${url}`);

    try {
        const response = await fetch(url);
        if (response.ok) {
            const responseData = await response.json();
            console.log('Dados recebidos do servidor:', responseData);
            return responseData;
        } else {
            console.error('Erro ao buscar dados do usuário:', response.statusText);
            return null;
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        return null;
    }
}

export async function atualizarDadosUsuario(dadosAtualizados) {
    const url = `http://localhost:8080/dadosUsuario/atualizar?id_usuario=${idUsuario}`;
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
        console.error('Erro na requisição:', error);
    }
}
