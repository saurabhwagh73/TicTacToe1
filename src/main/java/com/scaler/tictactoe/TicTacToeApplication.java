package com.scaler.tictactoe;

import com.scaler.tictactoe.Controller.GameController;
import com.scaler.tictactoe.Exceptions.BotCountException;
import com.scaler.tictactoe.Exceptions.PlayerCountException;
import com.scaler.tictactoe.Exceptions.uniqueSymbolException;
import com.scaler.tictactoe.Models.*;
import com.scaler.tictactoe.Strategy.winning.ColWinningStrategy;
import com.scaler.tictactoe.Strategy.winning.DiagonalWinningStrategy;
import com.scaler.tictactoe.Strategy.winning.RowWinningStrategy;
import com.scaler.tictactoe.Strategy.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class TicTacToeApplication {

    public static void main(String[] args) throws PlayerCountException, BotCountException, uniqueSymbolException {
        GameController gameController = new GameController();
        Scanner scanner = new Scanner(System.in);

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(
                new Player(1L , "Saurabh", new Symbol('X'),
                        PlayerType.Human)
        );

        players.add(
                new Bot(2L,
                        "Rohit",
                        new Symbol('O'),
                        BotDifficultyLevel.MEDIUM)
        );

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        Game game = gameController.startGame(
                players,
                winningStrategies,
                dimension);

        while(gameController.checkGameStatus(game).equals(GameState.IN_PROGRESS)) {
            gameController.printBoard(game);
            System.out.println("Do you want to undo? (y/n)");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("y")) {
                gameController.undo(game);
                continue;
            }
            gameController.makeMove(game);
        }

        System.out.println("Game  has ended");
        gameController.printBoard(game);

        if(gameController.checkGameStatus(game).equals(GameState.WIN)) {
            Player player = gameController.getWinner(game);
            System.out.println("Winner is: " + player.getName());
        } else {
            System.out.println("Game has drawn");
        }

    }

}
