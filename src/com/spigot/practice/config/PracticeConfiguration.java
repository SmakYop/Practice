package com.spigot.practice.config;

import com.spigot.practice.Practice;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PracticeConfiguration {

	private static PracticeConfiguration instance = new PracticeConfiguration();

	private String prefix = Practice.getInstance().getConfig().getString("General.Prefix").replace('&', '§');
	private String scoreboardName = Practice.getInstance().getConfig().getString("General.ScoreboardName").replace('&', '§');
	private int startPlayerElo = Practice.getInstance().getConfig().getInt("General.StartPlayerElo");
	private Location spawnLocation = new Location(Bukkit.getWorld(Practice.getInstance().getConfig().getString("Spawn.world")), Practice.getInstance().getConfig().getDouble("Spawn.x"),
			Practice.getInstance().getConfig().getDouble("Spawn.y"), Practice.getInstance().getConfig().getDouble("Spawn.z"));

	public String getPrefix(){
		return this.prefix;
	}

	public String getScoreboardName(){
		return this.scoreboardName;
	}

	public int getStartPlayerElo(){
		return this.startPlayerElo;
	}

	public Location getSpawnLocation(){
		return this.spawnLocation;
	}

	public static PracticeConfiguration getInstance(){
		return instance;
	}
}
