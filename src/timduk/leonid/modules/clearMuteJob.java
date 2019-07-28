package timduk.leonid.modules;

import net.dv8tion.jda.core.managers.GuildController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;
import timduk.leonid.Settings;

import java.util.Date;
import java.util.Map;

public class clearMuteJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        Long time = date.getTime() / 1000;

        for (Map.Entry<Long, Long> muted : Settings.get().mutedUsers.entrySet()) {
            if (muted.getValue() <= time) {
                GuildController guildController = Leonid.leonidJda.getGuildById(Settings.get().technoGuildId).getController();
                guildController
                        .removeSingleRoleFromMember(
                                guildController.getGuild().getMemberById(muted.getKey()),
                                guildController.getGuild().getRoleById(Settings.get().technoMuteRoleId)
                        )
                        .queue();
                Settings.get().mutedUsers.remove(muted.getKey());
            }
        }
    }
}