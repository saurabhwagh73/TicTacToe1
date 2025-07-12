package com.scaler.tictactoe.Models;

import java.util.Scanner;

public class Player {
    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Long id, String name, Symbol symbol, PlayerType playerType) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Symbol getSymbol() {
        return symbol;
    }
    public PlayerType getPlayerType() {
        return playerType;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setSymbol(char symbol) {
        this.symbol.setaChar(symbol);
    }
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Move makeMove(Board board){
        System.out.println("Please enter the row where you want to make the move(0 based index");
        int row=scanner.nextInt();
        System.out.println("Please enter the column where you want to make the move(0 based index");
        int col=scanner.nextInt();
        return new Move(new Cell(row,col),this);
    }
}
