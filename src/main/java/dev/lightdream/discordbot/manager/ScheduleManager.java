package dev.lightdream.discordbot.manager;

import dev.lightdream.discordbot.Main;
import dev.lightdream.discordbot.util.PlayerCountHelper;
import dev.lightdream.lambda.ScheduleUtils;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.entities.GuildChannel;


public class ScheduleManager {

    public ScheduleManager() {
        registerOnlinePlayersUpdater();
    }

    public void registerOnlinePlayersUpdater() {
        ScheduleUtils.runTaskTimer(() -> {
            GuildChannel channel = Main.instance.bot.getGuildChannelById(Main.instance.config.playerCountChanelID);
            if (channel == null) {
                Logger.error("Channel not found (" + Main.instance.config.playerCountChanelID + "). Please check your config");
                return;
            }

            channel.getManager().setName(Main.instance.config.playerCountString
                    .parse("count", PlayerCountHelper.getPlayerCount(Main.instance.config.playerCountServerIP))
                    .parse()
            ).queue();

        }, 10 * 1000L, 60 * 1000L);
    }

}
