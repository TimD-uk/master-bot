package timduk.leonid.modules;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;
import timduk.leonid.ext.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class luckyBoyJob implements Job
{
    private Map<luckyChance, Long[]> roleChances = new HashMap<>();
    private List<Member> memberListHigh = new ArrayList<>();
    private List<Member> memberListMedium = new ArrayList<>();
    private List<Member> memberListLow = new ArrayList<>();
    private List<Member> commonList = new ArrayList<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        roleChances.put(luckyChance.HIGH, new Long[]{525412671470960641L, 466525684844462090L, 466526022951632896L, 466526072461066250L, 466526113741406239L});
        roleChances.put(luckyChance.MEDIUM, new Long[]{466526156204802050L, 466526206825594881L, 466526230003580928L, 466526278120636417L});
        roleChances.put(luckyChance.LOW, new Long[]{466616770955051018L, 466526312584970250L});
        Guild guild = Leonid.leonidJda.getGuildById(Leonid.settings.technoGuildId);
        Member luckyBoy = getRandomMember(guild);
        Role luckyRole = guild.getRoleById(Leonid.settings.technoLuckyBoyRoleId);
        List<Member> membersInRole = guild.getMembersWithRoles(luckyRole);
        for (Member member : membersInRole)
        {
            guild.getController().removeSingleRoleFromMember(member, luckyRole).queue();
        }
        guild.getController().addSingleRoleToMember(luckyBoy, luckyRole).queue();
        guild.getTextChannelById(Leonid.settings.technoCraftLuckyManChannelId).sendMessage(luckyBoy.getAsMention() + " сегодня " + luckyRole.getAsMention() + Chat.HIDE + " апплодисменты" + Chat.HIDE).queue();
    }

    private Member getRandomMember(Guild guild)
    {
        List<Member> localList;
        for (int i = 0; i < roleChances.get(luckyChance.HIGH).length; i++)
        {
            memberListHigh.addAll(guild.getMembersWithRoles(guild.getRoleById(roleChances.get(luckyChance.HIGH)[i])));
        }
        memberListHigh = removeDuplicates(memberListHigh);

        localList = memberListHigh;

        for (int i = 0; i < luckyChance.HIGH.get(); i++)
        {
            memberListHigh.addAll(localList);
        }

        for (int i = 0; i < roleChances.get(luckyChance.MEDIUM).length; i++)
        {
            memberListMedium.addAll(guild.getMembersWithRoles(guild.getRoleById(roleChances.get(luckyChance.MEDIUM)[i])));
        }
        memberListMedium = removeDuplicates(memberListMedium);

        localList = memberListMedium;

        for (int i = 0; i < luckyChance.MEDIUM.get(); i++)
        {
            memberListHigh.addAll(localList);
        }

        for (int i = 0; i < roleChances.get(luckyChance.LOW).length; i++)
        {
            memberListLow.addAll(guild.getMembersWithRoles(guild.getRoleById(roleChances.get(luckyChance.LOW)[i])));
        }
        memberListLow = removeDuplicates(memberListLow);

        commonList.addAll(memberListHigh);
        commonList.addAll(memberListMedium);
        commonList.addAll(memberListLow);

        // Рандомного чела
        return commonList.get(rand(commonList.size()));
    }

    private Integer rand(Integer max)
    {
        return (int) (Math.random() * max);
    }

    private ArrayList<Member> removeDuplicates(List<Member> list)
    {
        ArrayList<Member> newList = new ArrayList<>();
        for (Member element : list)
        {
            if (!newList.contains(element))
            {
                newList.add(element);
            }
        }
        return newList;
    }

    private enum luckyChance
    {
        HIGH(7),
        MEDIUM(4),
        LOW(1);

        private final int chance;

        luckyChance(int chance)
        {
            this.chance = chance;
        }

        public Integer get()
        {
            return this.chance;
        }
    }
}