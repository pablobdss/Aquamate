
export async function obterMetaAutomatica(id_dadosUsuario) {
    const url = `http://localhost:8080/meta/auto/${id_dadosUsuario}`;
    console.log('Enviando solicitação GET para meta automática:', url);
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Erro ao obter meta automática');
    }
    const data = await response.json();
    return data; // Retorna apenas o valor da meta automática
}

export async function obterMetaManual(id_dadosUsuario) {
    const url = `http://localhost:8080/meta/manual/${id_dadosUsuario}`;
    console.log('Enviando solicitação GET para meta manual:', url);
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error('Erro ao obter meta manual');
    }
    const data = await response.json();
    return data;
}

export async function atualizarMetaManual(id_dadosUsuario, novaMetaManual) {

    const dadosAtualizados = {
        metaManual: novaMetaManual
    };
    try {
        const response = await fetch(`http://localhost:8080/meta/manual/${id_dadosUsuario}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dadosAtualizados)
        });
        if (response.ok) {
            return true; // Indica que a atualização foi bem-sucedida
        } else {
            return false; // Indica que houve um erro na atualização
        }
    } catch (error) {
        console.error('Erro na requisição: ', error);
        return false; // Indica que houve um erro na requisição
    }
}

