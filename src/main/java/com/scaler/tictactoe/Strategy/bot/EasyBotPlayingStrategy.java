package com.scaler.tictactoe.Strategy.bot;


import com.scaler.tictactoe.Models.Board;
import com.scaler.tictactoe.Models.Cell;
import com.scaler.tictactoe.Models.Move;
import com.scaler.tictactoe.Models.CellState;
import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> cells: board.getBoard()) {
            for(Cell cell: cells) {
                if(cell.getCellState().equals(CellState.Empty)) {
                    return new Move(cell, null);
                }
            }
        }

        return null;
    }
}