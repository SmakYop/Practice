package com.spigot.practice.listeners;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.inventory.ItemsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnection implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		PracticePlayer practicePlayer = new PracticePlayer(player);

		player.setHealth(20);
		player.setLevel(0);
		player.setExp(0);
		player.setFoodLevel(20);
		//player.teleport(PracticeConfig.SPAWN_LOCATION);

		sendLobbyItems(player);
		practicePlayer.getScoreboard().sendLobbyScoreboard();
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		if(PracticePlayer.get(event.getPlayer()).getQueue() != null)
			PracticePlayer.get(event.getPlayer()).getQueue().removePlayer(PracticePlayer.get(event.getPlayer()));
	}

	private void sendLobbyItems(Player player){
		ItemsManager unranked = new ItemsManager(Material.IRON_SWORD, "§eUnranked", new String[]{"§7Right-click to play unranked matches"});
		ItemsManager ranked = new ItemsManager(Material.DIAMOND_SWORD, "§eRanked", new String[]{"§7Right-click to play ranked matches"});

		player.getInventory().setItem(0, unranked.toItemStack());
		player.getInventory().setItem(1, ranked.toItemStack());
	}
}
