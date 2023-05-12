var active = document.getElementById('active'),
    over = document.getElementById('over'),
    not_begin = document.getElementById('not_begin'),
    active_bar = document.getElementById('1'),
    over_bar = document.getElementById('2'),
    not_begin_bar = document.getElementById('3');

function init(){
    changeBar(0);
}

function changeBar(num){
    let bars = [active_bar,over_bar,not_begin_bar];
    let divs = [active,over,not_begin];

    for (let i = 0; i<3;i++){
        if (i !== num){
            bars[i].className = "no";
            divs[i].style.display = 'none';
        } else {
            bars[i].className = "active";
            divs[i].style.display = 'block';
        }
    }
}

init();