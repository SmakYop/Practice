package com.spigot.practice.arena;

public class ArenaManager {

    public static void loadDefaultArena(){
        new Arena().registerFirstArena();
        for(int i=0; i<10;i++){
            String arenaId = "#"+i;
            new Arena().createArena(arenaId, ArenaType.DEFAULT);
        }
    }
}
