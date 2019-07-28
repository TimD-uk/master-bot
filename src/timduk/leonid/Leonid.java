package timduk.leonid;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import timduk.leonid.modules.crontab;
import timduk.utils.log;

import javax.security.auth.login.LoginException;
import java.util.Calendar;

public class Leonid extends ListenerAdapter {
    public static JDA leonidJda;
    public static CommandManager commandManager;
    public static crontab crontab;

    public static void load() {
        Settings.get().init();
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken(Settings.get().LeonidToken);
        jda.setAudioEnabled(false);
        jda.addEventListener(new Leonid());
        jda.addEventListener(new ActionManager());
        try {
            jda.buildAsync();
        } catch (LoginException e) {
            System.out.println("Bot exception " + e.getLocalizedMessage());
        }
        commandManager = new CommandManager();
        crontab = new crontab();
    }

    @Override
    public void onReady(ReadyEvent e) {
        leonidJda = e.getJDA();
        leonidJda.getPresence().setGame(Game.playing("I execute"));
        crontab.startSchedules();
        Calendar cal = Calendar.getInstance();
        String time = "[" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] ";
        log.info(time + "TechnoBot has been loaded");
    }
}