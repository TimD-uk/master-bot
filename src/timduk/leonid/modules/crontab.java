package timduk.leonid.modules;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class crontab {
    private JobDetail randomColorJob;
    private Trigger randomColorTrigger;
    private JobDetail clearMuteJob;
    private Trigger clearMuteTrigger;

    public crontab() {
        randomColorJob = JobBuilder
                .newJob(randomColorJob.class)
                .withIdentity("randomColorJob", "randomColorGroup")
                .build();

        randomColorTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("cronTriggerRandomColor", "randomColorGroup")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0/2 * * * * ?")
                )
                .build();

        clearMuteJob = JobBuilder
                .newJob(clearMuteJob.class)
                .withIdentity("clearMuteJob", "clearMuteGroup")
                .build();

        clearMuteTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("cronTriggerClearMute", "clearMuteGroup")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("* * * * * ?")
                )
                .build();

    }

    public void startSchedules() {
        try {
            Scheduler randomColorScheduler = new StdSchedulerFactory().getScheduler();
            randomColorScheduler.start();
            randomColorScheduler.scheduleJob(randomColorJob, randomColorTrigger);

            Scheduler clearMuteScheduler = new StdSchedulerFactory().getScheduler();
            clearMuteScheduler.start();
            clearMuteScheduler.scheduleJob(clearMuteJob, clearMuteTrigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}