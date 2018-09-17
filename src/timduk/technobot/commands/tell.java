package timduk.technobot.commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;

public class tell
{
    public tell (Message msg)
    {
        if (!msg.getChannel().getId().equals("466321863199424525")) {
            msg.delete().queue();
            return;
        }

        if (!msg.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            msg.delete().queue();
            return;
        }

        msg.getGuild().getTextChannelById(466530335782338560L).sendMessage(msg.getContentRaw().substring(6)).queue();
        msg.getChannel().sendMessage("Сообщение отправлено :smirk:").queue();
    }
}