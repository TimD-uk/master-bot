package timduk.technobot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import timduk.properties;
import timduk.technobot.TechnoBot;
import timduk.utils.log;

import java.awt.*;
import java.time.OffsetDateTime;

public class news
{
    public news (Message msg)
    {
        log.info(TechnoBot.hasPermission(msg.getMember()));
        if (msg.getChannel().getId().equals(properties.get().technoAdminChannelId.toString()) && TechnoBot.hasPermission(msg.getMember())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor("TechnoCraft","https://minecraftonly.ru", msg.getGuild().getIconUrl());
            embed.setFooter("Powered by TimDuk","https://hybridonly.ru/img/logo_timduk.png");
            embed.setColor(Color.RED);

            embed.setTimestamp(OffsetDateTime.now());
            embed.addField("Внимание!", msg.getContentDisplay().substring(6), false);

            msg.getGuild().getTextChannelById(properties.get().technoNewsChannelId).sendMessage("@everyone").queue();
            msg.getGuild().getTextChannelById(properties.get().technoNewsChannelId).sendMessage(embed.build()).queue();
            msg.getChannel().sendMessage("Оповещение успешно отправлено").queue();
        } else {
            msg.delete().queue();
        }
    }
}