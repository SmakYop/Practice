package com.spigot.practice;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.spigot.practice.arena.ArenaManager;
import com.spigot.practice.commands.ArenaCommand;
import com.spigot.practice.commands.PartyCommand;
import com.spigot.practice.config.ArenaConfig;
import com.spigot.practice.commands.SpawnCommand;
import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.listeners.ListenerManager;
import com.spigot.practice.match.Ladder;
import com.spigot.practice.queue.RankedQueue;
import com.spigot.practice.queue.UnrankedQueue;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Practice extends JavaPlugin{

	private static Practice instance;
	private static WorldEditPlugin worldEditPlugin;
	private ArenaConfig arenaConfig;
	private ArenaManager arenaManager;

	@Override
	public void onEnable() {
		instance = this;
		arenaConfig = new ArenaConfig();
		arenaManager = new ArenaManager();

		worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

		this.arenaManager.createArenaWorld();
		this.arenaConfig.loadArenaConfigFile();
		this.arenaManager.loadDefaultArena();

		saveDefaultConfig();
		registerCommands();
		PlayerConfig.loadPlayersFile();
		new ListenerManager(this).registerEvents();

		for(Ladder ladder : Ladder.values()) {
			new UnrankedQueue(ladder);
			new RankedQueue(ladder);
		}

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

	public static WorldEditPlugin getWorldEditPlugin(){
		return worldEditPlugin;
	}

	private void registerCommands(){
		this.getCommand("setspawn").setExecutor(new SpawnCommand());
		this.getCommand("arena").setExecutor(new ArenaCommand());
		this.getCommand("party").setExecutor(new PartyCommand());
	}

	public ArenaConfig getArenaConfig(){
		return this.arenaConfig;
	}

	public ArenaManager getArenaManager() {
		return this.arenaManager;
	}
}
