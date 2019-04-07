package com.spigot.practice.arena;

public enum ArenaType {

    DEFAULT("Default"), BUILDUHC("BuildUHC");

    private String arenaName;

    ArenaType(String arenaName){
        this.arenaName = arenaName;
    }

    public String getArenaName(){
        return this.arenaName;
    }
}
