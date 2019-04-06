package com.spigot.practice.arena;

import com.spigot.practice.Practice;
import org.bukkit.Location;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Arena {

    private String id;
    private Location location;
    private Location firstPlayerLocation;
    private Location secondPlayerLocation;
    private boolean isUsed = false;
    private ArenaType arenaType;
    private static Map<String, Arena> arenaList = new HashMap<>();

    public Arena(){}
    public Arena(String arenaId){this.id = arenaId;}

    public static void init(String arenaId){
        if(get(arenaId) == null) arenaList.put(arenaId, new Arena(arenaId));
    }

    public static Arena get(String arenaId){
        return arenaList.get(arenaId);
    }

    public static Collection<? extends Arena> getAllArenas(){
        return arenaList.values();
    }

    public void createArena(String arenaId, ArenaType arenaType){
        int arenaNumber = arenaList.size()+1;
        this.id = "#" + arenaNumber;
        this.arenaType = arenaType;
        init(arenaId);

        // setLocation = error
        //setLocation(Iterables.getLast(arenaList.values()).getLocation().clone().add(100,0,0));
        Practice.log("Arena created | Id: " + arenaId + " | ArenaType: " + arenaType);
    }

    public Arena remove(){
        Practice.log("Arena removed | Id: " + this.id);
        return arenaList.remove(this.id);
       /* cut map with worldedit */
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
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

    public boolean isUsed() {
        return isUsed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setUsed(boolean used) {
        isUsed = used;
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

