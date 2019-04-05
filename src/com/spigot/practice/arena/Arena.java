package com.spigot.practice.arena;

import com.google.common.collect.Iterables;
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
    private boolean isTemporary;
    private boolean isUsed = false;
    private ArenaType arenaType;
    private static Map<String, Arena> arenaList = new HashMap<>();

    public Arena(){}

    public static void init(String arenaId){
        if(get(arenaId) == null) arenaList.put(arenaId, new Arena());
    }

    public static Arena get(String arenaId){
        return arenaList.get(arenaId);
    }

    public static Collection<? extends Arena> getAllArenas(){
        return arenaList.values();
    }

    public void registerFirstArena(){
        this.id = "#1";
        this.isTemporary = false;
        this.arenaType = ArenaType.DEFAULT;
        init(this.id);
    }

    public void createArena(String arenaId, ArenaType arenaType){
        int arenaNumber = arenaList.size()+1;
        this.id = "#" + arenaNumber;
        this.arenaType = arenaType;
        init(arenaId);

        setLocation(Iterables.getLast(arenaList.values()).getLocation().clone().add(100,0,0));
        Practice.log("Arena created | Id: " + arenaId + " | ArenaType: " + arenaType);
    }

    /*public Arena removeArena(){
        return this;
    }*/

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

    public boolean isTemporary() {
        return isTemporary;
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

    public void setTemporary(boolean temporary) {
        isTemporary = temporary;
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

