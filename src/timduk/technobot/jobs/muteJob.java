package timduk.technobot.jobs;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.technobot.TechnoBot;

import java.sql.Timestamp;
import java.util.ArrayList;

public class muteJob implements Job
{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        ArrayList<Object> deletedUsers = new ArrayList<Object>();
        for (Object userId : TechnoBot.technoMutedPlayers.keySet()) {
            String id = (String) userId;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            int time = (int) (timestamp.getTime() / 1000);
            if (Integer.valueOf(TechnoBot.technoMutedPlayers.getProperty(id)) < time) {
                for (Guild guild : TechnoBot.technoJda.getGuilds()) {
                    if (guild.getId().equals("466321862209699871")) {
                        Member member = guild.getMemberById(id);
                        if (!member.equals(null)) {
                            guild.getController().removeSingleRoleFromMember(member, guild.getRoleById("467761280590282763")).queue();
                        }
                        deletedUsers.add(id);
                        break;
                    }
                }
            }
        }
        for (Object obj : deletedUsers) {
            TechnoBot.technoMutedPlayers.remove(obj);
        }
        TechnoBot.saveProp();
    }
}