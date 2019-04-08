package com.spigot.practice;

import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.match.GameType;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PracticePlayer {

	private Player player;
	private String name;
	private UUID uuid;
	private boolean inFight;
	private boolean isSpectator;
	private boolean hasParty;
	private int globalElo;

	private static HashMap<Player, PracticePlayer> practicePlayers = new HashMap<>();
	private static HashMap<GameType, Integer> eloByMatchType = new HashMap<>();

	public PracticePlayer(Player paramPlayer){
		if(!PlayerConfig.exists(paramPlayer)) PlayerConfig.registerPlayer(paramPlayer);
		this.player = paramPlayer;
		this.name = paramPlayer.getName();
		this.uuid = paramPlayer.getUniqueId();
		this.inFight = false;
		this.globalElo = PlayerConfig.getGlobalElo(paramPlayer);
		practicePlayers.put(paramPlayer, this);

		eloByMatchType.put(GameType.DEBUFF, PlayerConfig.getDebuffElo(paramPlayer));
		eloByMatchType.put(GameType.NO_DEBUFF, PlayerConfig.getNoDebuffElo(paramPlayer));
		eloByMatchType.put(GameType.BUILDUHC, PlayerConfig.getBuildUhcElo(paramPlayer));
		eloByMatchType.put(GameType.COMBO, PlayerConfig.getComboElo(paramPlayer));
		eloByMatchType.put(GameType.GAPPLE, PlayerConfig.getGappleElo(paramPlayer));
		eloByMatchType.put(GameType.NO_ENCHANT, PlayerConfig.getNoEnchantElo(paramPlayer));
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

	public int getGlobalElo(){
		return this.globalElo;
	}

	public boolean isInFight(){
		return this.inFight;
	}

	public boolean isSpectator() {
		return isSpectator;
	}

	public void setFight(boolean inFight){
		this.inFight = inFight;
	}

	public void setSpectator(boolean spectator) {
		isSpectator = spectator;
	}


	public int getElo(GameType gameType){
		return eloByMatchType.get(gameType);
	}

	public void addElo(GameType gameType, int elo){
		eloByMatchType.put(gameType, eloByMatchType.get(gameType)+elo);
	}

	public void removeElo(GameType gameType, int elo){
		eloByMatchType.put(gameType, eloByMatchType.get(gameType)-elo);
	}
}
