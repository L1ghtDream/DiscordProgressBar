package dev.lightdream.discordprogressbar.dto.config;

import dev.lightdream.discordprogressbar.Main;
import dev.lightdream.discordprogressbar.dto.Bar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Data {

    public HashMap<String, Bar> bars = new HashMap<>();

    public void save(){
        Main.instance.fileManager.save(this);
    }

}
