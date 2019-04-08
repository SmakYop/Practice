package com.spigot.practice.elo;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.GameType;

public class EloManager {

    public EloManager(){

    }

    public void updateElo(PracticePlayer playerWinner, PracticePlayer playerLooser, GameType gameType){
        int ratio = playerLooser.getElo(gameType) / playerWinner.getElo(gameType);
        playerWinner.addElo(gameType, (int)fonction(ratio));
        playerLooser.removeElo(gameType, (int)fonction(ratio));
    }

    private double fonction(int x){
        return 6.5 * Math.exp(x);
    }
}
