package timduk.leonid.modules;

import net.dv8tion.jda.core.managers.GuildController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;

import java.util.Date;
import java.util.Map;

public class clearMuteJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        Long time = date.getTime() / 1000;

        for (Map.Entry<Long, Long> muted : Leonid.settings.mutedUsers.entrySet())
        {
            if (muted.getValue() <= time) {
                GuildController guildController = Leonid.leonidJda.getGuildById(Leonid.settings.technoGuildId).getController();
                guildController
                        .removeSingleRoleFromMember(
                                guildController.getGuild().getMemberById(muted.getKey()),
                                guildController.getGuild().getRoleById(Leonid.settings.technoMuteRoleId)
                        )
                        .queue();
                Leonid.settings.mutedUsers.remove(muted.getKey());
            }
        }
    }
}