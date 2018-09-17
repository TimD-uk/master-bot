package timduk.technobot.commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.managers.GuildController;
import timduk.properties;
import timduk.technobot.TechnoBot;

import java.sql.Timestamp;

public class mute
{
    public mute (Message msg)
    {
        if (TechnoBot.hasPermission(msg.getMember())) {
            if (!msg.getMentionedMembers().equals(null)) {
                for (Member member : msg.getMentionedMembers())
                {
                    GuildController controller = member.getGuild().getController();
                    controller.addSingleRoleToMember(member, controller.getGuild().getRoleById(properties.get().technoMuteRoleId)).queue();
                    for (Channel channel : controller.getGuild().getTextChannels()) {
                        channel.putPermissionOverride(controller.getGuild().getRoleById(properties.get().technoMuteRoleId)).setDeny(Permission.MESSAGE_WRITE).queue();
                    }
                    for (Channel channel : controller.getGuild().getVoiceChannels()) {
                        channel.putPermissionOverride(controller.getGuild().getRoleById(properties.get().technoMuteRoleId)).setDeny(Permission.VOICE_SPEAK).queue();
                    }
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                    TechnoBot.technoMutedPlayers.setProperty(member.getUser().getId(), String.valueOf(timestamp.getTime() / 1000 + 1800));
                    TechnoBot.saveProp();
                    msg.getChannel().sendMessage("Пользователю выдан мут").queue();
                    msg.getGuild().getTextChannelById(properties.get().technoBotChannelId).sendMessage(member.getAsMention() + ", Вам выдан мут на 30 минут за нарушение правил сервера, либо Вы **пидор** ").queue();
                    break;
                }
            }
        }
    }
}