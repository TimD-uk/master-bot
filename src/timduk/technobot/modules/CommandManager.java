package timduk.technobot.modules;

import net.dv8tion.jda.core.entities.Message;
import timduk.technobot.commands.mute;
import timduk.technobot.commands.news;
import timduk.technobot.commands.someone;
import timduk.technobot.commands.tell;

import java.util.ArrayList;
import java.util.Calendar;

public class CommandManager
{
    private static ArrayList<String> someoneBlock = new ArrayList<String>();
    public static boolean check(Message msg)
    {
        if (msg.getContentDisplay().startsWith("!news ")) {
            new news(msg);
            return true;
        } else if (msg.getContentDisplay().startsWith("!mute ")) {
            new mute(msg);
            return true;
        } else if (msg.getContentDisplay().startsWith("!someone ")) {
            if (someoneBlock.contains(msg.getMember().getUser().getId()))
                return true;
            if (!checkTimeForSomeOne())
                return true;
            setTimeoutSomeOne(msg.getMember().getUser().getId());
            new someone(msg);
            return true;
        } else if (msg.getContentDisplay().startsWith("!tell ")) {
            new tell(msg);
            return true;
        }
        return false;
    }


    private static void setTimeoutSomeOne(String memId)
    {
        someoneBlock.add(memId);
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                someoneBlock.remove(memId);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static boolean checkTimeForSomeOne()
    {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 22 || hour < 6) {
            return true;
        }
        return false;
    }
}