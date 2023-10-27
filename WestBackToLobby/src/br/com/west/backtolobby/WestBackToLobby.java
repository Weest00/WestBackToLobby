package br.com.west.backtolobby;

import br.com.west.backtolobby.commands.LobbyCommand;
import br.com.west.backtolobby.inventorys.LobbyInventory;
import br.com.west.backtolobby.listeners.AsyncPlayerChatListener;
import br.com.west.backtolobby.listeners.InventoryClickListener;
import br.com.west.backtolobby.managers.LobbyManager;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class WestBackToLobby extends JavaPlugin {


    private static WestBackToLobby instance;

    private LobbyManager lobbyManager;

    private LobbyInventory lobbyInventory;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        lobbyManager = new LobbyManager();
        lobbyInventory = new LobbyInventory();
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        registerCommands();
        registerListeners();
        Bukkit.getConsoleSender().sendMessage("§e§lINFO: §fPlugin feito por west: https://gamersboard.com.br/profile/46546-westdev/");

    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("lobby").setExecutor(new LobbyCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);
    }

    public void sendPlayer(Player player, String serverString) {
        ByteArrayDataOutput dataOutput = ByteStreams.newDataOutput();
        dataOutput.writeUTF("Connect");
        dataOutput.writeUTF(serverString);
        player.sendPluginMessage(this, "BungeeCord", dataOutput.toByteArray());
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public LobbyInventory getLobbyInventory() {
        return lobbyInventory;
    }

    public static WestBackToLobby getInstance() {
        return instance;
    }
}
