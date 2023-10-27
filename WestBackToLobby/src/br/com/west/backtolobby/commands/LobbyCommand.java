package br.com.west.backtolobby.commands;

import br.com.west.backtolobby.WestBackToLobby;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LobbyCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String lbl, String[] args) {
        if (sender instanceof Player){


            Player player = (Player) sender;

            if (args.length == 0){
                player.openInventory(WestBackToLobby.getInstance().getLobbyInventory().openLobbyInventory(player));
            }



        } else {
            Bukkit.getConsoleSender().sendMessage("§e§lLOBBY! §fApenas jogadores podem usar este comando.");
        }


        return false;
    }
}
