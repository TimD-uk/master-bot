package timduk.technobot.modules;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import timduk.properties;

import java.util.List;
import java.util.Random;

public class MemberData
{
    public static Member getRandomMember (Guild guild)
    {
        List<Member> membersServers = guild.getMembers();

        Random random = new Random();
        while (true)
        {
            int rndInt = random.nextInt(membersServers.size()),
                count = 0;

            for (Member member : membersServers)
            {
                if (member.getUser().isBot())
                    continue;
                if (count == rndInt) {
                    return member;
                }
                else {
                    count++;
                }
            }
        }
    }

    public static void removeNewerIfExists(Member member)
    {
        Role startedRole = member.getGuild().getRoleById(properties.get().technoStartMemberRoleId);
        Role firstLvlRole = member.getGuild().getRoleById(properties.get().technoSomeOneMinRoleId);
        if (member.getRoles().contains(startedRole) && member.getRoles().contains(firstLvlRole))
            member.getGuild().getController().removeRolesFromMember(member, startedRole).queue();
    }
}