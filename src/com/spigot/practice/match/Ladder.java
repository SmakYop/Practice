package com.spigot.practice.match;

public enum Ladder {

    DEBUFF("Debuff", "Pots"),
    NO_DEBUFF("NoDebuff", "Pots"),
    BUILDUHC("BuildUHC", "Build"),
    COMBO("Combo", "Pots"),
    GAPPLE("Gapple", "Pots"),
    NO_ENCHANT("NoEnchant", "Pots");

    private String typeName;
    private String arenaType;

    Ladder(String typeName, String arenaType){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getArenaType() {
        return arenaType;
    }
}
