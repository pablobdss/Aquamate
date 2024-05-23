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
});


