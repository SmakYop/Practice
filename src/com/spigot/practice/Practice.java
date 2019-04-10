package com.spigot.practice;

import com.spigot.practice.commands.ArenaCommand;
import com.spigot.practice.commands.MatchCommand;
import com.spigot.practice.commands.PartyCommand;
import com.spigot.practice.config.ArenaConfig;
import com.spigot.practice.arena.ArenaManager;
import com.spigot.practice.commands.SpawnCommand;
import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Practice extends JavaPlugin{

	private static Practice instance;

	@Override
	public void onEnable() {
		instance = this;

		new ListenerManager(this).registerEvents();

		saveDefaultConfig();
		registerCommands();

		ArenaConfig.loadArenaConfigFile();
		PlayerConfig.loadPlayersFile();
		ArenaManager.loadDefaultArena();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static Practice getInstance(){
		return instance;
	}

	public static void log(String log){
		instance.getLogger().info("[Practice] "+log);
	}

	private void registerCommands(){
		this.getCommand("setspawn").setExecutor(new SpawnCommand());
		this.getCommand("arena").setExecutor(new ArenaCommand());
		this.getCommand("party").setExecutor(new PartyCommand());
		this.getCommand("match").setExecutor(new MatchCommand());
	}
}
