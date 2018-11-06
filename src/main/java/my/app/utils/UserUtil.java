package my.app.utils;

import java.text.DecimalFormat;

public class UserUtil {
    public static String formatId(int id){
        DecimalFormat decimalFormat = new DecimalFormat("0000000");
        return decimalFormat.format(id);
    }
}
