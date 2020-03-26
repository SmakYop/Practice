package com.spigot.practice.config;

import com.spigot.practice.Practice;
import com.spigot.practice.arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ArenaConfig {

    private File arenaFile = new File(Practice.getInstance().getDataFolder(), "arena.yml");

    public void loadArenaConfigFile(){
        try{
            if(!arenaFile.exists()) arenaFile.createNewFile();
        }catch (IOException e){
            Practice.log("ERROR: Problem with arena file.. Try to restart or contact an administrator");
        }
    }

    public void saveArena(String arenaId){
        Arena arena = Arena.get(arenaId);
        if(arena == null){
            Practice.log("ERROR: can't save arena (ID: #" + arenaId + "). Arena doesn't exists");
            return;
        }
        try {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
            fileConfiguration.set("arena."+arenaId+".name", arena.getName());
            fileConfiguration.set("arena."+arenaId+".build", arena.canBuild());
            fileConfiguration.set("arena."+arenaId+".center-location.world", arena.getCenterLocation().getWorld().getName());
            fileConfiguration.set("arena."+arenaId+".center-location.x", arena.getCenterLocation().getX());
            fileConfiguration.set("arena."+arenaId+".center-location.y", arena.getCenterLocation().getY());
            fileConfiguration.set("arena."+arenaId+".center-location.z", arena.getCenterLocation().getZ());
            fileConfiguration.set("arena."+arenaId+".first-pos.world", arena.getFirstPlayerLocation().getWorld().getName());
            fileConfiguration.set("arena."+arenaId+".first-pos.x", arena.getFirstPlayerLocation().getX());
            fileConfiguration.set("arena."+arenaId+".first-pos.y", arena.getFirstPlayerLocation().getY());
            fileConfiguration.set("arena."+arenaId+".first-pos.z", arena.getFirstPlayerLocation().getZ());
            fileConfiguration.set("arena."+arenaId+".second-pos.world", arena.getSecondPlayerLocation().getWorld().getName());
            fileConfiguration.set("arena."+arenaId+".second-pos.x", arena.getSecondPlayerLocation().getX());
            fileConfiguration.set("arena."+arenaId+".second-pos.y", arena.getSecondPlayerLocation().getY());
            fileConfiguration.set("arena."+arenaId+".second-pos.z", arena.getSecondPlayerLocation().getZ());
            fileConfiguration.set("arena."+arenaId+".spec-location.world", arena.getSpectateLocation().getWorld().getName());
            fileConfiguration.set("arena."+arenaId+".spec-location.x", arena.getSpectateLocation().getX());
            fileConfiguration.set("arena."+arenaId+".spec-location.y", arena.getSpectateLocation().getY());
            fileConfiguration.set("arena."+arenaId+".spec-location.z", arena.getSpectateLocation().getZ());
            fileConfiguration.save(arenaFile);
            Practice.log("Arena (ID: #" + arenaId + ") saved in arena.yml");
        }catch (IOException e){
            Practice.log("ERROR: can't register arena in arena.yml | Contact an administrator");
        }
    }

    public void removeArena(String arenaId){
        try{
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
            fileConfiguration.set("arena." + arenaId, null);
            fileConfiguration.save(arenaFile);
        }catch (IOException e){
            Practice.log("ERROR: can't revove arena in arena.yml | Contact an administrator");
        }
    }

    public boolean exists(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return fileConfiguration.get("arena."+arenaId) != null;
    }

    public String getName(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return fileConfiguration.getString("arena." + arenaId + ".name");
    }

    public boolean canBuild(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return fileConfiguration.getBoolean("arena." + arenaId + ".build");
    }

    public Location getCenterLocation(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return new Location(Bukkit.getWorld(fileConfiguration.getString("arena." + arenaId + ".center-location.world")), fileConfiguration.getDouble("arena." + arenaId + ".center-location.x"), fileConfiguration.getDouble("arena." + arenaId + ".center-location.y"), fileConfiguration.getDouble("arena." + arenaId + ".center-location.z"));
    }

    public Location getFirstPosLocation(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return new Location(Bukkit.getWorld(fileConfiguration.getString("arena." + arenaId + ".first-pos.world")), fileConfiguration.getDouble("arena." + arenaId + ".first-pos.x"), fileConfiguration.getDouble("arena." + arenaId + ".first-pos.y"), fileConfiguration.getDouble("arena." + arenaId + ".first-pos.z"));
    }

    public Location getSecondPosLocation(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return new Location(Bukkit.getWorld(fileConfiguration.getString("arena." + arenaId + ".second-pos.world")), fileConfiguration.getDouble("arena." + arenaId + ".second-pos.x"), fileConfiguration.getDouble("arena." + arenaId + ".second-pos.y"), fileConfiguration.getDouble("arena." + arenaId + ".second-pos.z"));
    }

    public Location getSpectatorLocation(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return new Location(Bukkit.getWorld(fileConfiguration.getString("arena." + arenaId + ".spec-location.world")), fileConfiguration.getDouble("arena." + arenaId + ".spec-location.x"), fileConfiguration.getDouble("arena." + arenaId + ".spec-location.y"), fileConfiguration.getDouble("arena." + arenaId + ".spec-location.z"));
    }
}
