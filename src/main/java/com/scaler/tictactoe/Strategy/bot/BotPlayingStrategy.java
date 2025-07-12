package com.scaler.tictactoe.Strategy.bot;


import com.scaler.tictactoe.Models.Board;
import com.scaler.tictactoe.Models.Move;

public interface BotPlayingStrategy {
    public Move makeMove(Board board);
}