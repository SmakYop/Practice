package com.spigot.practice.party;

import org.bukkit.entity.Player;

public class Party {

    private Player[] players;

    public Party(Player[] players){
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
}
