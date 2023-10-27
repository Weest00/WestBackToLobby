package br.com.west.backtolobby.managers;

import java.util.HashSet;
import java.util.Set;

public class LobbyManager {

    private Set<String> chat = new HashSet<>();

    private Set<String> chatAdmin = new HashSet<>();

    public Set<String> getChat() {
        return chat;
    }

    public Set<String> getChatAdmin() {
        return chatAdmin;
    }
}
