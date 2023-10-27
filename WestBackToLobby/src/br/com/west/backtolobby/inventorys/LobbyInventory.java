package br.com.west.backtolobby.inventorys;

import br.com.west.backtolobby.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyInventory {


    public Inventory openLobbyInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§7Voltar para o lobby");


        if (player.hasPermission("westbacktolobby.admin")) {
            inventory.setItem(12, ItemBuilder.add(Material.EYE_OF_ENDER, "§aVoltar para o lobby (Administradores)", "§7Clique para enviar um jogador ao lobby."));
        } else {
            inventory.setItem(12, ItemBuilder.add(Material.BARRIER, "§aVoltar para o lobby (Administradores)", "§7Apenas administradores podem usar este comando."));
        }
        inventory.setItem(13, ItemBuilder.add(Material.WATCH, "§aVoltar para o lobby", "§7Clique para voltar pro lobby do servidor."));

        inventory.setItem(14, ItemBuilder.addSkull("http://textures.minecraft.net/texture/c4e490e1658bfde4d4ef1ea7cd646c5353377905a1369b86ee966746ae25ca7", "§aFechar menu", "§7Clique para fechar este menu."));


        return inventory;
    }
}
