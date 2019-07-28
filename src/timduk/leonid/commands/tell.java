package timduk.leonid.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import timduk.leonid.Settings;
import timduk.leonid.ext.Chat;
import timduk.leonid.ext.Command;
import timduk.utils.log;

import java.awt.*;

public class tell extends Command {
    public tell() {
        log.info("Command 'tell' registered");
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

        msg.getGuild().getTextChannelById(Settings.get().technoCraftGeneralChannelId).sendMessage(this.getArgs(msg.getContentDisplay())).queue();
        msg.getChannel().sendMessage(
                new EmbedBuilder()
                        .addField("Leonid Arkadyevich", Chat.BOLD + "Сообщение отправлено" + Chat.BOLD, false)
                        .setColor(Color.MAGENTA)
                        .build()
        ).queue();
    }

    private String getAlias(String fullText) {
        for (String alias : this.getAliases()) {
            if (fullText.startsWith(Settings.get().cmdPrefix + alias)) {
                return alias;
            }
        }
        return null;
    }

    private String getArgs(String fullText) {
        return fullText.substring(Settings.get().cmdPrefix.length() + getAlias(fullText).length() + 1);
    }

}