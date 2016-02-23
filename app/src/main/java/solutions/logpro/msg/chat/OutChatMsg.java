package solutions.logpro.msg.chat;

import android.net.Uri;

import solutions.logpro.msg.OutMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class OutChatMsg extends OutMsg {

    private String mFrom;
    private String mContent;

    public OutChatMsg(String from, String content){
        mFrom = from;
        mContent = content;
    }

    @Override
    public Uri getUri() {
        return new Uri.Builder()
                .scheme(Consts.MSG_SCHEME)
                .authority(Consts.MSG_AUTHORITY)
                .path(Consts.Chat.NEW_CHAT_MSG_PATH)
                .appendQueryParameter("from", mFrom)
                .appendQueryParameter("content", mContent)
                .build();
    }
}
