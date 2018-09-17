package timduk;

import timduk.spacebot.SpaceBot;
import timduk.technobot.TechnoBot;
import timduk.utils.log;

public class bot
{
    public static void main(String[] args)
    {
        log.info("Bot is starting");

        properties.get().load();

        SpaceBot.load();
        TechnoBot.load();
    }
}