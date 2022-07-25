package dev.lightdream.discordprogressbar.command.bar;

import dev.lightdream.discordprogressbar.Main;
import dev.lightdream.discordprogressbar.manager.BarHelper;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.Arrays;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class DeleteBarCommand extends DiscordCommand {
    public DeleteBarCommand() {
        super(Main.instance, Arrays.asList("delete-bar"), "Deleted progress bar", Permission.ADMINISTRATOR, true, Arrays.asList(
                new CommandArgument(OptionType.STRING, "name", "Create a new progressbar", true)
        ));
    }

    @Override
    public void executeGuild(GuildCommandContext guildCommandContext) {
        String name = guildCommandContext.getArgument("name").getAsString();
        if (!BarHelper.exists(name)) {
            sendMessage(guildCommandContext, Main.instance.config.barDoesNotExists);
            return;
        }

        BarHelper.deleteBar(name);
        sendMessage(guildCommandContext, Main.instance.config.barDeleted);
    }

    @Override
    public void executePrivate(PrivateCommandContext privateCommandContext) {
        // Impossible
    }

    @Override
    public boolean isMemberSafe() {
        return false;
    }
}
