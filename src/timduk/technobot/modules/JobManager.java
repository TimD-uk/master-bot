package timduk.technobot.modules;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import timduk.technobot.jobs.luckyJob;
import timduk.technobot.jobs.muteJob;
import timduk.utils.log;

public class JobManager
{
    private static String everyMinute = "0 0/1 * 1/1 * ? *";
    private static String twiceWeek = "0 0 10 ? * MON,THU *";
    public static void load() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();

            scheduler.start();
            JobDetail jobDetail = JobBuilder.newJob(luckyJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(
                            CronScheduleBuilder
                                    .cronSchedule(twiceWeek)
                    )
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            log.info("Lucky man loaded");


            SchedulerFactory schedulerFactoryMute = new StdSchedulerFactory();
            Scheduler schedulerMute = schedulerFactoryMute.getScheduler();

            schedulerMute.start();
            JobDetail jobDetailMute = JobBuilder.newJob(muteJob.class).build();
            Trigger triggerMute = TriggerBuilder.newTrigger()
                    .startNow()
                    .withSchedule(
                            CronScheduleBuilder
                                    .cronSchedule(everyMinute)
                    )
                    .build();
            schedulerMute.scheduleJob(jobDetailMute, triggerMute);
            log.info("Mute job loaded");
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}