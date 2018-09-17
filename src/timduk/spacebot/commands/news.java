package timduk.spacebot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import timduk.properties;
import timduk.spacebot.SpaceBot;

import java.awt.*;
import java.time.OffsetDateTime;

public class news
{
    public news (Message msg)
    {
        if (msg.getChannel().getId().equals("480029345197326359") && SpaceBot.hasPermission(msg.getMember())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor("SpaceCraft","https://minecraftonly.ru", msg.getGuild().getIconUrl());
            embed.setFooter("Powered by TimDuk","https://hybridonly.ru/img/logo_timduk.png");
            embed.setColor(Color.RED);

            embed.setTimestamp(OffsetDateTime.now());
            embed.addField("Внимание!", msg.getContentDisplay().substring(6), false);

            msg.getGuild().getTextChannelById(properties.get().spaceNewsChannelId).sendMessage("@everyone").queue();
            msg.getGuild().getTextChannelById(properties.get().spaceNewsChannelId).sendMessage(embed.build()).queue();
            msg.getChannel().sendMessage("Оповещение успешно отправлено").queue();
        } else {
            msg.delete().queue();
        }
    }
}