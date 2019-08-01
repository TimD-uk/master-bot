package timduk.leonid.modules;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import timduk.leonid.utils.log;

public class CronTab
{
    private JobDetail randomColorJob;
    private Trigger randomColorTrigger;
    private JobDetail clearMuteJob;
    private Trigger clearMuteTrigger;
    private JobDetail luckyBoyJob;
    private Trigger luckyBoyTrigger;
    private JobDetail saveMuteJob;
    private Trigger saveMuteTrigger;
    private JobDetail keepAliveJob;
    private Trigger keepAliveTrigger;

    public CronTab()
    {
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

        luckyBoyJob = JobBuilder
                .newJob(luckyBoyJob.class)
                .withIdentity("luckyBoyJob", "luckyBoyGroup")
                .build();

        luckyBoyTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("cronTriggerLuckyBoy", "luckyBoyGroup")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0 0 18 ? * SUN,TUE,THU *")
                )
                .build();

        saveMuteJob = JobBuilder
                .newJob(saveMuteJob.class)
                .withIdentity("saveMuteJob", "saveMuteGroup")
                .build();

        saveMuteTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("cronTriggerSaveMute", "saveMuteGroup")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0/15 * * * * ?")
                )
                .build();

        keepAliveJob = JobBuilder
                .newJob(keepAliveJob.class)
                .withIdentity("keepAliveJob", "keepAliveGroup")
                .build();

        keepAliveTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("cronTriggerKeepAlive", "keepAliveGroup")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0/3 * * * * ?")
                )
                .build();

        log.info("CronTab schedules initialized");
    }

    public void startSchedules() {
        try {
            Scheduler randomColorScheduler = new StdSchedulerFactory().getScheduler();
            randomColorScheduler.start();
            randomColorScheduler.scheduleJob(randomColorJob, randomColorTrigger);
            log.info("Random color schedule started");

            Scheduler clearMuteScheduler = new StdSchedulerFactory().getScheduler();
            clearMuteScheduler.start();
            clearMuteScheduler.scheduleJob(clearMuteJob, clearMuteTrigger);
            log.info("Clear mute schedule started");

            Scheduler luckyBoyScheduler = new StdSchedulerFactory().getScheduler();
            luckyBoyScheduler.start();
            luckyBoyScheduler.scheduleJob(luckyBoyJob, luckyBoyTrigger);
            log.info("Lucky boy schedule started");

            Scheduler saveMuteScheduler = new StdSchedulerFactory().getScheduler();
            saveMuteScheduler.start();
            saveMuteScheduler.scheduleJob(saveMuteJob, saveMuteTrigger);
            log.info("Save mute schedule started");

            Scheduler keepAliveScheduler = new StdSchedulerFactory().getScheduler();
            keepAliveScheduler.start();
            keepAliveScheduler.scheduleJob(keepAliveJob, keepAliveTrigger);
            log.info("Keep alive connect to db schedule started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}