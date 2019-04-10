package com.spigot.practice.listeners;

import com.spigot.practice.inventory.InventoryManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener{

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        try{

            if(event.getItem().getData().getItemType() == Material.IRON_SWORD && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eUnranked") && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR)){
                new InventoryManager().openUnrankedInventory(player);
            }

            if(event.getItem().getData().getItemType() == Material.DIAMOND_SWORD && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eRanked") && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR)){
                new InventoryManager().openRankedInventory(player);
            }

        }catch (NullPointerException e){
            return;
        }
    }
}
