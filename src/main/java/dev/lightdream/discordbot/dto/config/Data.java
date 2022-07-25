package dev.lightdream.discordbot.dto.config;

import dev.lightdream.discordbot.Main;
import dev.lightdream.discordbot.dto.Bar;

import java.util.HashMap;

public class Data {

    public HashMap<String, Bar> bars = new HashMap<>();

    public void save(){
        Main.instance.fileManager.save(this);
    }

}
