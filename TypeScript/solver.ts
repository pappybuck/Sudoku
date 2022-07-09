export function solve(board: Number[]): Number[]{
    let index = board.findIndex( (element) => element === 0 );
    if (index === -1) {
        return board;
    }
    let successors = getSuccessors(board, index);
    return recSolve(board, successors, index);
}

function recSolve(board: Number[], successors: Number[], index: number): Number[] {
    let successor:Number;
    for (successor of successors) {
        board[index] = successor;
        let new_index = board.findIndex( (element) => element === 0 );
        if (new_index === -1) {
            return board;
        }
        let new_successors = getSuccessors(board, new_index);
        let result = recSolve(board, new_successors, new_index);
        if (result[new_index] !== 0) {
            return result;
        }
    }
    board[index] = 0;
    return board;
}

function getSuccessors(board: Number[], index: number): Number[]{
    let row_x: Number[] = [];
    let col_y: Number[] = [];
    let block: Number[] = [];
    let x:number = index % 9;
    let y:number = Math.floor(index / 9);
    for (let i = 0; i < 9; i++) {
        row_x.push(board[i * 9 + x]);
        col_y.push(board[y * 9 + i]);
    }
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            block.push(board[(y - y % 3 + i) * 9 + (x - x % 3 + j)]);
        }
    }
    let successors: Number[] = [];
    for (let i = 1; i <= 9; i++) {
        if (row_x.indexOf(i) === -1 && col_y.indexOf(i) === -1 && block.indexOf(i) === -1) {
            successors.push(i);
        }
    }
    return successors;
}