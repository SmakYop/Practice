package com.spigot.practice.match;

public enum Ladder {

    DEBUFF("Debuff", false),
    NO_DEBUFF("NoDebuff", false),
    BUILDUHC("BuildUHC", true),
    COMBO("Combo", false),
    GAPPLE("Gapple", false),
    NO_ENCHANT("NoEnchant", false);

    private String name;
    private boolean build;

    Ladder(String name, boolean build){
        this.name = name;
        this.build = build;
    }

    public String getName() {
        return name;
    }

    public boolean canBuild() {
        return build;
    }
}
