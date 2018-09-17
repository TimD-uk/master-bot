package timduk.technobot.jobs;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.properties;
import timduk.technobot.TechnoBot;
import timduk.technobot.modules.MemberData;
import timduk.utils.log;

public class luckyJob implements Job
{
    public luckyJob() {}
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        Guild technocraft = TechnoBot.technoJda.getGuildById(properties.get().technoGuildId);
        Role role = technocraft.getRoleById(properties.get().technoLuckyManRoleId);
        Member oldLuckyMan = null;

        for (Member mem : technocraft.getMembers())
        {
            if (mem.getRoles().contains(role))
            {
                oldLuckyMan = mem;
            }
        }

        Member rndMan;

        while (true)
        {
            rndMan = MemberData.getRandomMember(technocraft);
            if (!TechnoBot.hasPermission(rndMan) && rndMan.getRoles().contains(technocraft.getRoleById(properties.get().technoSomeOneMinRoleId)))
            {
                break;
            }
        }

        if (oldLuckyMan != null)
            technocraft.getController().removeSingleRoleFromMember(oldLuckyMan, role).queue();

        technocraft.getController().addSingleRoleToMember(rndMan, role).queue();
        technocraft.getTextChannelById(properties.get().technoMainChannelId).sendMessage(
                String.format("Внимание, @everyone!\nВ сегодняшней рандом-рулетке  побеждает %s и ему достаётся роль %s до следующего розыгрыша. Также, помимо этой уникальной роли он срывает куш и выигрывает **кое-что ценное** из банка призов!\n\n(Победителя прошу написать модераторам по поводу выигрыша)", rndMan.getAsMention(), role.getAsMention())
        ).queue();
        technocraft.getTextChannelById(properties.get().technoAdminChannelId).sendMessage(
                String.format("** %s только что выбил %s **", rndMan.getAsMention(), role.getAsMention())
        ).queue();

        if (rndMan.getUser().hasPrivateChannel())
        {
            rndMan.getUser().openPrivateChannel().queue((channel) -> {
                channel.sendMessage("Только что Вы победили в нашей рандом-рулетке. Проверьте канал " + technocraft.getTextChannelById(properties.get().technoMainChannelId).getAsMention() + " за подробностями").queue();
            });
        }

        log.info("Role " + role.getName() + " was given to " + rndMan.getUser().getName());
    }
}