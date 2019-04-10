package com.spigot.practice.commands;

import com.spigot.practice.PracticePlayer;
import com.spigot.practice.arena.Arena;
import com.spigot.practice.match.GameType;
import com.spigot.practice.match.Match;
import com.spigot.practice.match.Ranking;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MatchCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!(sender.isOp())) sender.sendMessage("§cVous devez être op");
        if(args.length != 3) {
            sender.sendMessage("§cCommande invalide : /match forcestart [PLAYER 1] [PLAYER 2]");
            return false;
        }

        if(args[0].equalsIgnoreCase("forcestart")){

            Player firstPlayer = Bukkit.getPlayer(args[1]);
            Player secondPlayer = Bukkit.getPlayer(args[2]);

            if(!(firstPlayer.isOnline() || secondPlayer.isOnline())){
                sender.sendMessage("§cJoueurs invalides");
                return true;
            }

            PracticePlayer firstPracticePlayer = PracticePlayer.get(firstPlayer);
            PracticePlayer secondPracticePlayer = PracticePlayer.get(secondPlayer);

            Arena arena = new Arena("#"+(Arena.getArenaList().size()+1),new Location(Bukkit.getWorld("world"),0,0,0),new Location(Bukkit.getWorld("world"),0,0,0));
            arena.createArena();
            Match match = new Match(firstPracticePlayer, secondPracticePlayer, arena, GameType.NO_DEBUFF, Ranking.UNRANKED);
            match.createMatch();

            return true;

        }

        sender.sendMessage("§cCommande invalide : /match forcestart [PLAYER 1] [PLAYER 2]");

        return false;
    }
}
