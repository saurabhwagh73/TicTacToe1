package com.scaler.tictactoe.Strategy.bot;


import com.scaler.tictactoe.Models.Board;
import com.scaler.tictactoe.Models.Cell;
import com.scaler.tictactoe.Models.CellState;
import com.scaler.tictactoe.Models.Move;

import java.util.List;

public class MediumBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        List<List<Cell>> boards=board.getBoard();
        int rowSize=boards.size();
        int colSize=boards.get(0).size();
        int i=0, j=colSize-1;
        while(i<rowSize && j>=0){
            if(boards.get(i).get(j).getCellState().equals(CellState.Empty)){
                return new Move(boards.get(i).get(j),null);
            }else if(boards.get(i).get(j-1).getCellState().equals(CellState.Empty)){
                j--;
            }else if(boards.get(i+1).get(j).getCellState().equals(CellState.Empty)){
                i++;
            }else{
                Move move=checkreverseManner(boards);
                return move;
            }
        }
        return null;
    }

    private Move checkreverseManner(List<List<Cell>> boards){
        for(int i=boards.size()-1;i>=0;i--){
            for(int j=boards.get(i).size()-1;j>=0;j--){
                if(boards.get(i).get(j).getCellState().equals(CellState.Empty)){
                    return new Move(boards.get(i).get(j),null);
                }
            }
        }
        return null;
    }
}