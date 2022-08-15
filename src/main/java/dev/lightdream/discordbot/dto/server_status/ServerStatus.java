package dev.lightdream.discordbot.dto.server_status;

public class ServerStatus {

    public PlayerData players;

    public ServerStatus(PlayerData players) {
        this.players = players;
    }

    public ServerStatus() {
    }

}
