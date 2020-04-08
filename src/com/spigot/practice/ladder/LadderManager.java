package com.spigot.practice.ladder;

import com.spigot.practice.Practice;
import com.spigot.practice.kits.Kit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LadderManager {

    public void loadLadders(){
        File ladderFile = new File(Practice.getInstance().getDataFolder(), "ladder.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(ladderFile);

        for(int i=1; i<= configuration.getConfigurationSection("ladder").getKeys(false).size(); i++){
            Ladder ladder = new Ladder(configuration.getString("ladder." + i + ".name"));
            ladder.setRanked(configuration.getBoolean("ladder." + i + ".ranked"));
            ladder.setBuild(configuration.getBoolean("ladder." + i + ".build"));
            ladder.setKit(Kit.getKits(configuration.getString("ladder." + i + ".kit")));
            ladder.setItemSlot(configuration.getInt("ladder." + i + ".item_slot"));

        }
    }

    public Ladder getLadder(int id){
        File ladderFile = new File(Practice.getInstance().getDataFolder(), "ladder.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(ladderFile);
        return Ladder.getLadder(configuration.getString("ladder." + id + ".name"));
    }

    public int getLadderNumber() {
        File ladderFile = new File(Practice.getInstance().getDataFolder(), "ladder.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(ladderFile);
        return configuration.getConfigurationSection("ladder").getKeys(false).size();
    }
}
