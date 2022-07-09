pub fn solve(board: &mut Vec<u32>){
    let index = board.iter().position(|&x| x == 0).unwrap();
    let successors = get_successors(&board, index);
    rec_solve(board, successors, index);
}

fn rec_solve(board: &mut Vec<u32>, successors: Vec<u32>, index: usize) -> &mut Vec<u32>{
    for successor in successors {
        board[index] = successor;
        let new_index = board.iter().position(|&x| x == 0);
        if new_index.is_none() {
            return board;
        }
        let new_index = new_index.unwrap();
        let new_successors = get_successors(board, new_index);
        let result = rec_solve(board, new_successors, new_index);
        if result[new_index] != 0 {
            return board;
        }
    }
    board[index] = 0;
    return board;
}


fn get_successors(board: &Vec<u32>, index: usize) -> Vec<u32> {
    let mut row_x: [u32; 9] = [0; 9];
    let mut col_y: [u32; 9] = [0; 9];
    let mut block: [u32; 9] = [0; 9];
    let x = index % 9;
    let y = index / 9;
    for i in 0..9 {
        row_x[i] = board[i * 9 + x];
        col_y[i] = board[y * 9 + i];
    }
    for i in 0..3 {
        for j in 0..3 {
            block[i * 3 + j] = board[(y / 3 * 3 + i) * 9 + (x / 3 * 3 + j)];
        }
    }
    let mut successors = Vec::new();
    for i in 1..10 {
        if !row_x.contains(&i) && !col_y.contains(&i) && !block.contains(&i) {
            successors.push(i);
        }
    }
    successors
}