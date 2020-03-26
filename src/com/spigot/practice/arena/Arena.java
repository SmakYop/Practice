package com.spigot.practice.arena;

import com.spigot.practice.Practice;
import org.bukkit.*;

import java.util.HashMap;

public class Arena {

    private String id;
    private String name;
    private Location firstPlayerLocation;
    private Location secondPlayerLocation;
    private Location arenaCenterLocation;
    private Location spectateLocation;
    private boolean isUsed = false;
    private boolean canBuild = false;

    private static HashMap<String, Arena> arenaList = new HashMap<>();

    public Arena(String arenaId){
        this.id = arenaId;
        arenaList.put(this.id, this);
    }

    public static Arena get(String arenaId){
        return arenaList.get(arenaId);
    }

    public void save(){
        Practice.getInstance().getArenaConfig().saveArena(this.id);
    }

    public void remove(){
        Practice.getInstance().getArenaConfig().removeArena(this.id);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getCenterLocation() {
        return arenaCenterLocation;
    }

    public Location getSpectateLocation() {
        return spectateLocation;
    }

    public Location getFirstPlayerLocation() {
        return firstPlayerLocation;
    }

    public Location getSecondPlayerLocation() {
        return secondPlayerLocation;
    }

    public static HashMap<String, Arena> getArenaList(){
        return arenaList;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public boolean canBuild(){
        return canBuild;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCanBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    public void setFirstPlayerLocation(Location firstPlayerLocation) {
        this.firstPlayerLocation = firstPlayerLocation;
    }

    public void setSecondPlayerLocation(Location secondPlayerLocation) {
        this.secondPlayerLocation = secondPlayerLocation;
    }

    public void setCenterLocation(Location arenaCenterLocation) {
        this.arenaCenterLocation = arenaCenterLocation;
    }

    public void setSpectateLocation(Location spectateLocation) {
        this.spectateLocation = spectateLocation;
    }
}

