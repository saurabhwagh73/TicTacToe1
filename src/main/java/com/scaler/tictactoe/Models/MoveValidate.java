package com.scaler.tictactoe.Models;

public class MoveValidate {
    private Board board;

    public MoveValidate(Board board){
        this.board=board;
    }
    public boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row >= board.getSize() || col >= board.getSize()) {
            return false;
        }

        if (!board.getBoard().get(row).get(col).getCellState().equals(CellState.Empty)) {
            return false;
        }

        return true;
    }
}
