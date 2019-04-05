package com.spigot.practice.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

	private Plugin plugin;

	public ListenerManager(Plugin plugin) {
		this.plugin = plugin;
	}

	public void registerEvents() {
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new PlayerConnection(), plugin);

	}

}
