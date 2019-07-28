package timduk.leonid.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import timduk.leonid.Settings;
import timduk.leonid.ext.Chat;
import timduk.leonid.ext.Command;
import timduk.utils.log;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class mute extends Command {
    public mute() {
        log.info("Command 'mute' registered");
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

        Member member = msg.getMentionedMembers().get(0); //Todo если нет уведомления
        Long time = getTime(msg.getContentRaw());
        if (time == 0L) {
            //Если нет вообще про время ничего, то показать время, сколько до конца мута
            return; //TODO уведомление, что не в той форме
        }

        msg.getGuild().getController().addSingleRoleToMember(member, msg.getGuild().getRoleById(Settings.get().technoMuteRoleId)).queue();
        Settings.get().mutedUsers.put(member.getUser().getIdLong(), time); //Проверка, если ли юзер в листе + занесение в файлик

        msg.getChannel().sendMessage(
                new EmbedBuilder()
                        .addField("Leonid Arkadyevich", Chat.BOLD + "Пользователю " + member.getAsMention() + " выдана молчанка на " + getStringTime(time) + Chat.BOLD, false)
                        .setColor(Color.MAGENTA)
                        .build()
        ).queue();
    }

    private String getStringTime(Long time) {
        Date date = new Date();
        Long nowTime = date.getTime() / 1000;
        long diffInTime = time - nowTime;

        if (diffInTime < 3600) {
            return new SimpleDateFormat("mm мин.").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
        } else if (diffInTime < 86400) {
            String hours = new SimpleDateFormat("HH").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
            hours = String.valueOf(Integer.valueOf(hours) - 3);
            String min = new SimpleDateFormat("mm мин.").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
            return hours + " " + min;
        } else {
            String days = new SimpleDateFormat("dd").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
            days = String.valueOf(Integer.valueOf(days) - 1);
            String hours = new SimpleDateFormat("HH").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
            hours = String.valueOf(Integer.valueOf(hours) - 3);
            String min = new SimpleDateFormat("mm мин.").format(new Date(TimeUnit.SECONDS.toMillis(diffInTime)));
            return days + " д. " + hours + " ч. " + min;
        }
    }

    private Long getTime(String fullText) {
        String time = fullText.replaceAll("\\s+", " ").split(" ")[2];
        Integer timeFromString;
        Matcher matcherNumbers = Pattern.compile("([0-9]?[0-9])").matcher(time);
        if (matcherNumbers.find()) {
            timeFromString = Integer.valueOf(matcherNumbers.group(0));
        } else {
            return 0L;
        }

        Matcher matcherLetter = Pattern.compile("([mhd])").matcher(time);
        long timeInS;
        if (matcherLetter.find()) {
            switch (matcherLetter.group(0)) {
                case "h":
                    timeInS = (long) (timeFromString * 3600);
                    break;
                case "d":
                    timeInS = (long) (timeFromString * 86400);
                    break;
                default:
                    timeInS = (long) (timeFromString * 60);
            }
        } else {
            timeInS = (long) (timeFromString * 60);
        }
        Date date = new Date();
        return date.getTime() / 1000 + timeInS;
    }
}