package com.spigot.practice.party;

import com.spigot.practice.PracticePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Party {

    private PracticePlayer owner;
    private boolean inQueue;
    private boolean isFull;
    private boolean inDuel;
    private int victory;
    private int defeat;
    private ArrayList<PracticePlayer> members = new ArrayList<>();

    private static HashMap<PracticePlayer, Party> parties = new HashMap<>();

    public Party(PracticePlayer owner){
        this.owner = owner;
        parties.put(owner, this);
        this.victory = 0;
        this.defeat = 0;
        this.inQueue = false;
        this.isFull = false;
        this.inDuel = false;
    }

    public static Party getParty(PracticePlayer owner){
        return parties.get(owner);
    }

    public ArrayList<PracticePlayer> getMembers() {
        return members;
    }

    public PracticePlayer getOwner() {
        return owner;

    }

    public void sendMessage(String message){
        for(PracticePlayer practicePlayer : getMembers()){
            practicePlayer.getPlayer().sendMessage(message);
        }
    }

    public void sendInfos(Player player){
        StringBuilder sb = new StringBuilder();
        for(PracticePlayer practicePlayer : getMembers()){
            sb.append(practicePlayer.getPlayer().getName()).append(", ");
        }

        player.sendMessage("§7------------ §6" + owner.getPlayer().getName() + "'s party §7------------");
        player.sendMessage("§7Members: §e" + sb.toString());
        player.sendMessage("§7Global Elo: §e" + getGlobalElo());
        player.sendMessage("");
        player.sendMessage("§7Victories: §e" + victory);
        player.sendMessage("§7Defeats: §e" + defeat);
        player.sendMessage("§7--------------------------------------------");
    }

    public void disband(){

    }

    public int getVictory() {
        return victory;
    }

    public int getDefeat() {
        return defeat;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isInDuel() {
        return inDuel;
    }

    public void joinParty(PracticePlayer practicePlayer){

    }

    public void leaveParty(PracticePlayer practicePlayer){

    }

    public int getGlobalElo(){
        int elo = 0;
        for(PracticePlayer practicePlayer : getMembers()){
           elo = elo + practicePlayer.getGlobalElo();
        }
        return (elo/getMembers().size());

    }

    public void sendInvitation(PracticePlayer practicePlayer){

    }
}
