package com.spigot.practice.config;

import com.spigot.practice.Practice;

import java.io.File;
import java.io.IOException;

public class LadderConfig {

    private File ladderFile = new File(Practice.getInstance().getDataFolder(), "ladder.yml");

    public void loadLadderConfigFile(){
        try{
            if(!ladderFile.exists()) ladderFile.createNewFile();
        }catch (IOException e){
            Practice.log("ERROR: Problem with ladder file.. Try to restart or contact an administrator");
        }
    }
}
