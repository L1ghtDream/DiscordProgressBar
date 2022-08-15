package dev.lightdream.discordbot.dto.server_status;

public class PlayerData {

    public int online;
    public int max;

    public PlayerData(int online, int max) {
        this.online = online;
        this.max = max;
    }

    public PlayerData() {
    }

}
