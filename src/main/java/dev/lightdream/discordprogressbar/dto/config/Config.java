package dev.lightdream.discordprogressbar.dto.config;

import com.google.gson.annotations.Expose;
import dev.lightdream.jdaextension.dto.JDAConfig;
import dev.lightdream.jdaextension.dto.JDAEmbed;
import dev.lightdream.logger.Logger;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.HashMap;

public class Config extends JDAConfig {

    public boolean debug  = false;
    public JDAEmbed barExists = JDAEmbed.red("Error", "Bar already exists");
    public JDAEmbed barDoesNotExists = JDAEmbed.red("Error", "Bar does not exists");
    public JDAEmbed invalidAmount = JDAEmbed.red("Error", "Invalid amount");
    public JDAEmbed barCreated = JDAEmbed.green("Success", "Bar created");
    public JDAEmbed barDeleted = JDAEmbed.green("Success", "Bar deleted");
    public JDAEmbed barUpdated = JDAEmbed.green("Success", "Bar updated");
    public HashMap<String, String> urls = new HashMap<String, String>() {{
        put("0%", "https://cdn.original.gg/0.png");
        put("5%", "https://cdn.original.gg/5.png");
        put("10%", "https://cdn.original.gg/10.png");
        put("15%", "https://cdn.original.gg/15.png");
        put("20%", "https://cdn.original.gg/20.png");
        put("25%", "https://cdn.original.gg/25.png");
        put("30%", "https://cdn.original.gg/30.png");
        put("35%", "https://cdn.original.gg/35.png");
        put("40%", "https://cdn.original.gg/40.png");
        put("45%", "https://cdn.original.gg/45.png");
        put("50%", "https://cdn.original.gg/50.png");
        put("55%", "https://cdn.original.gg/55.png");
        put("60%", "https://cdn.original.gg/60.png");
        put("65%", "https://cdn.original.gg/65.png");
        put("70%", "https://cdn.original.gg/70.png");
        put("75%", "https://cdn.original.gg/75.png");
        put("80%", "https://cdn.original.gg/80.png");
        put("85%", "https://cdn.original.gg/85.png");
        put("90%", "https://cdn.original.gg/90.png");
        put("95%", "https://cdn.original.gg/95.png");
        put("100%", "https://cdn.original.gg/100.png");
    }};
    @Expose
    private JDAEmbed barEmbed = JDAEmbed.black("%title%", "");

    public JDAEmbed announceSent = JDAEmbed.green("Success", "Announce sent");
    public JDAEmbed messageNotFound = JDAEmbed.red("Error", "Message id is not valid");
    public JDAEmbed invalidChannel = JDAEmbed.red("Error", "Channel is not valid.");

    public Long announceChannelId = 833683511977574490L;

    public EmbedBuilder getBarEmbed(String title, String image) {
        return barEmbed.clone()
                .parse("title", title)
                .build().setImage(image);
    }

}
