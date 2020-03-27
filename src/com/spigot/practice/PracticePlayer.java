package com.spigot.practice;

import com.spigot.practice.config.PlayerConfig;
import com.spigot.practice.inventory.ItemsManager;
import com.spigot.practice.match.Match;
import com.spigot.practice.queue.Queue;
import com.spigot.practice.scoreboard.Scoreboard;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PracticePlayer {

	private Player player;
	private boolean inFight;
	private boolean inQueue;
	private boolean isSpectator;
	private boolean hasParty;
	private int globalElo;
	private Queue queue;
	private Scoreboard scoreboard;
	private Match match;

	private static HashMap<Player, PracticePlayer> practicePlayers = new HashMap<>();
	//private static HashMap<LadderManager, Integer> eloByMatchType = new HashMap<>();

	public PracticePlayer(Player paramPlayer){
		if(!PlayerConfig.exists(paramPlayer)) PlayerConfig.registerPlayer(paramPlayer);
		this.player = paramPlayer;
		this.inFight = false;
		this.inQueue = false;
		this.queue = null;
		this.match = null;
		this.globalElo = PlayerConfig.getGlobalElo(paramPlayer);
		practicePlayers.put(paramPlayer, this);

		/*eloByMatchType.put(LadderManager.DEBUFF, PlayerConfig.getDebuffElo(paramPlayer));
		eloByMatchType.put(LadderManager.NO_DEBUFF, PlayerConfig.getNoDebuffElo(paramPlayer));
		eloByMatchType.put(LadderManager.BUILDUHC, PlayerConfig.getBuildUhcElo(paramPlayer));
		eloByMatchType.put(LadderManager.COMBO, PlayerConfig.getComboElo(paramPlayer));
		eloByMatchType.put(LadderManager.GAPPLE, PlayerConfig.getGappleElo(paramPlayer));
		eloByMatchType.put(LadderManager.NO_ENCHANT, PlayerConfig.getNoEnchantElo(paramPlayer));*/
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

	/*public int getElo(LadderManager ladderManager){
		return eloByMatchType.get(ladderManager);
	}

	public void addElo(LadderManager ladderManager, int elo){
		eloByMatchType.put(ladderManager, eloByMatchType.get(ladderManager)+elo);
	}

	public void removeElo(LadderManager ladderManager, int elo){
		eloByMatchType.put(ladderManager, eloByMatchType.get(ladderManager)-elo);
	}*/

	public void sendLobbyItems(){
		ItemsManager unranked = new ItemsManager(Material.IRON_SWORD, "§eUnranked", new String[]{"§7Right-click to play unranked matches"});
		ItemsManager ranked = new ItemsManager(Material.DIAMOND_SWORD, "§eRanked", new String[]{"§7Right-click to play ranked matches"});

		this.player.getInventory().setItem(0, unranked.toItemStack());
		this.player.getInventory().setItem(1, ranked.toItemStack());
	}

	public void sendLobbyScoreboard(){
		scoreboard = new Scoreboard(" §6§lPRACTICE Spigot ");
		scoreboard.add(" ", 8);
		scoreboard.add("§7Global Elo: §e" + this.globalElo, 7);
		scoreboard.add("  ", 6);
		scoreboard.add("§7In party: §c✖", 5);
		scoreboard.add("§7In queue: " + getQueueString(), 4);
		scoreboard.add("   ", 3);
		scoreboard.add("§7Name: §e" + this.player.getName(), 2);
		scoreboard.add("", 1);
		scoreboard.add("§6mc.practice-server.net", 0);
		scoreboard.build();
		scoreboard.send(this.player);
	}

	public void sendDuelScoreboard(){
		scoreboard = new Scoreboard(" §6§lPRACTICE Spigot ");
		scoreboard.add(" ", 7);
		scoreboard.add("§7Opponent: §e" + match.getOpponent(this).getPlayer().getName(), 6);
		scoreboard.add("  ", 5);
		scoreboard.add("§7Ladder: §e" + match.getLadder().getName(), 4);
		scoreboard.add("§7Ranking: §e" + match.getRanking().getName(), 3);
		scoreboard.add("§7Arena: §6" + match.getArena().getName() + " §7(ID: #" + match.getArena().getId() + ")", 2);
		scoreboard.add("", 1);
		scoreboard.add("§6mc.practice-server.net", 0);
		scoreboard.build();
		scoreboard.send(this.player);
	}

	private String getQueueString(){
	    if(!inQueue) return "§c✖";
        return queue.getLadder().getName();
    }
}
