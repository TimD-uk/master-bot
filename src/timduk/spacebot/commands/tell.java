package timduk.spacebot.commands;

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import timduk.properties;

public class tell
{
    public tell (Message msg)
    {
        if (!msg.getChannel().getId().equals(properties.get().spaceAdminChannelId.toString())) {
            msg.delete().queue();
            return;
        }

        if (!msg.getMember().hasPermission(Permission.ADMINISTRATOR)) {
            msg.delete().queue();
            return;
        }

        msg.getGuild().getTextChannelById(properties.get().spaceMainChannelId).sendMessage(msg.getContentRaw().substring(6)).queue();
        msg.getChannel().sendMessage("Сообщение отправлено :smirk:").queue();
    }
}