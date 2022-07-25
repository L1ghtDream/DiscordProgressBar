package dev.lightdream.discordprogressbar.command.announcer;

import dev.lightdream.discordprogressbar.Main;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
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

        guildCommandContext.getTextChannel().retrieveMessageById(id).queue(message -> {
            OptionMapping texChannelOption = guildCommandContext.getArgument("channel");
            TextChannel textChannel;
            if (texChannelOption != null) {
                textChannel = texChannelOption.getAsTextChannel();
            } else {
                textChannel = Main.instance.bot.getTextChannelById(Main.instance.config.announceChannelId);
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
