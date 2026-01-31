package com.scaler.tictactoe.Models;

import com.scaler.tictactoe.Strategy.winning.WinningStrategy;
import com.scaler.tictactoe.Exceptions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private List<Player> players;
    private Board board;
    private Player winner;
    private List<Move> moves;
    private int nextPlayerIndex;
    private GameState gameState;
    private int dimension;
    private List<WinningStrategy> winningStrategies;

    public Game(List<Player> players,
                int dimension,
                List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.gameState = GameState.IN_PROGRESS;
        this.dimension = dimension;
        this.winningStrategies = winningStrategies;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        public List<Player> getPlayers() {
            return players;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addPlayer(Player player) {
            this.players.add(player);
            return this;
        }

        public int getDimension() {
            return dimension;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public List<WinningStrategy> getWinningStrategies() {
            return winningStrategies;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws PlayerCountException, BotCountException, uniqueSymbolException {
            GameValidate isvalidGame=new GameValidate(dimension,players);
            isvalidGame.validate();

            return new Game(players, dimension, winningStrategies);
        }
    }

    public void printBoard() {
        board.printBoard();
    }

    public void makeMove() {
        Player currentMovePlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + currentMovePlayer.getName() + " turn. Please make your move.");

        Move move = currentMovePlayer.makeMove(board);

        MoveValidate moveValidate=new MoveValidate(board);
        if(!moveValidate.validateMove(move)) {
            System.out.println("Invalid move. Please try again.");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setCellState(CellState.Filled);
        cellToChange.setPlayer(currentMovePlayer);

        Move finalMoveObj = new Move(cellToChange, currentMovePlayer);
        moves.add(finalMoveObj);

        nextPlayerIndex += 1;
        nextPlayerIndex %= players.size();

        if(checkWinner(move)) {
            gameState = GameState.WIN;
            winner = currentMovePlayer;
        } else if(moves.size() == board.getSize() * board.getSize()) {
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy: winningStrategies) {
            if(winningStrategy.checkWinner(move, board)) {
                return true;
            }
        }

        return false;
    }

    public void undo() {
        if(moves.size() == 0) {
            System.out.println("No moves to undo");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(lastMove);

        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(CellState.Empty);

        for(WinningStrategy winningStrategy: winningStrategies) {
            winningStrategy.handleUndo(lastMove, board);
        }

        nextPlayerIndex -= 1;
        nextPlayerIndex = (nextPlayerIndex + players.size()) % players.size();
    }
}
