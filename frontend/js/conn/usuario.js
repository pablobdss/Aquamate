// usuario.js

// Função para buscar o ID do usuário pelo e-mail e armazenar em sessionStorage
async function getIdUsuarioAndStoreByEmail(email) {
    try {
        const response = await fetch(`http://ec2-18-230-228-181.sa-east-1.compute.amazonaws.com:8080/dadosusuario/id-usuario?email=${encodeURIComponent(email)}`);
        if (response.ok) {
            const data = await response.json();
            if (data && data.length > 0) {
                const idUsuario = data[0]; // Supondo que a resposta seja uma lista de IDs e queremos apenas o primeiro
                sessionStorage.setItem('idUsuario', idUsuario);
                return idUsuario;
            } else {
                throw new Error('Nenhum ID de usuário encontrado para o e-mail fornecido.');
            }
        } else {
            throw new Error('Erro ao buscar o ID do usuário.');
        }
    } catch (error) {
        console.error('Erro na requisição:', error);
        return null;
    }
}

// Função para obter o ID do usuário armazenado em sessionStorage
function getIdUsuarioFromSessionStorage() {
    return sessionStorage.getItem('id_usuario');
}

// Exportando as funções para uso em outros scripts
export { getIdUsuarioAndStoreByEmail, getIdUsuarioFromSessionStorage };
