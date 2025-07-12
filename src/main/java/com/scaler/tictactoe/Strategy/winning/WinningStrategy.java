package com.scaler.tictactoe.Strategy.winning;


import com.scaler.tictactoe.Models.Board;
import com.scaler.tictactoe.Models.Move;

public interface WinningStrategy {
    public boolean checkWinner(Move move, Board board);
    public void handleUndo(Move move, Board board);
}