package com.spigot.practice.queue;

import com.spigot.practice.Practice;
import com.spigot.practice.PracticePlayer;
import com.spigot.practice.arena.Arena;
import com.spigot.practice.ladder.Ladder;
import com.spigot.practice.match.Match;
import com.spigot.practice.match.Ranking;

import java.util.ArrayList;

public abstract class Queue {

    public abstract Ladder getLadder();

    public abstract Ranking getRanking();

    public abstract ArrayList<PracticePlayer> getPlayers();

    public abstract void addPlayer(PracticePlayer practicePlayer);

    public abstract void removePlayer(PracticePlayer practicePlayer);

    void startMatch(PracticePlayer firstPlayer, PracticePlayer secondPlayer, Ladder ladder, Ranking ranking){
        Arena arena = Practice.getInstance().getArenaManager().selectRandomPlayableArena(ladder.canBuild());
        if(arena == null){
            removePlayer(firstPlayer); removePlayer(secondPlayer);
            firstPlayer.getPlayer().sendMessage("§cNo arena is available."); secondPlayer.getPlayer().sendMessage("§cNo arena is available.");
            return;
        }
        Match match = new Match(firstPlayer, secondPlayer, Practice.getInstance().getArenaManager().selectRandomPlayableArena(ladder.canBuild()), ladder, ranking);

        firstPlayer.setMatch(match); secondPlayer.setMatch(match);
        firstPlayer.sendDuelScoreboard(); secondPlayer.sendDuelScoreboard();
        firstPlayer.removeQueue(); secondPlayer.removeQueue();

        removePlayer(firstPlayer); removePlayer(secondPlayer);

        match.createMatch();
    }
}
