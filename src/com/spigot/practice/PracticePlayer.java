package com.spigot.practice;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PracticePlayer {

	private Player player;
	private String name;
	private UUID uuid;
	private boolean inFight;
	private int globalElo;

	private static Map<Player, PracticePlayer> practicePlayers = new HashMap<>();

	public PracticePlayer(Player paramPlayer){
		this.player = paramPlayer;
		this.name = paramPlayer.getName();
		this.uuid = paramPlayer.getUniqueId();
		this.inFight = false;
		practicePlayers.put(paramPlayer, this);
	}

	public static PracticePlayer get(Player paramPlayer){
		return practicePlayers.get(paramPlayer);
	}

	public Player getPlayer(){
		return this.player;
	}

	public String getName(){
		return this.name;
	}

	public UUID getUUID(){
		return this.uuid;
	}

	public boolean isInFight(){
		return this.inFight;
	}

	public void setFight(boolean inFight){
		this.inFight = inFight;
	}
}
