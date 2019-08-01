package timduk.leonid.modules;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;

import java.sql.SQLException;

public class keepAliveJob implements Job
{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        try
        {
            Leonid.database.select("*", Leonid.settings.db_tableNameSettings, "settings_name='token'");
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}