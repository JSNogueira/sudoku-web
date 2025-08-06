async function loadSudoku() {
    try {
        const response = await fetch('http://localhost:8080/api/sudoku/new');
        const board = await response.json();

        const table = document.getElementById('sudoku-board');
        table.innerHTML = ''; // limpa tabuleiro anterior

        for (let i = 0; i < 9; i++) {
            const row = document.createElement('tr');
            for (let j = 0; j < 9; j++) {
                const cell = document.createElement('td');

                if (board[i][j] === 0) {
                    const input = document.createElement('input');
                    input.type = 'number';
                    input.min = 1;
                    input.max = 9;
                    cell.appendChild(input);
                } else {
                    cell.textContent = board[i][j];
                }

                row.appendChild(cell);
            }
            table.appendChild(row);
        }
    } catch (error) {
        console.error('Erro ao carregar o Sudoku:', error);
    }
}

window.onload = loadSudoku;