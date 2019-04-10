package com.spigot.practice.elo;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.GameType;

public class EloManager {

    public EloManager(){

    }

    public void updateElo(PracticePlayer playerWinner, PracticePlayer playerLooser, GameType gameType){
        double ratio = playerLooser.getElo(gameType) / playerWinner.getElo(gameType);
        playerWinner.addElo(gameType, f(ratio));
        playerLooser.removeElo(gameType, f(ratio));
        //sendMessage
    }

    private int f(double x){
        return (int)(36.78/(1+71.46*Math.exp(-3.68*x)));
    }
}
