package dev.lightdream.discordbot.util;


import com.google.gson.Gson;
import dev.lightdream.discordbot.dto.server_status.ServerStatus;
import dev.lightdream.messagebuilder.MessageBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PlayerCountHelper {

    public static MessageBuilder API_URL = new MessageBuilder("https://api.mcsrvstat.us/2/%ip%");

    public static int getPlayerCount(String ip) {
        try {
            String urlString = API_URL.parse("ip", ip).parse();

            URL url = new URL(urlString);

            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            ServerStatus status = new Gson().fromJson(response.toString(), ServerStatus.class);

            return status.players.online;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
