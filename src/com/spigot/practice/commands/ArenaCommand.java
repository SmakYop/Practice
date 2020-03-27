package com.spigot.practice.commands;

import com.spigot.practice.Practice;
import com.spigot.practice.PracticePlayer;
import com.spigot.practice.arena.Arena;
import com.spigot.practice.config.PracticeConfig;
import com.spigot.practice.queue.Queue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;
        if(!(player.hasPermission(PracticeConfig.PERMISSION_ARENA) || player.isOp())) return true;

        if(PracticePlayer.get(player).isInQueue()) player.sendMessage("§cCan't do this in queue.");

        if(args.length == 0){
            player.sendMessage("§7---------------- §eArena Commands §7----------------");
            player.sendMessage("§7> §e/arena create");
            player.sendMessage("§7> §e/arena setname [arena ID] [arena name]");
            player.sendMessage("§7> §e/arena setcenter [arena ID]");
            player.sendMessage("§7> §e/arena setpos1 [arena ID]");
            player.sendMessage("§7> §e/arena setpos2 [arena ID]");
            player.sendMessage("§7> §e/arena setspectator [arena ID]");
            player.sendMessage("§7> §e/arena setbuild [arena ID]");
            player.sendMessage("§7> §e/arena save [arena ID]");
            player.sendMessage("§7-----------------------------------------------");
            return true;
        }

        if(args.length == 1){
            if(!(args[0].equalsIgnoreCase("tpworld") || args[0].equalsIgnoreCase("create"))){
                player.sendMessage("§cError: type /arena to see all posibilities.");
                return true;
            }
            if(args[0].equalsIgnoreCase("tpworld")){
                // Check if arena world exists. If not : create and tp after
                return true;
            }
            if(args[0].equalsIgnoreCase("create")){
                int id = Practice.getInstance().getArenaManager().getArenasNumber()+1;
                new Arena("" + id);
                player.sendMessage("§aYou successfully created a new arena! §7(ID: #" + id + ")");
                return true;
            }

        }

        String arenaId = args[1];

        if(args.length == 2){
            if(!(args[0].equalsIgnoreCase("setpos1") || args[0].equalsIgnoreCase("setpos2") || args[0].equalsIgnoreCase("setcenter") ||
                    args[0].equalsIgnoreCase("setspectator") || args[0].equalsIgnoreCase("setbuild") || args[0].equalsIgnoreCase("duplicate") || args[0].equalsIgnoreCase("save"))){
                player.sendMessage("§cError: type /arena to see all posibilities.");
                return true;
            }

            if(Arena.get(arenaId) == null){
                player.sendMessage("§cThis arena doesn't exists.");
                return true;
            }

            Arena arena = Arena.get(arenaId);

            if(args[0].equalsIgnoreCase("setpos1")){
                arena.setFirstPlayerLocation(player.getLocation());
                player.sendMessage("§aYou successfully set the first player position for the arena §7(ID: #" + arenaId + ")");
                return true;
            }
            if(args[0].equalsIgnoreCase("setpos2")){
                arena.setSecondPlayerLocation(player.getLocation());
                player.sendMessage("§aYou successfully set the second player position for the arena §7(ID: #" + arenaId + ")");
                return true;
            }
            if(args[0].equalsIgnoreCase("setcenter")){
                arena.setCenterLocation(player.getLocation());
                player.sendMessage("§aYou successfully set the center location for the arena §7(ID: #" + arenaId + ")");
                return true;
            }
            if(args[0].equalsIgnoreCase("setspectator")){
                arena.setSpectateLocation(player.getLocation());
                player.sendMessage("§aYou successfully set the spectator location for the arena §7(ID: #" + arenaId + ")");
                return true;
            }
            if(args[0].equalsIgnoreCase("setbuild")){
                arena.setCanBuild(!arena.canBuild());
                player.sendMessage("§aYou successfully set the build permission for the arena §7(ID: #" + arenaId + ")");
                return true;
            }
            if(args[0].equalsIgnoreCase("save")){
                // Check si tout est set
                arena.save();
                player.sendMessage("§aYou successfully saved the arena §7(ID: #" + arenaId + ")");
                return true;
            }

        }

        if(args.length == 3){
            if(!args[0].equalsIgnoreCase("setname")){
                player.sendMessage("§cError: type /arena to see all posibilities.");
                return true;
            }

            if(Arena.get(arenaId) == null){
                player.sendMessage("§cThis arena doesn't exists.");
                return true;
            }

            Arena.get(arenaId).setName(args[2]);
            player.sendMessage("§aYou successfully set the name §7" + args[2] + " §afor tha arena §7(ID: #" + arenaId + ")");
            return true;
        }

        if(args.length > 3){
            player.sendMessage("§cError: type /arena to see all posibilities.");
            return true;
        }

        return false;
    }
}
