package com.spigot.practice.elo;

import com.spigot.practice.PracticePlayer;

public class EloManager {

    /*public void updateElo(PracticePlayer playerWinner, PracticePlayer playerLooser, LadderManager ladderManager){
        double ratio = playerLooser.getElo(ladderManager) / playerWinner.getElo(ladderManager);
        playerWinner.addElo(ladderManager, f(ratio));
        playerLooser.removeElo(ladderManager, f(ratio));
        //sendMessage
    }*/

    private int f(double x){
        return (int)(36.78/(1+71.46*Math.exp(-3.68*x)));
    }
}
