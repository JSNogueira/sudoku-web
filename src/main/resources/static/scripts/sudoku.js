async function loadSudoku() {
    try {
        const response = await fetch('https://web-production-4c280.up.railway.app/api/sudoku/new');
        const board = await response.json();

        const table = document.getElementById('sudoku-board');
        table.innerHTML = ''; // limpa tabuleiro anterior

        for (let i = 0; i < 9; i++) {
            const row = document.createElement('tr');
            for (let j = 0; j < 9; j++) {
                const cell = document.createElement('td');
                cell.id = `td${i}${j}`

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

//window.onload = loadSudoku;

function enviarTabuleiro() {
    const board = [];

    for (let i = 0; i < 9; i++) {
        const row = [];
        for (let j = 0; j < 9; j++) {
            const cell = document.getElementById(`td${i}${j}`);
            const input = cell.querySelector("input"); // pega o input, se existir

            let value;
            if (input) {
                value = input.value ? parseInt(input.value) : 0;
            } else {
                value = cell.textContent ? parseInt(cell.textContent) : 0;
            }

            row.push(value);
        }
        board.push(row);
    }

    fetch("https://web-production-4c280.up.railway.app/api/sudoku/validar", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(board),
    })
    .then((res) => res.json())
    .then((data) => {
      if(JSON.stringify(data).includes("Tabuleiro válido")){
        alert("O tabuleiro é válido, mas ainda está incompleto.");
      } else if(JSON.stringify(data).includes("Tabuleiro completo")){
        alert("Você concluiu o jogo, parabéns!");
      } else {
        alert("O tabuleiro é inválido!");
      }
    })
    .catch((err) => {
        alert("Erro ao enviar tabuleiro: " + err);
        console.error("Erro ao enviar tabuleiro:", err);
    });
}