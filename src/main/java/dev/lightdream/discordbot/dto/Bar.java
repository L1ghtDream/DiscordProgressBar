package dev.lightdream.discordbot.dto;

import dev.lightdream.discordbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Bar {

    public String title;
    public Long channel;
    public Long message;

    public Bar(String title, MessageChannel channel) {
        try {
            this.title = title;
            this.channel = channel.getIdLong();

            EmbedBuilder embed = Main.instance.config.getBarEmbed(title, Main.instance.config.urls.get("0%"));

            CompletableFuture<Message> message = channel.sendMessageEmbeds(embed.build()).submit();
            this.message = message.get().getIdLong();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("ConstantConditions")
    public void update(String amount) {
        String url = Main.instance.config.urls.get(amount);

        Main.instance.bot.getTextChannelById(channel).editMessageEmbedsById(message, Main.instance.config.getBarEmbed(title, url).build()).queue();
    }

    @SuppressWarnings("ConstantConditions")
    public void delete() {
        Main.instance.bot.getTextChannelById(channel).deleteMessageById(message).queue();
    }

}
