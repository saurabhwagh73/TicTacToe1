package com.scaler.tictactoe.Models;

import com.scaler.tictactoe.Exceptions.BotCountException;
import com.scaler.tictactoe.Exceptions.PlayerCountException;
import com.scaler.tictactoe.Exceptions.uniqueSymbolException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameValidate {
    private int dimension;
    private List<Player> players;

    public GameValidate(int dimension,List<Player> players){
        this.dimension=dimension;
        this.players=players;
    }

    public void validate() throws BotCountException, PlayerCountException, uniqueSymbolException {
        validateBotCount();
        validatePlayerCount();
        validateUniqueSymbols();
    }

    public void validateBotCount() throws BotCountException {
        int botCount = 0;
        for(Player player: players) {
            if(player.getPlayerType().equals(PlayerType.Bot)) {
                botCount += 1;
            }
        }

        if(botCount > 1) {
            throw new BotCountException("Only one allowed BOT Player");
        }
    }

    public void validatePlayerCount() throws PlayerCountException {
        if(players.size() != dimension - 1) {
            throw new PlayerCountException("player count is not valid as game dimension");
        }
    }

    public void validateUniqueSymbols() throws uniqueSymbolException {
        Map<Character,Integer> fmap=new HashMap<>();
        for(Player player:players){
            if(fmap.containsKey(player.getSymbol().getaChar())){
                int oldfreq=fmap.get(player.getSymbol().getaChar());
                fmap.put(player.getSymbol().getaChar(),oldfreq+1);
            }else{
                fmap.put(player.getSymbol().getaChar(),1);
            }
        }
        for(Player player:players){
            int fq=fmap.get(player.getSymbol().getaChar());
            if(fq>1){
                throw new uniqueSymbolException("Please provide each player unique symbol");
            }
        }
    }
}
