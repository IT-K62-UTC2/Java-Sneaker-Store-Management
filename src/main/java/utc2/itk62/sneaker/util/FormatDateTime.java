package utc2.itk62.sneaker.util;

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
}
