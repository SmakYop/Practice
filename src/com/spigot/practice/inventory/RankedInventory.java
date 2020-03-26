package com.spigot.practice.inventory;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.Ladder;
import com.spigot.practice.queue.Queue;
import com.spigot.practice.queue.RankedQueue;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RankedInventory implements InventoryProvider{

    private Player player;
    private ClickableItem nodebuff, debuff, builduhc, combo, gapple, noenchant;

    RankedInventory(Player player){
        this.player = player;
        this.setupItems(player);
    }

    private void setupItems(Player player){
        PracticePlayer practicePlayer = PracticePlayer.get(player);

        this.nodebuff = ClickableItem.of(new ItemsManager(new ItemStack(373, 1, (byte) 16421).getType(), "§6NoDebuff",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked Nodebuff"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.NO_DEBUFF);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });
        this.debuff = ClickableItem.of(new ItemsManager(new ItemStack(373, 1, (byte) 16452).getType(), "§6Debuff",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked Debuff"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.DEBUFF);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });
        this.builduhc = ClickableItem.of(new ItemsManager(new ItemStack(327, 1, (byte) 0).getType(), "§6BuildUHC",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked BuildUHC"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.BUILDUHC);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });
        this.combo = ClickableItem.of(new ItemsManager(new ItemStack(349, 1, (byte) 3).getType(), "§6Combo",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked Combo"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.COMBO);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });
        this.gapple = ClickableItem.of(new ItemsManager(new ItemStack(322, 1, (byte) 0).getType(), "§6Gapple",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked Gapple"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.GAPPLE);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });
        this.noenchant = ClickableItem.of(new ItemsManager(new ItemStack(311, 1, (byte) 0).getType(), "§6NoEnchant",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eRanked NoEnchant"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = RankedQueue.getQueue(Ladder.NO_ENCHANT);
            queue.addPlayer(practicePlayer);
            practicePlayer.setQueue(queue);
        });

    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        inventoryContents.set(0,0,nodebuff);
        inventoryContents.set(0,1,debuff);
        inventoryContents.set(0,2,builduhc);
        inventoryContents.set(0,3,combo);
        inventoryContents.set(0,4,gapple);
        inventoryContents.set(0,5,noenchant);
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }

    public Player getPlayer() {
        return player;
    }
}
