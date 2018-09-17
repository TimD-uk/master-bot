package timduk.spacebot.modules;

import net.dv8tion.jda.core.entities.Message;
import timduk.properties;

public class ChatTriggers
{
    private static boolean allowHello = true,
            allowMention = true;
    public static boolean check(Message msg)
    {
        String hello [] = {"привет", "ку", "qq", "здарова", "добрый день", "доброе утро", "добрый вечер"};
        String answerHello [] = {"Привет"};
        String answerMention [] = {"Я не умею говорить :pensive:", "Что?", "А ну повтори :bow::skin-tone-3:"};

        // Triggers check
        if (checkContent(msg, hello)) {
            if (!isAllowAnswerHello())
                return false;
            int rand = (int) (Math.random() * answerHello.length);
            msg.getChannel().sendMessage(answerHello[rand] + " " + msg.getGuild().getEmoteById(455783210064805909L).getAsMention()).queue();
            setTimeoutHello();
            return true;
        }
        // Check mention
        if (msg.getMentionedMembers().contains(msg.getGuild().getMemberById(properties.get().spaceBotUserId))) {
            if (!isAllowAnswerMention())
                return false;
            int rand = (int) (Math.random() * answerMention.length);
            msg.getChannel().sendMessage(answerMention[rand]).queue();
            setTimeoutMention();
            return true;
        }
        return false;
    }

    private static void setTimeoutMention() {
        //20sec
        allowMention = false;
        new Thread(() -> {
            try {
                Thread.sleep(20000);
                allowMention = true;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void setTimeoutHello() {
        //20sec
        allowHello = false;
        new Thread(() -> {
            try {
                Thread.sleep(20000);
                allowHello = true;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static boolean isAllowAnswerMention()
    {
        return allowMention;
    }

    private static boolean isAllowAnswerHello()
    {
        return allowHello;
    }

    private static boolean checkContent(Message msg, String query[])
    {
        for (String str : query)
        {
            String queryLoc = " "+str+" ";
            if ((" " + msg.getContentDisplay().toLowerCase() + " ").contains(queryLoc)) {
                return true;
            }
        }
        return false;
    }
}