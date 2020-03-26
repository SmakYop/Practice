package com.spigot.practice.inventory;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.match.Ladder;
import com.spigot.practice.match.Ranking;
import com.spigot.practice.queue.Queue;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnrankedInventory implements InventoryProvider{

    private Player player;
    private ClickableItem nodebuff, debuff, builduhc, combo, gapple, noenchant;

    public UnrankedInventory(Player player){
        this.player = player;
        this.setupItems(player);
    }

    private void setupItems(Player player){
        PracticePlayer practicePlayer = PracticePlayer.get(player);
        this.nodebuff = ClickableItem.of(new ItemsManager(new ItemStack(373, 1, (byte) 16421).getType(), "§6NoDebuff",
                new String[] {"§ePlaying: ","§eQueued: ","§7Click to queue for §eUnranked Nodebuff"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = new Queue(Ladder.NO_DEBUFF);
            queue.setRanking(Ranking.UNRANKED);
            queue.addPlayer(practicePlayer);
        });
        this.debuff = ClickableItem.of(new ItemsManager(new ItemStack(373, 1, (byte) 16452).getType(), "§6Debuff",
                new String[] {"§ePlaying: ","§eQueued: ","§7Click to queue for §eUnranked Debuff"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.closeInventory();

            Queue queue = new Queue(Ladder.DEBUFF);
            queue.setRanking(Ranking.UNRANKED);
            queue.addPlayer(practicePlayer);
        });
        this.builduhc = ClickableItem.of(new ItemsManager(new ItemStack(327, 1, (byte) 0).getType(), "§6BuildUHC",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eUnranked BuildUHC"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.sendMessage("§cSoon");
            player.closeInventory();
        });
        this.combo = ClickableItem.of(new ItemsManager(new ItemStack(349, 1, (byte) 3).getType(), "§6Combo",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eUnranked Combo"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.sendMessage("§cSoon");
            player.closeInventory();
        });
        this.gapple = ClickableItem.of(new ItemsManager(new ItemStack(322, 1, (byte) 0).getType(), "§6Gapple",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eUnranked Gapple"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.sendMessage("§cSoon");
            player.closeInventory();
        });
        this.noenchant = ClickableItem.of(new ItemsManager(new ItemStack(311, 1, (byte) 0).getType(), "§6NoEnchant",
                new String[] {"§ePlaying: ","§eQueued: ", "§7Click to queue for §eUnranked NoEnchant"}).toItemStack(), event -> {
            event.setCancelled(true);
            player.sendMessage("§cSoon");
            player.closeInventory();
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
}
