package solutions.logpro.msg.auth;

import android.net.Uri;

import solutions.logpro.msg.OutMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class OutAuthMsg extends OutMsg {

    private String email;
    private String password;

    public OutAuthMsg(String email, String password){
        this.email = email;
        this.password = password;
    }

    @Override
    public Uri getUri() {
        return new Uri.Builder()
                .scheme(Consts.MSG_SCHEME)
                .authority(Consts.MSG_AUTHORITY)
                .path(Consts.Auth.MSG_AUTH_PATH)
                .appendQueryParameter("email", email)
                .appendQueryParameter("password", password)
                .build();
    }
}
