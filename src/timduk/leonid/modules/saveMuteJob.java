package timduk.leonid.modules;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import timduk.leonid.Leonid;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class saveMuteJob implements Job
{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        try
        {
            Leonid.database.delete(Leonid.settings.db_tableNameMute);
            List<String> SQLPut = new ArrayList<>();
            for (Map.Entry<Long, Long> values : Leonid.settings.mutedUsers.entrySet())
            {
                SQLPut.add(values.getKey() + ", " + values.getValue());
            }
            if (!SQLPut.isEmpty())
                Leonid.database.insert(Leonid.settings.db_tableNameMute, "ID, timestamp_end", SQLPut);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


    }
}