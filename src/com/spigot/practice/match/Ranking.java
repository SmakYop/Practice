package com.spigot.practice.match;

public enum Ranking {

    UNRANKED("Unranked"),
    RANKED("Ranked");

    private String rankingName;

    Ranking(String rankingName){
        this.rankingName = rankingName;
    }

    public String getRankingName() {
        return rankingName;
    }
}
