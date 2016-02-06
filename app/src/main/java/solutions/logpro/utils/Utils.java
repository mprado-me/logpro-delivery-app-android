package solutions.logpro.utils;

/**
 * Created by MarcoAurelio on 05/02/2016.
 */
public class Utils {
    public static final String GENERAL_LOG_TAG = "MPDEBUG";

    public static String formatTelNumber(CharSequence text) {
        String tel = "";
        for (int i = 0; i < text.length(); i++){
            Character c = text.charAt(i);
            if( Character.isDigit(c) )
                tel += c;
        }
        return tel;
    }
}
