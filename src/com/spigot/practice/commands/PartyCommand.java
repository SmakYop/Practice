package com.spigot.practice.commands;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.party.Party;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;
        PracticePlayer practicePlayer = PracticePlayer.get(player);

        if(practicePlayer.isInQueue()) player.sendMessage("§cYou can't run this command in queue.");

        if(args.length == 0){
            player.sendMessage("§7---------------- §eParty Commands §7----------------");
            player.sendMessage("§7> §e/party create");
            player.sendMessage("§7> §e/party invite [player name]");
            player.sendMessage("§7> §e/party kick [player name]");
            player.sendMessage("§7> §e/party disband");
            player.sendMessage("§7> §e/party info [player name]");
            player.sendMessage("§7-----------------------------------------------");
            return true;
        }

        if(args.length == 1){
            if(!(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("disband"))){
                player.sendMessage("§cError: type /party to see all posibilities.");
                return true;
            }
            if(args[0].equalsIgnoreCase("create")){
                if(practicePlayer.getParty() != null){
                    player.sendMessage("§cYou already have a party.");
                    return true;
                }
                new Party(practicePlayer);
                player.sendMessage("§aYou successfully created your party.");
                practicePlayer.getScoreboard().update();
                return true;
            }
            if(args[0].equalsIgnoreCase("disband")){
                return true;
            }
        }

        if(args.length == 2){
            if(!(args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("kick") || args[0].equalsIgnoreCase("info"))){
                player.sendMessage("§cError: type /party to see all posibilities.");
                return true;
            }

            String targetName = args[1];
            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("§cThis player isn't online.");
                return true;
            }

            if(args[0].equalsIgnoreCase("invite")){
                if(practicePlayer.getParty() == null){
                    player.sendMessage("§cYou haven't a party.");
                    return true;
                }

                if(practicePlayer.getParty().getOwner() != practicePlayer){
                    player.sendMessage("§cYou aren't the owner of your party.");
                    return true;
                }
                if(PracticePlayer.get(Bukkit.getPlayer(targetName)).getParty() != null){
                    player.sendMessage("§cThis player already has a party.");
                    return true;
                }

                PracticePlayer practiceTarget = PracticePlayer.get(Bukkit.getPlayer(targetName));
                practicePlayer.getParty().sendInvitation(practiceTarget);

                return true;
            }
            if(args[0].equalsIgnoreCase("kick")){
                if(practicePlayer.getParty() == null){
                    player.sendMessage("§cYou haven't a party.");
                    return true;
                }

                if(practicePlayer.getParty().getOwner() != practicePlayer){
                    player.sendMessage("§cYou aren't the owner of your party.");
                    return true;
                }

                PracticePlayer practiceTarget = PracticePlayer.get(Bukkit.getPlayer(targetName));

                if(practicePlayer.getParty() != practiceTarget.getParty()){
                    player.sendMessage("§cThis player isn't in your party.");
                    return true;
                }




                return true;
            }
            if(args[0].equalsIgnoreCase("info")){
                String playerName = args[1];
                if(PracticePlayer.get(Bukkit.getPlayer(playerName)).getParty() == null){
                    player.sendMessage("§cThis player doesn't have a party.");
                    return true;
                }
                Party.getParty(PracticePlayer.get(Bukkit.getPlayer(playerName)).getParty().getOwner()).sendInfos(player);
                return true;
            }
        }

        return false;
    }
}
