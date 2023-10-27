package br.com.west.backtolobby.listeners;

import br.com.west.backtolobby.WestBackToLobby;
import br.com.west.backtolobby.utils.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {


    @EventHandler
    void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String playerName = player.getName();
        String message = event.getMessage();

        if (WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().contains(playerName)) {
            event.setCancelled(true);

            if (message.equalsIgnoreCase("cancelar")) {
                WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().remove(playerName);
                player.sendMessage("§aProcesso cancelado com sucesso.");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
                return;
            }

            if (message.equals(playerName)){
                player.sendMessage("§cVocê não pode enviar a si mesmo para o lobby, use a outra opção.");
                player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
                return;
            }

            Player playerChat = Bukkit.getPlayer(message);

            if (playerChat == null) {
                player.sendMessage("§cEste jogador não existe ou está offline no momento.");
                player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
                return;
            }

            WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().remove(playerName);
            TitleAPI.sendTitle(player, "§a§lLOBBY!", "§fEstamos enviando o jogador §a" + playerChat.getName() + "§f ao lobby do servidor.");
            TitleAPI.sendTitle(playerChat, "§a§lLOBBY!", "§fVocê foi enviado ao lobby do servidor.");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
            WestBackToLobby.getInstance().sendPlayer(playerChat, WestBackToLobby.getInstance().getConfig().getString("lobby-name"));

        }


        if (WestBackToLobby.getInstance().getLobbyManager().getChat().contains(playerName)) {
            event.setCancelled(true);

            if (message.equalsIgnoreCase("cancelar")) {
                WestBackToLobby.getInstance().getLobbyManager().getChat().remove(playerName);
                player.sendMessage("§aProcesso cancelado com sucesso.");
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
                return;
            }


            WestBackToLobby.getInstance().getLobbyManager().getChat().remove(playerName);
            TitleAPI.sendTitle(player, "§a§lLOBBY!", "§fVocê foi enviado ao lobby do servidor.");
            player.playSound(player.getLocation(), Sound.LEVEL_UP, 1F, 1F);
            WestBackToLobby.getInstance().sendPlayer(player, "lobby");

        }
    }
}
