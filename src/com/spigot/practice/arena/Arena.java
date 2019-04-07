package com.spigot.practice.arena;

import com.spigot.practice.Practice;
import org.bukkit.Location;

import java.util.HashMap;

public class Arena {

    private String id;
    private Location arenaLocation;
    private Location firstPlayerLocation;
    private Location secondPlayerLocation;
    private boolean isUsed = false;
    private boolean isTemporary;
    private ArenaType arenaType;
    private static HashMap<String, Arena> arenaList = new HashMap<>();

    public Arena(String arenaId, ArenaType arenaType, Location arenaLocation, Location firstPlayerLocation, Location secondPlayerLocation){
        this.id = arenaId;
        arenaList.put(this.id, this);
        this.arenaType = arenaType;
        this.arenaLocation = arenaLocation;
        this.firstPlayerLocation = firstPlayerLocation;
        this.secondPlayerLocation = secondPlayerLocation;
    }

    public static Arena get(String arenaId){
        return arenaList.get(arenaId);
    }

    public void createArena(){
        ArenaConfig.saveArena(this.id);
        Practice.log("Arena created | Id: " + this.id + " | ArenaType: " + arenaType);
        // setLocation(Iterables.getLast(arenaList.values()).getLocation().clone().add(100,0,0));
        // paste worldedit en fonction du type
    }

    public void remove(){
        arenaList.remove(this.id);
        Practice.log("Arena removed | Id: " + this.id);
       // cut map with worldedit
    }

    public String getId() {
        return id;
    }

    public Location getArenaLocation() {
        return arenaLocation;
    }

    public ArenaType getArenaType() {
        return arenaType;
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

    public boolean isTemporary() {
        return isTemporary;
    }

    public void setLocation(Location location) {
        this.arenaLocation = location;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
    }

    public void setArenaType(ArenaType arenaType) {
        this.arenaType = arenaType;
    }

    public void setFirstPlayerLocation(Location firstPlayerLocation) {
        this.firstPlayerLocation = firstPlayerLocation;
    }

    public void setSecondPlayerLocation(Location secondPlayerLocation) {
        this.secondPlayerLocation = secondPlayerLocation;
    }
    
}

