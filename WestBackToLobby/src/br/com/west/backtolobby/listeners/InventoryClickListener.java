package br.com.west.backtolobby.listeners;

import br.com.west.backtolobby.WestBackToLobby;
import br.com.west.backtolobby.utils.TitleAPI;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        String playerName = player.getName();

        if (event.getClickedInventory() == null || event.getCurrentItem() == null){
            return;
        }


        if (event.getClickedInventory().getTitle().equals("§7Voltar para o lobby")){
            event.setCancelled(true);

            if (event.getSlot() == 12){
                if (WestBackToLobby.getInstance().getLobbyManager().getChat().contains(playerName) || WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().contains(playerName)) {
                    TitleAPI.sendTitle(player, "§c§lLOBBY!", "§fVocê já tem um processo em andamento.");
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CAT_MEOW,1F,1F);
                    return;
                }

                if (player.hasPermission("westbacktolobby.admin")){
                    WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().add(playerName);
                    player.sendMessage("§aDigite o nome do jogador a ser enviado ao lobby. \npara cancelar a ação digite §7§ncancelar§a.");
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.LEVEL_UP,1F,1F);
                } else {
                    TitleAPI.sendTitle(player, "§c§lLOBBY!", "§fSem permissão para usar este comando.");
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CAT_MEOW,1F,1F);
                }
            }


            if (event.getSlot() == 13){
                if (WestBackToLobby.getInstance().getLobbyManager().getChat().contains(playerName) || WestBackToLobby.getInstance().getLobbyManager().getChatAdmin().contains(playerName)) {
                    TitleAPI.sendTitle(player, "§c§lLOBBY!", "§fVocê já tem um processo em andamento.");
                    player.closeInventory();
                    player.playSound(player.getLocation(), Sound.CAT_MEOW, 1F, 1F);
                    return;
                }

                WestBackToLobby.getInstance().getLobbyManager().getChat().add(playerName);
                player.sendMessage("§aDigite qualquer coisa para ser enviado ao lobby. \npara cancelar a ação digite §7§ncancelar§a.");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.LEVEL_UP,1F,1F);

            }

            if (event.getSlot() == 14){
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.LEVEL_UP,1F,1F);

            }



        }

    }
}
