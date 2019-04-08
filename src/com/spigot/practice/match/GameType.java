package com.spigot.practice.match;

public enum GameType {

    DEBUFF("Debuff"),
    NO_DEBUFF("NoDebuff"),
    BUILDUHC("BuildUHC"),
    COMBO("Combo"),
    GAPPLE("Gapple"),
    NO_ENCHANT("NoEnchant");

    private String typeName;

    GameType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
