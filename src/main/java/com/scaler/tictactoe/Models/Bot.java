package com.scaler.tictactoe.Models;


import com.scaler.tictactoe.Strategy.bot.BotPlayingFactory;
import com.scaler.tictactoe.Strategy.bot.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(Long id,String name, Symbol symbol,BotDifficultyLevel botDifficultyLevel) {
        super(id, name, symbol, PlayerType.Bot);
        this.difficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingFactory
                .getBotPlayingStrategy(difficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);

        return move;
    }
}
