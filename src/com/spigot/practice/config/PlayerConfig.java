package com.spigot.practice.config;

import com.spigot.practice.Practice;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerConfig {

    private static File playersFile = new File(Practice.getInstance().getDataFolder(), "players.yml");

    public static void loadPlayersFile(){
        try{
            if(!playersFile.exists()) playersFile.createNewFile();
        }catch (IOException e){
            return;
        }
    }

    public static void registerPlayer(Player player){
        String playerName = player.getName();
        try{
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
            fileConfiguration.set("players."+playerName+".elo.globlal_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.debuff_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.nodebuff_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.builduhc_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.combo_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.gapple_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".elo.noenchant_elo", PracticeConfig.START_PLAYER_ELO);
            fileConfiguration.set("players."+playerName+".stats.unranked_wins", 0);
            fileConfiguration.set("players."+playerName+".stats.unranked_looses", 0);
            fileConfiguration.set("players."+playerName+".stats.ranked_wins", 0);
            fileConfiguration.set("players."+playerName+".stats.ranked_looses", 0);
            fileConfiguration.save(playersFile);
        }catch (IOException e){
            return;
        }
    }

    public static boolean exists(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.get("players."+player.getName()) != null;
    }

    /** Player's Elo **/

    public static int getGlobalElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.globlal_elo");
    }

    public static int getDebuffElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.debuff_elo");
    }

    public static int getNoDebuffElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.nodebuff_elo");
    }

    public static int getBuildUhcElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.builduhc_elo");
    }

    public static int getComboElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.combo_elo");
    }

    public static int getGappleElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.gapple_elo");
    }

    public static int getNoEnchantElo(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".elo.noenchant_elo");
    }

    /** Player's stats **/

    public static int getUnrankedWins(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".stats.unranked_wins");
    }

    public static int getUnrankedLooses(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".stats.unranked_looses");
    }

    public static int getRankedWins(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".stats.ranked_wins");
    }

    public static int getRankedLooses(Player player){
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(playersFile);
        return fileConfiguration.getInt("players."+player.getName()+".stats.ranked_looses");
    }
}
