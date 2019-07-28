package timduk.leonid.modules;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;
import timduk.leonid.Settings;

import java.awt.*;

public class randomColorJob implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Leonid.leonidJda
                .getGuildById(Settings.get().technoGuildId)
                .getRoleById(Settings.get().technoLuckyBoyRoleId)
                .getManager()
                .setColor(randomColor())
                .queue();
        // Просто роли менять цвет и всё, даже юзеров не надо искать
    }

    private Color randomColor() {
        return new Color(this.rand(255), this.rand(255), this.rand(255));
    }

    private Integer rand(Integer max) {
        return (int) (Math.random() * max);
    }
    //TODO здесь - собственно изменение цветов у соответствующих ролей
}