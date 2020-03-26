package com.spigot.practice.elo;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.Ladder;

public class EloManager {

    public EloManager(){

    }

    public void updateElo(PracticePlayer playerWinner, PracticePlayer playerLooser, Ladder ladder){
        double ratio = playerLooser.getElo(ladder) / playerWinner.getElo(ladder);
        playerWinner.addElo(ladder, f(ratio));
        playerLooser.removeElo(ladder, f(ratio));
        //sendMessage
    }

    private int f(double x){
        return (int)(36.78/(1+71.46*Math.exp(-3.68*x)));
    }
}
