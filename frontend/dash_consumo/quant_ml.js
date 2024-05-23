let counter = 0;
let meta = document.getElementById('frase-da-meta');

function updateCounterDisplay() {
    document.getElementById('frase-quant').innerText = counter + ' ml';
    const percentage = (counter / meta) * 100;
    document.getElementById('percentage').innerText = percentage.toFixed(2) + ' %';
    document.documentElement.style.setProperty('--water-level', percentage / 100);
}

document.getElementById('frase-da-meta').addEventListener('change', function() {
    meta = parseInt(this.value);
    updateCounterDisplay();
});

document.getElementById('seta2').addEventListener('click', function() {
    if (counter < meta) {
        counter += 100;
        updateCounterDisplay();
    }
});

document.getElementById('seta1').addEventListener('click', function() {
    if (counter > 0) {
        counter -= 100;
        updateCounterDisplay();
    }
});

updateCounterDisplay();

    
