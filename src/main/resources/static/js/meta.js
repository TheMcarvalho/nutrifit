function verificarMeta() {
    var tipoMeta = document.getElementById('tipoMeta').value;
    var pesoAlvo = document.getElementById('pesoAlvo');
    if (tipoMeta === 'EMAGRECER') {
        pesoAlvo.disabled = false;
    } else {
        pesoAlvo.disabled = true;
        pesoAlvo.value = '';
    }
}
