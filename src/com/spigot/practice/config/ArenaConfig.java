package com.spigot.practice.config;

import com.spigot.practice.Practice;
import com.spigot.practice.arena.Arena;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ArenaConfig {

    private static File arenaFile = new File(Practice.getInstance().getDataFolder(), "arena.yml");

    public static void loadArenaConfigFile(){
        try{
            if(!arenaFile.exists()) arenaFile.createNewFile();
        }catch (IOException e){
            return;
        }
    }

    public static void saveArena(String arenaId){
        Arena arena = Arena.get(arenaId);
        try {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
            fileConfiguration.set("arena." + arenaId + ".first_player_location.world", arena.getFirstPlayerLocation().getWorld().getName());
            fileConfiguration.set("arena." + arenaId + ".first_player_location.x", arena.getFirstPlayerLocation().getBlockX());
            fileConfiguration.set("arena." + arenaId + ".first_player_location.y", arena.getFirstPlayerLocation().getBlockY());
            fileConfiguration.set("arena." + arenaId + ".first_player_location.z", arena.getFirstPlayerLocation().getBlockZ());
            fileConfiguration.set("arena." + arenaId + ".second_player_location.world", arena.getSecondPlayerLocation().getWorld().getName());
            fileConfiguration.set("arena." + arenaId + ".second_player_location.x", arena.getSecondPlayerLocation().getBlockX());
            fileConfiguration.set("arena." + arenaId + ".second_player_location.y", arena.getSecondPlayerLocation().getBlockY());
            fileConfiguration.set("arena." + arenaId + ".second_player_location.z", arena.getSecondPlayerLocation().getBlockZ());
            fileConfiguration.set("arena." + arenaId + ".temporary", arena.isTemporary());
            fileConfiguration.save(arenaFile);
            Practice.log("Arena " + arenaId + " saved in arena.yml");
        }catch (IOException e){
            return;
        }
    }

    public static void removeArena(String arenaId){
        try{
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
            fileConfiguration.set("arena." + arenaId, null);
            fileConfiguration.save(arenaFile);
        }catch (IOException e){
            return;
        }
    }

    public static boolean exists(String arenaId){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        return fileConfiguration.get("arena."+arenaId) != null;
    }
}
