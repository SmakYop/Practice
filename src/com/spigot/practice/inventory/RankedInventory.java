package com.spigot.practice.inventory;

import com.spigot.practice.Practice;
import com.spigot.practice.PracticePlayer;
import com.spigot.practice.ladder.Ladder;
import com.spigot.practice.queue.Queue;
import com.spigot.practice.queue.RankedQueue;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RankedInventory implements InventoryProvider{

    private Player player;

    RankedInventory(Player player){
        this.player = player;
        this.setupItems(player);
    }

    private void setupItems(Player player){
        PracticePlayer practicePlayer = PracticePlayer.get(player);
        for(int i = 1; i<= Practice.getInstance().getLadderManager().getLadderNumber(); i++){
            Ladder ladder = Practice.getInstance().getLadderManager().getLadder(i);
            ClickableItem clickableItem = ClickableItem.of(new ItemsManager(new ItemStack(373, 1, (byte) 16421).getType(), "§6" + ladder.getName(),
                    new String[] {"§ePlaying: ","§eQueued: ","§7Click to queue for §eRanked " + ladder.getName()}).toItemStack(), event -> {
                event.setCancelled(true);
                practicePlayer.getPlayer().closeInventory();

                Queue queue = RankedQueue.getQueue(ladder);
                queue.addPlayer(practicePlayer);
                practicePlayer.setQueue(queue);

            });

            ladder.setClickableItemItem(clickableItem);
        }

    }

    @Override
    public void init(Player player, InventoryContents inventoryContents) {
        for(int i=1; i<= Practice.getInstance().getLadderManager().getLadderNumber(); i++){
            Ladder ladder = Practice.getInstance().getLadderManager().getLadder(i);
            inventoryContents.set(0, ladder.getItemSlot(), ladder.getClickableItem());
        }
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }

    public Player getPlayer() {
        return player;
    }
}
