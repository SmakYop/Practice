package com.spigot.practice.arena;

public class ArenaManager {

    public static void loadDefaultArena(){
        for(int i=1; i<=10;i++){
            String arenaId = "#"+i;
            new Arena().createArena(arenaId, ArenaType.DEFAULT);
        }
    }

    public static void removeArenas(){
        for(int i=1; i<=Arena.getAllArenas().size()+1; i++){
            String arenaId = "#"+i;
            Arena.get(arenaId).remove();
        }
    }
}
