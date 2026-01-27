package com.scaler.tictactoe.Controller;


import com.scaler.tictactoe.Exceptions.BotCountException;
import com.scaler.tictactoe.Exceptions.PlayerCountException;
import com.scaler.tictactoe.Exceptions.uniqueSymbolException;
import com.scaler.tictactoe.Models.Game;
import com.scaler.tictactoe.Models.GameState;
import com.scaler.tictactoe.Models.Player;
import com.scaler.tictactoe.Strategy.winning.WinningStrategy;

import java.util.List;
public class GameController {

    public Game startGame(List<Player> players,
                          List<WinningStrategy> winningStrategies,
                          int dimension) throws PlayerCountException, BotCountException, uniqueSymbolException {

        return Game
                .getBuilder()
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setDimension(dimension)
                .build();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void printBoard(Game game) {
        game.printBoard();
    }

    public GameState checkGameStatus(Game game) {
        return game.getGameState();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void undo(Game game) {
        game.undo();
    }
}
