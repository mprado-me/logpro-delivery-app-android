package solutions.logpro.msg.chat;

import org.json.JSONException;
import org.json.JSONObject;

import solutions.logpro.msg.InMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class InChatMsg extends InMsg {

    private String mFrom;
    private String mContent;

    public InChatMsg(JSONObject jsonObject){
        setParams(jsonObject);
    }

    @Override
    protected void setParams(JSONObject jsonObject) {
        try {
            mFrom = jsonObject.getString(Consts.Chat.FROM_PARAM);
            mContent = jsonObject.getString(Consts.Chat.CONTENT_PARAM);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String from() {
        return mFrom;
    }

    public String content() {
        return mContent;
    }
}
