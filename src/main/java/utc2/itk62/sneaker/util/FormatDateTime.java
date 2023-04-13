package utc2.itk62.sneaker.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FormatDateTime {
    public static String formatTimeStampToString(Timestamp timestamp) {
        String s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp);
        return s;
    }
}
