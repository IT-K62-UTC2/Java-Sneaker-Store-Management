package utc2.itk62.store.util;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class FormatDateTime {
    public static String timestampToString(Timestamp timestamp) {
        String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);
        return s;
    }

    public static Timestamp stringToTimestamp(String timestamp) {
        return null;
    }

    public static int compareDatepicker(LocalDate fromDate, LocalDate toDate) {
        if(toDate.isBefore(fromDate)) {
            return -1;
        }

        if (toDate.equals(fromDate)) {
            return 0;
        }
        return 1;
    }

    public static String timeToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(timestamp.getTime());
        return dateFormat.format(date);
    }
    public static String dateToString(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(timestamp.getTime());
        return dateFormat.format(date);
    }



    public static String DateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public static String timeToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }
}
