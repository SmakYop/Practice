package com.spigot.practice.arena;

import com.spigot.practice.config.ArenaConfig;
import com.spigot.practice.config.PracticeConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaManager {

    public static void loadDefaultArena(){
        for(int i = 1; i<= PracticeConfig.DEFAULT_ARENA_NUMBER; i++){
            String arenaId = "#"+i;

            if(!ArenaConfig.exists(arenaId)){
                Location a = new Location(Bukkit.getWorld("world"),0,0,0);
                Location b = new Location(Bukkit.getWorld("world"),0,0,0);
                Arena defaultArena = new Arena(arenaId, a,b);
                defaultArena.createArena();
            }
        }
    }
}
