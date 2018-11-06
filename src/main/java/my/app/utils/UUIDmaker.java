package my.app.utils;

import java.util.UUID;

public class UUIDmaker {
    public static String getUUID(){
        String res = null;
        res = UUID.randomUUID().toString().replace("-","");
        return res;
    }
}
