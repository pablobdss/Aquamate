export default async function getConsumo() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto
    const url = `ec2-18-230-228-181.sa-east-1.compute.amazonaws.com:8080/consumo/resgatar?id_dadosUsuario=${id_dadosUsuario}`;

    return fetch(url)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erro ao obter consumo atual de água');
            }
        });
}

export function atualizarConsumo(data) {
    const id_dadosUsuario = 1;
    const url = `http://localhost:8080/consumo/atualizar?id_dadosUsuario=${id_dadosUsuario}`;

    console.log('Enviando solicitação PUT para:', url);
    console.log('Dados a serem enviados:', JSON.stringify(data));

    return fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}
