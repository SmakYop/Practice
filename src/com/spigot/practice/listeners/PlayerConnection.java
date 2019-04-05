package com.spigot.practice.listeners;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.manager.PracticeManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnection implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		PracticePlayer.init(player);

		PracticeManager.getInstance().loadSocreboard(player);

		player.setAllowFlight(false);
		player.setHealth(20);
		player.setLevel(0);
		player.setExp(0);
		player.setFoodLevel(20);
	}
}
