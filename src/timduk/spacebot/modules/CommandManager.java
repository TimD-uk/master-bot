package timduk.spacebot.modules;

import net.dv8tion.jda.core.entities.Message;
import timduk.spacebot.commands.news;
import timduk.spacebot.commands.someone;
import timduk.spacebot.commands.tell;

import java.util.ArrayList;

public class CommandManager
{
    private static ArrayList<String> someoneBlock = new ArrayList<String>();
    public static boolean check(Message msg)
    {
        if (msg.getContentDisplay().startsWith("!news ")) {
            new news(msg);
            return true;
        } else if (msg.getContentDisplay().startsWith("!someone ")) {
            if (someoneBlock.contains(msg.getMember().getUser().getId()))
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

    private static void setTimeoutSomeOne(String memId) {
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
}