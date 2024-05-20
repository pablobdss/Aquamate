const btnEsqueceuSenha = document.querySelector('.esqueceu-senha');
const container = document.querySelector('.container');
const overlay = document.querySelector('.overlay');

btnEsqueceuSenha.addEventListener('click', () => {
    container.style.display = 'block';
    overlay.style.display = 'block';
});

overlay.addEventListener('click', () => {
    container.style.display = 'none';
    overlay.style.display = 'none';
});
