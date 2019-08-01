package timduk.leonid;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import timduk.leonid.modules.CronTab;
import timduk.leonid.utils.log;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;
import java.util.TimeZone;

public class Leonid extends ListenerAdapter {
    public static JDA leonidJda;
    public static CommandManager commandManager;
    public static CronTab crontab;
    public static DataBase database;
    public static Settings settings;

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        log.info("Bot is starting up");
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));

        // DataBase initialize
        database = new DataBase();
        // Compile settings & commands
        settings = new Settings();
        settings.initCmd();
        // Create command manager
        commandManager = new CommandManager();
        // Scheduler is running
        crontab = new CronTab();

        if (!database.showTables("settings"))
        {
            database.createTable(
                    String.format("`%s`", settings.db_tableNameSettings),
                    "`settings_name` TEXT NOT NULL, `value` TEXT NOT NULL"
            );
            String token = System.console().readLine("Enter token -> ");
            database.insert(settings.db_tableNameSettings, "settings_name, value", String.format("'token', '%s'", token));
        }
        if (database.select("*", settings.db_tableNameSettings, "settings_name = 'token'").isEmpty())
        {
            String token = System.console().readLine("Enter token -> ");
            database.insert(settings.db_tableNameSettings, "settings_name, value", String.format("'token', '%s'", token));
        }


        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken(settings.LeonidToken);
        jda.setAudioEnabled(false);
        jda.addEventListener(new Leonid());
        jda.addEventListener(new ActionManager());
        try {
            jda.buildAsync();
        } catch (LoginException e) {
            System.out.println("Bot exception " + e.getLocalizedMessage());
        }
    }

    @Override
    public void onReady(ReadyEvent e) {
        leonidJda = e.getJDA();
        leonidJda.getPresence().setGame(Game.playing("I work"));
        crontab.startSchedules();
        log.info("TechnoBot has been loaded");
    }
}