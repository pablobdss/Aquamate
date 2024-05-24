import { obterMetaAutomatica, obterMetaManual } from "./conn/metas.js";
import { getDadosUsuario } from "./conn/dadosUsuario.js";
document.addEventListener('DOMContentLoaded', (event) => {
    const elements = document.querySelectorAll('.shrink');
    let interval;

    elements.forEach(element => {
        let scale = 1;

        const shrink = () => {
            clearInterval(interval);
            interval = setInterval(() => {
                if (scale > 0.95) {
                    scale -= 0.005;
                    element.style.transform = `scale(${scale})`;
                } else {
                    clearInterval(interval);
                }
            }, 16); 
        };

        const grow = () => {
            clearInterval(interval);
            interval = setInterval(() => {
                if (scale < 1) {
                    scale += 0.01;
                    element.style.transform = `scale(${scale})`;
                } else {
                    clearInterval(interval);
                }
            }, 16);
        };

        element.addEventListener('mouseover', shrink);
        element.addEventListener('mouseout', grow);
    });

    function verificarLogin() {
        const authToken = localStorage.getItem('authToken');
        console.log('AuthToken:', authToken);
        return authToken !== null;
    }

    if (verificarLogin()) {
        console.log('Usuário está logado');
        const logoutLink = document.getElementById('link2');
        if (logoutLink) {
            logoutLink.addEventListener('click', function(event) {
                event.preventDefault();
                console.log('Link de logout clicado');
                fetch('http://ec2-18-230-228-181.sa-east-1.compute.amazonaws.com:8080/usuario/logout', {
                    method: 'POST',
                    credentials: 'include'
                })
                .then(response => {
                    if (response.ok) {
                        console.log('Logout bem-sucedido');
                        localStorage.removeItem('authToken');
                        window.location.href = '/frontend/pages/login-register/login-register.html';
                    } else {
                        console.error('Erro ao fazer logout', response.status, response.statusText);
                    }
                })
                .catch(error => {
                    console.error('Erro na requisição de logout:', error);
                });
            });
        } else {
            console.error('Link de logout não encontrado no DOM');
        }
    } else {
        console.log('Usuário não está logado');
    }

    obterMeta()
});

async function obterMeta() {
    const id_dadosUsuario = 1; // Substitua 1 pelo ID do usuário correto

    try {
        const dadosUsuario = await getDadosUsuario(id_dadosUsuario); // Chama a função para obter os dados do usuário
        
        console.log('Dados do usuário recebidos:', dadosUsuario);
        const metaMlElement = document.getElementById("frase-da-meta");
        const tipoMeta = dadosUsuario.tipoMeta;
        const fraseMeta = dadosUsuario.apelido;

        // Atualizar o conteúdo do elemento com o id 'frase' para mostrar a mensagem com o apelido
        document.getElementById('frase').innerHTML = `hora de beber<br>água, ${fraseMeta}`;
        if (tipoMeta) {
            // Se o tipo de meta for verdadeiro (automática), obtenha a meta automática
            const metaAutomatica = await obterMetaAutomatica(id_dadosUsuario);
            const valorMetaAutomatica = metaAutomatica.metaAutomatica;
            metaMlElement.innerText = `${valorMetaAutomatica} ML`;
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
