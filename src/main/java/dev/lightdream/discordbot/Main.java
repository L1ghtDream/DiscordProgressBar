package dev.lightdream.discordbot;

import dev.lightdream.discordbot.command.announcer.AnnounceCommand;
import dev.lightdream.discordbot.command.bar.CreateBarCommand;
import dev.lightdream.discordbot.command.bar.DeleteBarCommand;
import dev.lightdream.discordbot.command.bar.UpdateBarCommand;
import dev.lightdream.discordbot.dto.config.Config;
import dev.lightdream.discordbot.dto.config.Data;
import dev.lightdream.filemanager.FileManager;
import dev.lightdream.filemanager.FileManagerMain;
import dev.lightdream.jdaextension.JDAExtensionMain;
import dev.lightdream.jdaextension.dto.JDAConfig;
import dev.lightdream.jdaextension.managers.DiscordCommandManager;
import dev.lightdream.logger.LoggableMain;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.JDA;

import java.io.File;
import java.util.Arrays;

public class Main implements JDAExtensionMain, FileManagerMain, LoggableMain {


    // Static
    public static Main instance;

    // Instances
    public JDA bot;

    // Config
    public Config config;
    public Data data;

    // Manager
    public FileManager fileManager;

    public void enable() {
        instance = this;
        Logger.init(this);

        fileManager = new FileManager(this);
        loadConfigs();

        bot = JDAExtensionMain.generateBot(this);

        new DiscordCommandManager(this, Arrays.asList(
                new CreateBarCommand(),
                new DeleteBarCommand(),
                new UpdateBarCommand(),
                new AnnounceCommand()
        ));
    }

    private void loadConfigs() {
        config = fileManager.load(Config.class);
        data = fileManager.load(Data.class);
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public JDA getBot() {
        return bot;
    }

    @Override
    public JDAConfig getJDAConfig() {
        return config;
    }

    @Override
    public File getDataFolder() {
        return new File(System.getProperty("user.dir"));
    }

    @Override
    public boolean debug() {
        return config.debug;
    }

    @Override
    public void log(String s) {
        System.out.println(s);
    }
}
