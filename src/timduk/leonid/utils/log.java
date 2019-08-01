package timduk.leonid.utils;

import java.util.Calendar;

public class log
{
    public static void info(Object o)
    {
        String str = o.toString();
        Calendar cal = Calendar.getInstance();
        String time = "[" + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND) + "] "; //TODO Нолики добавлять
        System.out.println(time + str);
        //TODO сделать красиво
    }
}