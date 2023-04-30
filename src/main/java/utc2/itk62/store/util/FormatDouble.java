package utc2.itk62.store.util;

import java.text.DecimalFormat;
import java.text.ParseException;

public class FormatDouble {
    public static String toString(double value) {
        DecimalFormat formatter = new DecimalFormat("#,##0");
        return formatter.format(value);
    }

    public static double toDouble(String value) {
        DecimalFormat formatter = new DecimalFormat("#,##0");
        try {
            return formatter.parse(value).doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
