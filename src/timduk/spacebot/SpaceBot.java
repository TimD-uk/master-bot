package timduk.spacebot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import timduk.properties;
import timduk.spacebot.modules.ChatTriggers;
import timduk.spacebot.modules.CommandManager;
import timduk.utils.log;

import javax.security.auth.login.LoginException;
import java.util.List;

public class SpaceBot extends ListenerAdapter
{
    public static JDA spaceJda = null;
    public static void load()
    {
        JDABuilder jda = new JDABuilder(AccountType.BOT);
        jda.setToken(properties.get().spaceBotToken);
        jda.setAudioEnabled(false);
        jda.addEventListener(new SpaceBot());
        try {
            jda.buildAsync();
        } catch (LoginException e) {
            System.out.println("Bot exception " + e.getLocalizedMessage());
        }
    }

    @Override
    public void onReady(ReadyEvent e)
    {
        spaceJda = e.getJDA();
        log.info("SpaceBot has been loaded");
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e)
    {

        spaceJda = e.getJDA();
        if (e.getAuthor().isBot())
            return;

        if (!CommandManager.check(e.getMessage()))
            ChatTriggers.check(e.getMessage());
    }

    public static boolean hasPermission(Member mem)
    {
        List<Role> roles = mem.getRoles();
        if (mem.getUser().getId().equals(properties.get().TimDukId.toString()))
            return true;
        if (roles.contains(spaceJda.getGuildById(properties.get().spaceGuildId).getRoleById(properties.get().spaceHeadRoleId)))
            return true;

        if (roles.contains(spaceJda.getGuildById(properties.get().spaceGuildId).getRoleById(properties.get().spaceModerRoleId)))
            return true;

        for (Long each : properties.get().spaceOtherControlRolesId)
        {
            if (roles.contains(spaceJda.getGuildById(properties.get().spaceGuildId).getRoleById(each)))
                return true;
        }
        return false;
    }
}