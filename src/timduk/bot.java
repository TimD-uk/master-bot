package timduk;

import timduk.leonid.Leonid;
import timduk.utils.log;

import java.util.TimeZone;

public class bot
{
    public static void main(String[] args)
    {
        log.info("Bot is starting");
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));

        Leonid.load();
    }
}