package timduk.leonid.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.Role;
import timduk.leonid.Leonid;
import timduk.leonid.ext.Chat;
import timduk.leonid.ext.Command;
import timduk.leonid.utils.log;

import java.awt.*;

public class nick extends Command {
    public nick() {
        log.info("Command 'nick' registered");
    }

    @Override
    public void execute(Message msg) {
        if (!hasPerms(msg.getMember())) {
            log.info("Haven't got perms");
            //TODO удалять, если недостаточно пермов + писать в чат на пару секунд, что недостаточно пермов
            return;
        }
        if (!isAllowedChannel(msg.getChannel().getIdLong())) {
            log.info("Doesn't allow in this channel");
            //TODO удалять, если не тот канал + писать в чат на пару секунд, что не туда
            return;
        }
        if (!hasArgs(msg.getContentDisplay())) {
            log.info("Haven't got arguments");
            return; //TODO сообщение, что недостаточно аргументов + удалять сообщение, что было от пользователя, а так же сообщение, что недостаточно аргументов на 20 секунд
        }

        Member member = msg.getMentionedMembers().get(0);
        for (Role role : member.getRoles()) {
            if (role.getIdLong() == Leonid.settings.technoHeadRoleId
                    || role.getIdLong() == Leonid.settings.technoTimDukRoleId
                    || role.getIdLong() == Leonid.settings.technoModerRoleId
                    || role.getIdLong() == Leonid.settings.technoLowModerRoleId
            ) {
                //TODO послать, что ты пидор и нельзя менять ему ник
                return;
            }
        }

        msg.getGuild().getController()
                .setNickname(
                        member,
                        getArgs(msg.getContentRaw())[1]
                )
                .queue();

        msg.getGuild()
                .getTextChannelById(Leonid.settings.technoCraftGeneralChannelId)
                .sendMessage(member.getAsMention() + ", " + msg.getMember().getAsMention() + " поменял твой ник")
                .queue();

        msg.getChannel().sendMessage(
                new EmbedBuilder()
                        .addField("Leonid Arkadyevich", Chat.BOLD + "Вы поменяли никнейм " + member.getAsMention() + Chat.BOLD, false)
                        .setColor(Color.MAGENTA)
                        .build()
        ).queue();

    }

    private String getAlias(String fullText) {
        for (String alias : this.getAliases()) {
            if (fullText.startsWith(Leonid.settings.cmdPrefix + alias))
            {
                return alias;
            }
        }
        return null;
    }

    private String[] getArgs(String fullRawText) {
        return fullRawText
                .substring(
                        Leonid.settings.cmdPrefix.length()
                                + getAlias(fullRawText).length()
                                + 1
                )
                .replaceAll("\\s+", " ")
                .split(" ");
    }

}