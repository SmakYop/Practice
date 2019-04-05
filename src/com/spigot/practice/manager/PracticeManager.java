package com.spigot.practice.manager;

import com.spigot.practice.config.PracticeConfiguration;
import com.spigot.practice.scoreboard.Scoreboard;
import org.bukkit.entity.Player;

public class PracticeManager {

    private static PracticeManager instance = new PracticeManager();

    public static PracticeManager getInstance(){
        return instance;
    }

    public void loadSocreboard(Player player){
        Scoreboard scoreboard = new Scoreboard(PracticeConfiguration.getInstance().getScoreboardName());
        scoreboard.add("§7§m§l             §r§7§m                    ");
        scoreboard.add("§6Online: §e");
        scoreboard.add("§6In Fights: §e0");
        scoreboard.add("§6In Queues: §e0");
        scoreboard.add("");
        scoreboard.add("§6Global Elo: §e1400");
        scoreboard.add("§7§m§l         §r§7§m                        ");
        scoreboard.build();
        scoreboard.send(player);
    }
}
