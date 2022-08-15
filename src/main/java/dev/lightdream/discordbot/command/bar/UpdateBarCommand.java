package dev.lightdream.discordbot.command.bar;

import dev.lightdream.discordbot.Main;
import dev.lightdream.discordbot.util.BarHelper;
import dev.lightdream.jdaextension.commands.DiscordCommand;
import dev.lightdream.jdaextension.dto.CommandArgument;
import dev.lightdream.jdaextension.dto.context.GuildCommandContext;
import dev.lightdream.jdaextension.dto.context.PrivateCommandContext;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.Arrays;

@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class UpdateBarCommand extends DiscordCommand {
    public UpdateBarCommand() {
        super(Main.instance, Arrays.asList("update-bar"), "Updates progress bar", Permission.ADMINISTRATOR, true, Arrays.asList(
                new CommandArgument(OptionType.STRING, "name", "Create a new progressbar", true),
                new CommandArgument(OptionType.STRING, "amount", "0%, 5%, 10% .... 100%", true)
        ));
    }

    @Override
    public void executeGuild(GuildCommandContext guildCommandContext) {
        String name = guildCommandContext.getArgument("name").getAsString();
        if (!BarHelper.exists(name)) {
            sendMessage(guildCommandContext, Main.instance.config.barDoesNotExists);
            return;
        }

        String amount = guildCommandContext.getArgument("amount").getAsString();
        if (!Main.instance.config.urls.containsKey(amount)) {
            sendMessage(guildCommandContext, Main.instance.config.invalidAmount);
        }

        BarHelper.getBar(name).update(amount);
        sendMessage(guildCommandContext, Main.instance.config.barUpdated);
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
