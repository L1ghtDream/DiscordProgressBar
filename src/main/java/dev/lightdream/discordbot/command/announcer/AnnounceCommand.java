package dev.lightdream.discordbot.command.announcer;

import dev.lightdream.discordbot.Main;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.Arrays;

public class AnnounceCommand extends DiscordCommand {
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public AnnounceCommand() {
        super(Main.instance, Arrays.asList("announce"), "Creates a new announcement", Permission.ADMINISTRATOR, true, Arrays.asList(
                new CommandArgument(OptionType.STRING, "message_id", "Create a new progressbar", true),
                new CommandArgument(OptionType.STRING, "channel_id", "Create a new progressbar", false)
        ));
    }

    @Override
    public void executeGuild(GuildCommandContext guildCommandContext) {
        System.out.println("LALALALALALALALLALAL");
        long id = Long.parseLong(guildCommandContext.getArgument("message_id").getAsString());

        MessageChannel channel;
        try {
            channel = guildCommandContext.getTextChannel();
        } catch (Exception e) {
            return;
            //channel = guildCommandContext.getEvent().getNewsChannel();
        }

        channel.retrieveMessageById(id).queue(message -> {
            OptionMapping chanelIDOption = guildCommandContext.getArgument("channel_id");
            Long channelID = null;
            if (chanelIDOption != null) {
                channelID = Long.parseLong(guildCommandContext.getArgument("channel_id").getAsString());
            }
            MessageChannel textChannel;

            if (channelID != null) {
                try {
                    textChannel = Main.instance.bot.getTextChannelById(channelID);
                } catch (Exception e) {
                    textChannel = Main.instance.bot.getNewsChannelById(channelID);
                }
            } else {
                if (Main.instance.config.isNews) {
                    textChannel = Main.instance.bot.getNewsChannelById(Main.instance.config.announceChannelId);
                } else {
                    textChannel = Main.instance.bot.getTextChannelById(Main.instance.config.announceChannelId);
                }
            }

            if (textChannel == null) {
                sendMessage(guildCommandContext, Main.instance.config.invalidChannel);
                return;
            }

            System.out.println(message);
            System.out.println(message.getContentRaw());

            textChannel.sendMessage(message.getContentRaw()).queue();

            sendMessage(guildCommandContext, Main.instance.config.announceSent);
        }, e -> sendMessage(guildCommandContext, Main.instance.config.messageNotFound));
    }

    @Override
    public void executePrivate(PrivateCommandContext privateCommandContext) {

    }

    @Override
    public boolean isMemberSafe() {
        return false;
    }
}
