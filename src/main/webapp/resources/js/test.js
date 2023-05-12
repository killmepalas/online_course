var check = document.getElementById('check'),
    count = document.getElementById('count');

function changeCount() {
    if (check.checked) {
        count.style.display = 'block';
    } else
        count.style.display = 'none';
}

changeCount();