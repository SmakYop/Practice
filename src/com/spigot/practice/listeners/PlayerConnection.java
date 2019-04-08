package com.spigot.practice.listeners;

import com.spigot.practice.PracticePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnection implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		new PracticePlayer(player);

		player.setHealth(20);
		player.setLevel(0);
		player.setExp(0);
		player.setFoodLevel(20);
		//player.teleport(PracticeConfig.SPAWN_LOCATION);
	}
}
