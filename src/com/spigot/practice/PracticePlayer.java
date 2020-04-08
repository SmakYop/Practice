package com.spigot.practice;

import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.inventory.ItemsManager;
import com.spigot.practice.match.Match;
import com.spigot.practice.party.Party;
import com.spigot.practice.queue.Queue;
import com.spigot.practice.scoreboard.PlayerScoreboard;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PracticePlayer {

	private Player player;
	private boolean inFight;
	private boolean inQueue;
	private boolean isSpectator;
	private int globalElo;
	private Queue queue;
	private Match match;
	private PlayerScoreboard playerScoreboard;
	private Party party;

	private static HashMap<Player, PracticePlayer> practicePlayers = new HashMap<>();

	public PracticePlayer(){}

	public PracticePlayer(Player paramPlayer){
		if(!PlayerConfig.exists(paramPlayer)) PlayerConfig.registerPlayer(paramPlayer);
		this.player = paramPlayer;
		this.inFight = false;
		this.inQueue = false;
		this.queue = null;
		this.match = null;
		this.party = null;
		this.globalElo = PlayerConfig.getGlobalElo(paramPlayer);
		practicePlayers.put(paramPlayer, this);
		this.playerScoreboard = new PlayerScoreboard(this);
	}

	public static PracticePlayer get(Player paramPlayer){
		return practicePlayers.get(paramPlayer);
	}

	public Player getPlayer(){
		return this.player;
	}

	public int getGlobalElo(){
		return this.globalElo;
	}

	public Queue getQueue() {
		return queue;
	}

	public Match getMatch() {
		return match;
	}

	public PlayerScoreboard getScoreboard() {
		return playerScoreboard;
	}

	public Party getParty() {
		return party;
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

	public void setSpectator(boolean spectator) {
		isSpectator = spectator;
	}

	public void setQueue(Queue queue){
		this.inQueue = true;
		this.queue = queue;
	}

	public void removeQueue(){
		this.inQueue = false;
		this.queue = null;
	}

	public void setMatch(Match match){
		this.inFight = true;
		this.match = match;
	}

	public void removeMatch(){
		this.inFight = false;
		this.match = null;
	}

	public String getQueueString(){
	    if(!inQueue) return "§c✖";
        return "§e" + queue.getLadder().getName() + " §7(" + queue.getRanking().getName() + ")";
    }

	public String getPartyString(){
		if(party == null) return "§c✖";
		return "§e" + party.getOwner().getPlayer().getName() + "'s party";
	}
}
