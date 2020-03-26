package com.spigot.practice.match;

public enum Ranking {

    UNRANKED("Unranked"),
    RANKED("Ranked");

    private String name;

    Ranking(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
