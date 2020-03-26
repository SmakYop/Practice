package com.spigot.practice.match;

import com.spigot.practice.Practice;
import com.spigot.practice.PracticePlayer;
import com.spigot.practice.arena.Arena;
import com.spigot.practice.config.PracticeConfig;

public class Match {

    private PracticePlayer firstPlayer;
    private PracticePlayer secondPlayer;
    private Arena arena;
    private Ladder ladder;
    private Ranking ranking;

    public Match(PracticePlayer firstPlayer, PracticePlayer secondPlayer, Arena arena, Ladder ladder, Ranking ranking){
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.arena = arena;
        this.ladder = ladder;
        this.ranking = ranking;
    }

    public void createMatch(){
        Practice.log("Creating match... Arena: #" + arena.getId() + " | Ladder: " + ladder.getTypeName() + " | Ranking: " + ranking.getRankingName());
        this.firstPlayer.getPlayer().sendMessage(PracticeConfig.PREFIX+" §7Match found | §6" + ladder.getTypeName() + " §7| §6" + ranking.getRankingName());
        this.firstPlayer.getPlayer().sendMessage(PracticeConfig.PREFIX+" §7Opponent: §c" + this.secondPlayer.getPlayer().getName());
        this.secondPlayer.getPlayer().sendMessage(PracticeConfig.PREFIX+" §7Match found | §6" + ladder.getTypeName() + " §7| §6" + ranking.getRankingName());
        this.secondPlayer.getPlayer().sendMessage(PracticeConfig.PREFIX+" §7Opponent: §c" + this.firstPlayer.getPlayer().getName());

        this.firstPlayer.getPlayer().teleport(this.arena.getFirstPlayerLocation());
        this.secondPlayer.getPlayer().teleport(this.arena.getSecondPlayerLocation());
        startMatch();
    }

    private void startMatch(){

    }

    public void finishMatch(){

    }

    public void cancelMatch(String reason){

    }

    public PracticePlayer getFirstPlayer() {
        return firstPlayer;
    }

    public PracticePlayer getSecondPlayer() {
        return secondPlayer;
    }

    public Arena getArena() {
        return arena;
    }

    public Ladder getLadder() {
        return ladder;
    }

    public Ranking getRanking() {
        return ranking;
    }
}
