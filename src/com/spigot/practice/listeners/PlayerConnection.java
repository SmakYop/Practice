package com.spigot.practice.listeners;

import com.spigot.practice.PracticePlayer;
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

		practicePlayer.sendLobbyItems();
		practicePlayer.sendLobbyScoreboard();
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event){
		if(PracticePlayer.get(event.getPlayer()).getQueue() != null)
			PracticePlayer.get(event.getPlayer()).getQueue().removePlayer(PracticePlayer.get(event.getPlayer()));
	}
}
