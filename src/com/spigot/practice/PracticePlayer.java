package com.spigot.practice;

import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.inventory.ItemsManager;
import com.spigot.practice.match.Ladder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PracticePlayer {

	private Player player;
	private String name;
	private UUID uuid;
	private boolean inFight;
	private boolean inQueue;
	private boolean isSpectator;
	private boolean hasParty;
	private int globalElo;

	private static HashMap<Player, PracticePlayer> practicePlayers = new HashMap<>();
	private static HashMap<Ladder, Integer> eloByMatchType = new HashMap<>();

	public PracticePlayer(Player paramPlayer){
		if(!PlayerConfig.exists(paramPlayer)) PlayerConfig.registerPlayer(paramPlayer);
		this.player = paramPlayer;
		this.name = paramPlayer.getName();
		this.uuid = paramPlayer.getUniqueId();
		this.inFight = false;
		this.inQueue = false;
		this.globalElo = PlayerConfig.getGlobalElo(paramPlayer);
		practicePlayers.put(paramPlayer, this);

		eloByMatchType.put(Ladder.DEBUFF, PlayerConfig.getDebuffElo(paramPlayer));
		eloByMatchType.put(Ladder.NO_DEBUFF, PlayerConfig.getNoDebuffElo(paramPlayer));
		eloByMatchType.put(Ladder.BUILDUHC, PlayerConfig.getBuildUhcElo(paramPlayer));
		eloByMatchType.put(Ladder.COMBO, PlayerConfig.getComboElo(paramPlayer));
		eloByMatchType.put(Ladder.GAPPLE, PlayerConfig.getGappleElo(paramPlayer));
		eloByMatchType.put(Ladder.NO_ENCHANT, PlayerConfig.getNoEnchantElo(paramPlayer));
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

	public boolean isInQueue() {
		return inQueue;
	}

	public boolean isSpectator() {
		return isSpectator;
	}

	public void setInFight(boolean inFight){
		this.inFight = inFight;
	}

	public void setInQueue(boolean inQueue) {
		this.inQueue = inQueue;
	}

	public void setSpectator(boolean spectator) {
		isSpectator = spectator;
	}

	public int getElo(Ladder ladder){
		return eloByMatchType.get(ladder);
	}

	public void addElo(Ladder ladder, int elo){
		eloByMatchType.put(ladder, eloByMatchType.get(ladder)+elo);
	}

	public void removeElo(Ladder ladder, int elo){
		eloByMatchType.put(ladder, eloByMatchType.get(ladder)-elo);
	}

	public void sendLobbyItems(){
		ItemsManager unranked = new ItemsManager(Material.IRON_SWORD, "§eUnranked", new String[]{"§7Right-click to play unranked matches"});
		ItemsManager ranked = new ItemsManager(Material.DIAMOND_SWORD, "§eRanked", new String[]{"§7Right-click to play ranked matches"});

		this.player.getInventory().setItem(0, unranked.toItemStack());
		this.player.getInventory().setItem(1, ranked.toItemStack());
	}
}
