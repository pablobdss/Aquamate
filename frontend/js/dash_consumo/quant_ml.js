let counter = 0;
var meta
function updateCounterDisplay() {
    document.getElementById('frase-quant').innerText = counter + ' ml';
}

document.getElementById('frase-quant').addEventListener('change', function() {
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