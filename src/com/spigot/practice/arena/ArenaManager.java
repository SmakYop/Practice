package com.spigot.practice.arena;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.schematic.MCEditSchematicFormat;
import com.sk89q.worldedit.world.DataException;
import com.spigot.practice.Practice;
import com.spigot.practice.config.PracticeConfig;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ArenaManager {

    public int getArenasNumber(){
        File arenaFile = new File(Practice.getInstance().getDataFolder(), "arena.yml");
        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);
        try{
            return fileConfiguration.getConfigurationSection("arena").getKeys(false).size();
        }catch (NullPointerException e){
            Practice.log("ERROR: Can't load arena number: " + e.getMessage());
        }
        return 0;
    }

    public void loadDefaultArena(){
        if(Practice.getInstance().getArenaManager().getArenasNumber() == 0) return;
        for(int i = 1; i<= Practice.getInstance().getArenaManager().getArenasNumber(); i++){
            String arenaId = ""+i;
            Arena arena = new Arena(arenaId);
            arena.setName(Practice.getInstance().getArenaConfig().getName(arenaId));
            arena.setCanBuild(Practice.getInstance().getArenaConfig().canBuild(arenaId));
            arena.setFirstPlayerLocation(Practice.getInstance().getArenaConfig().getFirstPosLocation(arenaId));
            arena.setSecondPlayerLocation(Practice.getInstance().getArenaConfig().getSecondPosLocation(arenaId));
            arena.setSpectateLocation(Practice.getInstance().getArenaConfig().getSpectatorLocation(arenaId));
            arena.setCenterLocation(Practice.getInstance().getArenaConfig().getCenterLocation(arenaId));
        }
    }

    public void pasteArenaSchematic(String schematicName, Location arenaCenterLocation){
        File arenaFile = new File(Practice.getInstance().getDataFolder()+"/templates/arena_"+schematicName+".schematic");
        EditSession editSession = Practice.getWorldEditPlugin().getWorldEdit().getEditSessionFactory().getEditSession(new BukkitWorld(Bukkit.getWorld(PracticeConfig.ARENA_WORLD_NAME)), 100000000);
        try{
            CuboidClipboard clipboard = MCEditSchematicFormat.getFormat(arenaFile).load(arenaFile);
            clipboard.paste(editSession, new Vector(arenaCenterLocation.getX(), arenaCenterLocation.getY(), arenaCenterLocation.getZ()), false);
        }catch (MaxChangedBlocksException|DataException|IOException e){
            Practice.log("ERROR: can't paste arena schematic, contact an administrator");
        }
    }

    public void createArenaWorld(){
        World arenaWorld = Bukkit.getWorld("arena");
        if(arenaWorld == null){
            Practice.log("World arena doesn't not exist. Creating world arena...");

            WorldCreator worldCreator = new WorldCreator("arena");
            worldCreator.generator(new ChunkGenerator() {
                @Override
                public byte[] generate(World world, Random random, int x, int z) {
                    return new byte[32768];
                }
            });
            worldCreator.generateStructures(false);

            World world = worldCreator.createWorld();
            world.setDifficulty(Difficulty.PEACEFUL);
            world.save();

            Practice.log("World created");
        }
    }

    public Arena selectRandomPlayableArena(boolean build){
        for(Arena arenas : Arena.getArenaList().values()){
            if(!arenas.isUsed() && arenas.canBuild() == build) return arenas;
        }
        return null;
    }
}