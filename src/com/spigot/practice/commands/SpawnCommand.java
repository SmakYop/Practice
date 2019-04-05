package com.spigot.practice.commands;

import com.spigot.practice.Practice;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender paramCommandSender, Command command, String label, String[] args) {

		Player player = (Player) paramCommandSender;
		if(player.hasPermission("practice.setspawn")){
			if(args.length == 0){

				Location playerLocation = player.getLocation();
				Practice.getInstance().getConfig().set("Spawn.world", playerLocation.getWorld().getName());
				Practice.getInstance().getConfig().set("Spawn.x", playerLocation.getX());
				Practice.getInstance().getConfig().set("Spawn.y", playerLocation.getY());
				Practice.getInstance().getConfig().set("Spawn.z", playerLocation.getZ());

				player.sendMessage("§aSpawn Location has been set!");

			}
		}else{
			player.sendMessage("§cYou don't have permission.");
		}

		return false;
	}
}
