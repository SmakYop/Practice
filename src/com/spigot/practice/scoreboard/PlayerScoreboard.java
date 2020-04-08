package com.spigot.practice.scoreboard;

import com.spigot.practice.PracticePlayer;

public class PlayerScoreboard {

    private Scoreboard scoreboard;
    private PracticePlayer practicePlayer;

    public PlayerScoreboard(PracticePlayer practicePlayer){
        this.practicePlayer = practicePlayer;
    }

    public void sendLobbyScoreboard(){
        scoreboard = new Scoreboard(" §6§lPRACTICE Spigot ");
        scoreboard.add("", 10);
        scoreboard.add("§7Global Elo: §e" + practicePlayer.getGlobalElo(), 9);
        scoreboard.add("", 8);
        scoreboard.add("§7Party:", 7);
        scoreboard.add("§e" + practicePlayer.getPartyString(), 6);
        scoreboard.add("§7Queue:", 5);
        scoreboard.add("§e" + practicePlayer.getQueueString(), 4);
        scoreboard.add("", 3);
        scoreboard.add("§7Name: §e" + practicePlayer.getPlayer().getName(), 2);
        scoreboard.add("", 1);
        scoreboard.add("§6mc.practice-server.net", 0);
        scoreboard.build();
        scoreboard.send(practicePlayer.getPlayer());
    }

    public void sendDuelScoreboard(){
        scoreboard = new Scoreboard(" §6§lPRACTICE Spigot ");
        scoreboard.add("", 7);
        scoreboard.add("§7Opponent: §e" + practicePlayer.getMatch().getOpponent(practicePlayer).getPlayer().getName(), 6);
        scoreboard.add("", 5);
        scoreboard.add("§7Ladder: §e" + practicePlayer.getMatch().getLadder().getName(), 4);
        scoreboard.add("§7Ranking: §e" + practicePlayer.getMatch().getRanking().getName(), 3);
        scoreboard.add("§7Arena: §6" + practicePlayer.getMatch().getArena().getName() + " §7(ID: #" + practicePlayer.getMatch().getArena().getId() + ")", 2);
        scoreboard.add("", 1);
        scoreboard.add("§6mc.practice-server.net", 0);
        scoreboard.build();
        scoreboard.send(practicePlayer.getPlayer());
    }

    public void update(){
        scoreboard = new Scoreboard(" §6§lPRACTICE Spigot ");
        scoreboard.add("", 10);
        scoreboard.add("§7Global Elo: §e" + practicePlayer.getGlobalElo(), 9);
        scoreboard.add("", 8);
        scoreboard.add("§7Party:", 7);
        scoreboard.add("§e" + practicePlayer.getPartyString(), 6);
        scoreboard.add("§7Queue:", 5);
        scoreboard.add("§e" + practicePlayer.getQueueString(), 4);
        scoreboard.add("", 3);
        scoreboard.add("§7Name: §e" + practicePlayer.getPlayer().getName(), 2);
        scoreboard.add("", 1);
        scoreboard.add("§6mc.practice-server.net", 0);
        scoreboard.build();
        scoreboard.send(practicePlayer.getPlayer());
    }
}
