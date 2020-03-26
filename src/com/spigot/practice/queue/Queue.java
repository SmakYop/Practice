package com.spigot.practice.queue;

import com.spigot.practice.Practice;
import com.spigot.practice.PracticePlayer;
import com.spigot.practice.arena.Arena;
import com.spigot.practice.match.Ladder;
import com.spigot.practice.match.Match;
import com.spigot.practice.match.Ranking;

import java.util.ArrayList;
import java.util.HashMap;

public class Queue {

    private Ladder ladder;
    private Ranking ranking;

    private static HashMap<PracticePlayer, Ladder> unrankedQueuePlayers = new HashMap<>();
    private static HashMap<PracticePlayer, Ladder> rankedQueuePlayers = new HashMap<>();

    public Queue(Ladder ladder) {
        this.ladder = ladder;
    }

    public HashMap<PracticePlayer, Ladder> getUnrankedQueuePlayers(){
        return unrankedQueuePlayers;
    }

    public HashMap<PracticePlayer, Ladder> getRankedQueuePlayers(){
        return rankedQueuePlayers;
    }

    public void addPlayer(PracticePlayer practicePlayer){
        if(this.ranking == Ranking.UNRANKED) {
            unrankedQueuePlayers.put(practicePlayer, this.ladder);
            practicePlayer.getPlayer().sendMessage("§eSuccessfully added to the queue! Mode: §6" + this.ladder.getTypeName() + " §e| §6" + this.ranking.getRankingName());

            if(unrankedQueuePlayers.size() == 2) startMatch(this.ranking);

        }else if(this.ranking == Ranking.RANKED){
            rankedQueuePlayers.put(practicePlayer, this.ladder);
            practicePlayer.getPlayer().sendMessage("§eSuccessfully added to the queue! Mode: §6" + this.ladder.getTypeName() + " §e| §6" + this.ranking.getRankingName());

            if(rankedQueuePlayers.size() == 2) startMatch(this.ranking);
        }
    }

    private void removePlayer(PracticePlayer practicePlayer){
        if(this.ranking == Ranking.UNRANKED) unrankedQueuePlayers.remove(practicePlayer);
        else if(this.ranking == Ranking.RANKED) rankedQueuePlayers.remove(practicePlayer);
    }

    private void startMatch(Ranking ranking){
        if(ranking == Ranking.UNRANKED){
            ArrayList<PracticePlayer> list = new ArrayList(unrankedQueuePlayers.keySet());
            PracticePlayer firstPlayer = list.get(0);
            PracticePlayer secondPlayer = list.get(1);

            Match match = new Match(firstPlayer, secondPlayer, Practice.getInstance().getArenaManager().selectRandomPlayableArena(), this.ladder, Ranking.UNRANKED);
            match.createMatch();

            removePlayer(firstPlayer);
            removePlayer(secondPlayer);

        }

        if(ranking == Ranking.RANKED){
            ArrayList<PracticePlayer> list = new ArrayList(unrankedQueuePlayers.keySet());
            PracticePlayer firstPlayer = list.get(0);
            PracticePlayer secondPlayer = list.get(1);

            /**Arena arena = new Arena("#"+(Arena.getArenaList().size()+1),new Location(Bukkit.getWorld("world"),0,0,0),new Location(Bukkit.getWorld("world"),0,0,0));
            arena.createArena();

            Match match = new Match(firstPlayer, secondPlayer, arena, this.ladder, Ranking.RANKED);
            match.createMatch();**/

            removePlayer(firstPlayer);
            removePlayer(secondPlayer);
        }
    }

    public Ladder getLadder() {
        return ladder;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking){
        this.ranking = ranking;
    }
}
