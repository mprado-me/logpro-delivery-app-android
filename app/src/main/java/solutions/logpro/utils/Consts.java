package solutions.logpro.utils;

import solutions.logpro.msg.auth.NoAuthReason;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class Consts {
    public static final String GENERAL_LOG_TAG = "MPDEBUG";
    public static final String MSG_SCHEME = "https";
    public static final String MSG_AUTHORITY = "logprodeliveryserver.appspot.com";

    public static class LoginInfo{
        public static final String FILE_NAME = "login";
        public static final String EMAIL_KEY = "email";
        public static final String PASSWORD_KEY = "password";
    }

    public static class Auth {
        public static final String MSG_AUTH_PATH = "mobile/auth-user";

        public static final String AUTH_OK_PARAM = "auth_ok";
        public static final String NO_AUTH_REASON_PARAM = "reason";
        // TODO: Colocar a enum NoAuthReason aqui
        public static NoAuthReason noAuthReason(String reasonAsText){
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

    public static class Chat {
        public static final String NEW_CHAT_MSG_PATH = "mobile/post-chat-msg";
        public static final String FROM_PARAM = "from";
        public static final String CONTENT_PARAM = "content";
    }

    public static class MsgFetcher {
        public static final String MSG_FETCH_PATH = "mobile/fetch-msg";

        public static final String TYPE_PARAM = "type";
        public static final String CONTENT_PARAM = "content";
    }

    public enum MsgType {
        IN_CHAT;

        public static MsgType type(String type){
            switch (type){
                case "server-to-mob-chat":
                    return MsgType.IN_CHAT;
                default:
                    throw new EnumConstantNotPresentException(MsgType.class, "");
            }
        }
    }

}
