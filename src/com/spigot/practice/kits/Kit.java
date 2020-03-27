package com.spigot.practice.kits;

import java.util.HashMap;

public class Kit {

    private String name;

    private static HashMap<String, Kit> kits = new HashMap<>();

    public Kit(String name){
        this.name = name;
        kits.put(name, this);
    }

    public static Kit getKits(String name){
        return kits.get(name);
    }

    public String getName() {
        return name;
    }
}
