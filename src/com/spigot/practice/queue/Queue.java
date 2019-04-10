package com.spigot.practice.queue;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.GameType;
import com.spigot.practice.match.Ranking;

import java.util.ArrayList;
import java.util.HashMap;

public class Queue {

    private GameType gameType;
    private Ranking ranking;

    private static HashMap<PracticePlayer, GameType> unrankedQueuePlayers = new HashMap<>();
    private static HashMap<PracticePlayer, GameType> rankedQueuePlayers = new HashMap<>();

    public Queue(GameType gameType) {
        this.gameType = gameType;
    }

    public HashMap<PracticePlayer, GameType> getUnrankedQueuePlayers(){
        return unrankedQueuePlayers;
    }

    public HashMap<PracticePlayer, GameType> getRankedQueuePlayers(){
        return rankedQueuePlayers;
    }

    public void addPlayer(PracticePlayer practicePlayer){
        if(this.ranking == Ranking.UNRANKED) {
            unrankedQueuePlayers.put(practicePlayer, this.gameType);
            practicePlayer.getPlayer().sendMessage("§eSuccessfully added to the queue! Mode: §6" + this.gameType.getTypeName() + " §e| §6" + this.ranking.getRankingName());
            practicePlayer.getPlayer().sendMessage("Queue size:" + unrankedQueuePlayers.size());

            //Check number of players
            if(unrankedQueuePlayers.size() == 2) startMatch(this.ranking);

        }else if(this.ranking == Ranking.RANKED){
            rankedQueuePlayers.put(practicePlayer, this.gameType);
            practicePlayer.getPlayer().sendMessage("§eSuccessfully added to the queue! Mode: §6" + this.gameType.getTypeName() + " §e| §6" + this.ranking.getRankingName());
            practicePlayer.getPlayer().sendMessage("Queue size:" + rankedQueuePlayers.size());

            //Check number of players
            if(rankedQueuePlayers.size() == 2) startMatch(this.ranking);
        }
    }

    public void removePlayer(PracticePlayer practicePlayer){
        if(this.ranking == Ranking.UNRANKED) unrankedQueuePlayers.remove(practicePlayer);
        else if(this.ranking == Ranking.RANKED) rankedQueuePlayers.remove(practicePlayer);
    }

    private void startMatch(Ranking ranking){
        if(ranking == Ranking.UNRANKED){
            ArrayList<PracticePlayer> list = new ArrayList(unrankedQueuePlayers.keySet());
            PracticePlayer firstPlayer = list.get(0);
            PracticePlayer secondPlayer = list.get(1);

            /**Arena arena = new Arena("#"+(Arena.getArenaList().size()+1),new Location(Bukkit.getWorld("world"),0,0,0),new Location(Bukkit.getWorld("world"),0,0,0));
            arena.createArena();

            Match match = new Match(firstPlayer, secondPlayer, arena, this.gameType, Ranking.UNRANKED);
            match.createMatch();**/

            removePlayer(firstPlayer);
            removePlayer(secondPlayer);

        }

        if(ranking == Ranking.RANKED){
            ArrayList<PracticePlayer> list = new ArrayList(unrankedQueuePlayers.keySet());
            PracticePlayer firstPlayer = list.get(0);
            PracticePlayer secondPlayer = list.get(1);

            /**Arena arena = new Arena("#"+(Arena.getArenaList().size()+1),new Location(Bukkit.getWorld("world"),0,0,0),new Location(Bukkit.getWorld("world"),0,0,0));
            arena.createArena();

            Match match = new Match(firstPlayer, secondPlayer, arena, this.gameType, Ranking.RANKED);
            match.createMatch();**/

            removePlayer(firstPlayer);
            removePlayer(secondPlayer);
        }
    }

    public GameType getGameType() {
        return gameType;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking){
        this.ranking = ranking;
    }
}
