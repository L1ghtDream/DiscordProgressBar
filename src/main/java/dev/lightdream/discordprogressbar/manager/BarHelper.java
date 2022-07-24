package dev.lightdream.discordprogressbar.manager;

import dev.lightdream.discordprogressbar.Main;
import dev.lightdream.discordprogressbar.dto.Bar;
import net.dv8tion.jda.api.entities.MessageChannel;

public class BarHelper {

    public static boolean exists(String name) {
        return Main.instance.data.bars.get(name) != null;
    }

    public static void createBar(String name, MessageChannel channel) {
        Main.instance.data.bars.put(name, new Bar(name, channel));
        Main.instance.data.save();
    }

    public static void deleteBar(String name) {
        getBar(name).delete();
        Main.instance.data.bars.remove(name);
        Main.instance.data.save();
    }

    public static Bar getBar(String name) {
        return Main.instance.data.bars.get(name);
    }

}
