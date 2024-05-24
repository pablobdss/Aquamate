import getConsumo from "JS/conn/consumo.js";
function obterConsumoAtual() {
    getConsumo()
        .then(data => {
            console.log('Dados recebidos:', data);
            const percentualAtingido = data.percentualAtingido;
            console.log('Percentual Atingido:', percentualAtingido);

            // Atualiza interface
            const consumoAtualElement = document.getElementById("consumoAtual");
            consumoAtualElement.textContent = `Percentual Atingido: ${percentualAtingido}%`;

            // Converte pra fração decimal
            const waterLevel = percentualAtingido / 100;
            
            // Atualiza o estilo da água
            document.querySelector('.water').style.setProperty('--water-level', waterLevel);
        })
        .catch(error => {
            console.error('Erro ao obter consumo atual de água:', error);
        });
}

// Chama a função pra carregar a página
window.onload = obterConsumoAtual;
