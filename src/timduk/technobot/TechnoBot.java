package timduk.technobot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import timduk.properties;
import timduk.technobot.modules.ChatTriggers;
import timduk.technobot.modules.CommandManager;
import timduk.technobot.modules.JobManager;
import timduk.technobot.modules.MemberData;
import timduk.utils.log;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.List;
import java.util.Properties;

public class TechnoBot extends ListenerAdapter
{
    public static JDA technoJda = null;
    public static Properties technoMutedPlayers = new Properties();
    public static File technoFile = new File("technoMuted.properties");
    public static void load()
    {
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken(properties.get().technoBotToken);
        jda.setAudioEnabled(false);
        jda.addEventListener(new TechnoBot());
        try {
            jda.buildAsync();
        } catch (LoginException e) {
            System.out.println("Bot exception " + e.getLocalizedMessage());
        }
        try {
            if (!technoFile.exists())
            {
                technoFile.createNewFile();
            }
            FileInputStream fis = new FileInputStream(technoFile);
            technoMutedPlayers.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JobManager.load();
        properties.get().load();
    }

    //Override functions
    @Override
    public void onReady(ReadyEvent e)
    {
        technoJda = e.getJDA();
        technoJda.getPresence().setGame(Game.playing("MinecraftOnly.ru"));
        log.info("TechnoBot has been loaded");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {
        technoJda = e.getJDA();
        if (e.getAuthor().isBot())
            return;

        if (!CommandManager.check(e.getMessage()))
            ChatTriggers.check(e.getMessage());
    }

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent e)
    {
        technoJda = e.getJDA();
        if (e.getMember().getUser().isBot())
            return;
        MemberData.removeNewerIfExists(e.getMember());
    }

    // Custom public functions
    public static void saveProp()
    {
        try {
            OutputStream os = new FileOutputStream(technoFile);
            technoMutedPlayers.store(os, "Muted users");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean hasPermission(Member mem)
    {
        List<Role> roles = mem.getRoles();
        if (mem.getUser().getId().equals(properties.get().TimDukId.toString()))
            return true;
        if (roles.contains(properties.get().technoHeadRoleId.toString()))
            return true;

        if (roles.contains(properties.get().technoModerRoleId.toString()))
            return true;

        for (Long each : properties.get().technoOtherControlRolesId)
        {
            if (roles.contains(each.toString()))
                return true;
        }
        return false;
    }
}