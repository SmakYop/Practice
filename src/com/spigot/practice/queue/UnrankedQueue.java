package com.spigot.practice.queue;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.ladder.Ladder;
import com.spigot.practice.match.Ranking;

import java.util.ArrayList;
import java.util.HashMap;

public class UnrankedQueue extends Queue {

    private Ranking ranking;
    private Ladder ladder;
    private ArrayList<PracticePlayer> players = new ArrayList<>();

    private static HashMap<Ladder, UnrankedQueue> queues = new HashMap<>();

    public UnrankedQueue(Ladder ladder){
        this.ladder = ladder;
        this.ranking = Ranking.UNRANKED;
        queues.put(ladder, this);
    }

    public static Queue getQueue(Ladder ladder){
        return queues.get(ladder);
    }

    @Override
    public Ranking getRanking() {
        return ranking;
    }

    @Override
    public Ladder getLadder() {
        return ladder;
    }

    @Override
    public ArrayList<PracticePlayer> getPlayers() {
        return players;
    }

    @Override
    public void addPlayer(PracticePlayer practicePlayer) {
        Queue queue = practicePlayer.getQueue();
        if(queue != null && queue.getRanking() == this.ranking && queue.getLadder() == this.ladder){
            practicePlayer.getPlayer().sendMessage("§cYou are already in this queue.");
            return;
        }
        if(queue != null && queue.getRanking() != this.ranking){
            queue.removePlayer(practicePlayer);
        }
        if(queue != null && queue.getRanking() == this.ranking && queue.getLadder() != this.ladder){
            queue.removePlayer(practicePlayer);
        }

        this.players.add(practicePlayer);
        practicePlayer.setQueue(UnrankedQueue.getQueue(this.ladder));
        practicePlayer.getScoreboard().update();
        practicePlayer.getPlayer().sendMessage("§eSuccessfully added to the queue! Mode: §6" + this.ladder.getName() + " §e| §6" + this.ranking.getName());
        practicePlayer.getPlayer().sendMessage("§ePosition: §7#" + players.size());

        if(players.size() == 2)
            startMatch(this.players.get(0), this.players.get(1), this.ladder, this.ranking);
    }

    @Override
    public void removePlayer(PracticePlayer practicePlayer) {
        this.players.remove(practicePlayer);
    }
}
