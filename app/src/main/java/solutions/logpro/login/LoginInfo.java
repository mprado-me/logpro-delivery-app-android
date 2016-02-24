package solutions.logpro.login;

import android.app.Activity;
import android.content.SharedPreferences;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class LoginInfo {

    public static void save(Activity activity, String email, String password) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(Consts.LoginInfo.FILE_NAME, Activity.MODE_PRIVATE).edit();
        editor.putString(Consts.LoginInfo.EMAIL_KEY, email);
        editor.putString(Consts.LoginInfo.PASSWORD_KEY, password);
        editor.commit();
    }

    public static String email(Activity activity) {
        SharedPreferences loginInfo = activity.getSharedPreferences(Consts.LoginInfo.FILE_NAME, Activity.MODE_PRIVATE);
        return loginInfo.getString(Consts.LoginInfo.EMAIL_KEY, null);
    }

    public static String password(Activity activity) {
        SharedPreferences loginInfo = activity.getSharedPreferences(Consts.LoginInfo.FILE_NAME, Activity.MODE_PRIVATE);
        return loginInfo.getString(Consts.LoginInfo.PASSWORD_KEY, null);
    }
}
