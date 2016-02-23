package solutions.logpro.utils;

import solutions.logpro.msg.auth.NoAuthReason;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class Consts {
    public static final String GENERAL_LOG_TAG = "MPDEBUG";
    public static final String MSG_SCHEME = "https";
    public static final String MSG_AUTHORITY = "logprodeliveryserver.appspot.com";

    public static class Auth {
        public static final String MSG_AUTH_PATH = "mobile/auth-user";

        public static final String AUTH_OK_PARAM = "auth_ok";
        public static final String NO_AUTH_REASON_PARAM = "reason";
        public static NoAuthReason getNoAuthReason(String reasonAsText){
            switch(reasonAsText){
                case "email":
                    return NoAuthReason.EMAIL;
                case "password":
                    return NoAuthReason.PASSWORD;
                default:
                    throw new EnumConstantNotPresentException(NoAuthReason.class, "");
            }
        }
    }

}
