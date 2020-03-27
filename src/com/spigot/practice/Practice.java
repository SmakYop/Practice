package com.spigot.practice;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.spigot.practice.arena.ArenaManager;
import com.spigot.practice.commands.ArenaCommand;
import com.spigot.practice.commands.PartyCommand;
import com.spigot.practice.commands.SpawnCommand;
import com.spigot.practice.config.ArenaConfig;
import com.spigot.practice.config.LadderConfig;
import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.elo.EloManager;
import com.spigot.practice.ladder.Ladder;
import com.spigot.practice.ladder.LadderManager;
import com.spigot.practice.listeners.ListenerManager;
import com.spigot.practice.queue.RankedQueue;
import com.spigot.practice.queue.UnrankedQueue;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Practice extends JavaPlugin{

	private static Practice instance;
	private static WorldEditPlugin worldEditPlugin;
	private ArenaConfig arenaConfig;
	private ArenaManager arenaManager;
	private EloManager eloManager;
	private LadderConfig ladderConfig;
	private LadderManager ladderManager;

	@Override
	public void onEnable() {
		instance = this;
		arenaConfig = new ArenaConfig();
		arenaManager = new ArenaManager();
		eloManager = new EloManager();
		ladderConfig = new LadderConfig();
		ladderManager = new LadderManager();

		worldEditPlugin = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");

		this.arenaManager.createArenaWorld();
		this.arenaConfig.loadArenaConfigFile();
		this.ladderConfig.loadLadderConfigFile();
		this.arenaManager.loadDefaultArena();
		this.ladderManager.loadLadders();

		saveDefaultConfig();
		registerCommands();
		PlayerConfig.loadPlayersFile();
		new ListenerManager(this).registerEvents();

		for(Ladder ladder : Ladder.getLadders().values()){
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

	public EloManager getEloManager() {
		return this.eloManager;
	}

	public LadderConfig getLadderConfig() {
		return ladderConfig;
	}

	public LadderManager getLadderManager() {
		return ladderManager;
	}
}
