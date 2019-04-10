package com.spigot.practice.inventory;

import com.spigot.practice.Practice;
import com.spigot.practice.config.PracticeConfig;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.entity.Player;

public class InventoryManager {

    public void openUnrankedInventory(Player player){
        SmartInventory unrankedInventory = SmartInventory.builder()
                .provider(new UnrankedInventory(player))
                .id("unrankedInventory")
                .size(1,9)
                .title(PracticeConfig.UNRANKED_TITLE)
                .build();
        unrankedInventory.open(player);
    }

    public void openRankedInventory(Player player){
        SmartInventory rankedInventory = SmartInventory.builder()
                .provider(new RankedInventory(player))
                .id("rankedInventory")
                .size(1,9)
                .title(PracticeConfig.RANKED_TITLE)
                .build();
        rankedInventory.open(player);
    }
}
