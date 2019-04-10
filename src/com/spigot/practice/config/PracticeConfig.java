package com.spigot.practice.config;

import com.spigot.practice.Practice;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class PracticeConfig {

	public static String PREFIX = Practice.getInstance().getConfig().getString("General.prefix").replace('&', '§');
	public static int START_PLAYER_ELO = Practice.getInstance().getConfig().getInt("General.start_player_elo");
	public static int DEFAULT_ARENA_NUMBER = Practice.getInstance().getConfig().getInt("Arena.number_default_arena");
	public static Location SPAWN_LOCATION = new Location(Bukkit.getWorld(Practice.getInstance().getConfig().getString("Spawn.world")), Practice.getInstance().getConfig().getDouble("Spawn.x"), Practice.getInstance().getConfig().getDouble("Spawn.y"), Practice.getInstance().getConfig().getDouble("Spawn.z"));

	// Messages Configuration
	public static String ADDED_QUEUE = "";

	// Inventory Configuration
	public static String UNRANKED_TITLE = "§eUnranked Matches";
	public static String RANKED_TITLE = "§eRanked Matches";

}
