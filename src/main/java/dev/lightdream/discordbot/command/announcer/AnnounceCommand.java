package dev.lightdream.discordbot.command.announcer;

import dev.lightdream.discordbot.Main;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.BaseGuildMessageChannel;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.Arrays;

public class AnnounceCommand extends DiscordCommand {
    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public AnnounceCommand() {
        super(Main.instance, Arrays.asList("announce"), "Creates a new announcement", Permission.ADMINISTRATOR, true, Arrays.asList(
                new CommandArgument(OptionType.STRING, "message_id", "Create a new progressbar", true),
                new CommandArgument(OptionType.CHANNEL, "channel", "Create a new progressbar", false)
        ));
    }

    @Override
    public void executeGuild(GuildCommandContext guildCommandContext) {
        long id = Long.parseLong(guildCommandContext.getArgument("message_id").getAsString());

        BaseGuildMessageChannel channel;
        try {
            channel = guildCommandContext.getTextChannel();
        } catch (Exception e) {
            channel = guildCommandContext.getEvent().getNewsChannel();
        }

        channel.retrieveMessageById(id).queue(message -> {
            OptionMapping textChannelOption = guildCommandContext.getArgument("channel");
            BaseGuildMessageChannel textChannel;

            if (textChannelOption != null) {
                try{
                    textChannel = textChannelOption.getAsTextChannel();
                }catch (Exception e){
                    textChannel = textChannelOption.getAsNewsChannel();
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

            textChannel.sendMessage(message).queue();

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
