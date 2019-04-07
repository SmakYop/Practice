package com.spigot.practice;

import com.spigot.practice.arena.ArenaConfig;
import com.spigot.practice.arena.ArenaManager;
import com.spigot.practice.commands.SpawnCommand;
import com.spigot.practice.listeners.ListenerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Practice extends JavaPlugin{

	private static Practice instance;

	@Override
	public void onEnable() {
		instance = this;

		new ListenerManager(this).registerEvents();
		saveDefaultConfig();
		ArenaConfig.loadArenaConfigFile();
		registerCommands();

        log("----------------------------------------");
		ArenaManager.loadDefaultArena();
		log("----------------------------------------");
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
	}
}
