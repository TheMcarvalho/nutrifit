let contadorItens = 0;

function adicionarItem() {
    const container = document.getElementById('itens-container');
    const index = contadorItens++;

    const div = document.createElement('div');
    div.className = 'grid grid-cols-5 gap-3 mb-3 item-ficha';
    div.innerHTML = `
        <select name="itensFicha[${index}].exercicio.id"
                class="px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary-400">
            ${getOpcoesExercicios()}
        </select>
        <input type="number" name="itensFicha[${index}].series" placeholder="Séries"
               class="px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary-400">
        <input type="number" name="itensFicha[${index}].repeticoes" placeholder="Repetições"
               class="px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary-400">
        <input type="number" step="0.1" name="itensFicha[${index}].carga" placeholder="Carga (kg)"
               class="px-3 py-2 border border-gray-300 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary-400">
        <button type="button" onclick="this.closest('.item-ficha').remove()"
                class="text-red-500 hover:text-red-700 text-sm font-medium">
            Remover
        </button>
    `;
    container.appendChild(div);
}

function getOpcoesExercicios() {
    const select = document.getElementById('exercicios-ref');
    if (!select) return '<option value="">Selecione</option>';
    return select.innerHTML;
}
