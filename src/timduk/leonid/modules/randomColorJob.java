package timduk.leonid.modules;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;

import java.awt.*;

public class randomColorJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Leonid.leonidJda
                .getGuildById(Leonid.settings.technoGuildId)
                .getRoleById(Leonid.settings.technoLuckyBoyRoleId)
                .getManager()
                .setColor(randomColor())
                .queue();
    }

    private Color randomColor() {
        return new Color(this.rand(255), this.rand(255), this.rand(255));
    }

    private Integer rand(Integer max) {
        return (int) (Math.random() * max);
    }
}