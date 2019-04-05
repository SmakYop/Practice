package com.spigot.practice;

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
		registerCommands();
	}

	@Override
	public void onDisable() {
		instance = null;
	}

	public static Practice getInstance(){
		return instance;
	}

	public static void log(String log){
		instance.getLogger().info(log);
	}

	private void registerCommands(){
		this.getCommand("setspawn").setExecutor(new SpawnCommand());
	}
}
