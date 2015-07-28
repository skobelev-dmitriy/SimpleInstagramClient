package rf.digitworld.simpleinstagramclient.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Дмитрий on 14.07.2015.
 */
public class DateUtils {

    public static String timeAgo(String createdTime){
        String text=null;
        long created=Long.valueOf(createdTime);
        long now= new Date().getTime()/1000;

        long diff=(now-created); //seconds
        Log.d("myLogs","now="+now+" diff="+diff);
        if (diff>60){
            long  minuts=diff%60; //minuts
            text=minuts+" мин.";
        }

        if (diff>3600){
            long   hours=diff/3600; //hours
            text=hours+" ч.";
        }

        if (diff>86400) {
            long   days=diff/86400;
            text=days+" дн.";
        }
        if (diff>604800) {
            long   days=diff/604800;
            text=days+" нед.";
        }
        if (diff>2592000) {
            long   months=diff/2592000;
            text=months+" мес.";
        }
        if (diff>31536000) {
            long   years=diff/31536000;
            text=years+" г.";
        }

        /*switch (diff/1000){
            case 1:
                break;

        }*/

        return text;
    }
}
