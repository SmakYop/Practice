package com.spigot.practice.ladder;

import com.spigot.practice.kits.Kit;
import fr.minuskube.inv.ClickableItem;

import java.util.HashMap;

public class Ladder {

    private String name;
    private boolean ranked;
    private boolean canBuild;
    private ClickableItem clickableItem;
    private Kit kit;
    private int itemSlot;

    private static HashMap<String, Ladder> ladders = new HashMap<>();

    Ladder(String name){
        this.name = name;
        ladders.put(name, this);
    }

    public static HashMap<String, Ladder> getLadders() {
        return ladders;
    }

    public static Ladder getLadder(String name){
        return ladders.get(name);
    }

    public String getName() {
        return name;
    }

    public boolean isRanked() {
        return ranked;
    }

    public boolean canBuild() {
        return canBuild;
    }

    public ClickableItem getClickableItem() {
        return clickableItem;
    }

    public Kit getKit() {
        return kit;
    }

    public int getItemSlot() {
        return itemSlot;
    }

    public void setRanked(boolean ranked) {
        this.ranked = ranked;
    }

    public void setBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    public void setClickableItemItem(ClickableItem clickableItem) {
        this.clickableItem = clickableItem;
    }

    public void setKit(Kit kit) {
        this.kit = kit;
    }

    public void setItemSlot(int itemSlot) {
        this.itemSlot = itemSlot;
    }
}
