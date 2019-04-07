package com.spigot.practice.arena;

import com.spigot.practice.Practice;
import com.spigot.practice.config.PracticeConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaManager {

    public static void loadDefaultArena(){
        for(int i = 1; i<= PracticeConfiguration.DEFAULT_ARENA_NUMBER; i++){
            String arenaId = "#"+i;

            if(!ArenaConfig.exists(arenaId)){
                Location a = new Location(Bukkit.getWorld("world"),0,0,0);
                Location b = new Location(Bukkit.getWorld("world"),0,0,0);
                Location c = new Location(Bukkit.getWorld("world"),0,0,0);
                Arena defaultArena = new Arena(arenaId, ArenaType.DEFAULT, a,b,c);
                defaultArena.createArena();
            }
        }
    }

    public static void removeArenas(){
        Arena.getArenaList().clear();
        // cut worldedit
        Practice.log("Arena removed");
    }
}
