package solutions.logpro.msgexchange;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import solutions.logpro.msg.chat.InChatMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class MsgFromServerHandler {
    private static final String LOG_TAG = MsgFromServerHandler.class.getName() + Consts.GENERAL_LOG_TAG;

    private ArrayList<OnInChatMsgArrivesListener> mOnMsgFromServerArrivesArrayList;

    public MsgFromServerHandler() {
        mOnMsgFromServerArrivesArrayList = new ArrayList<OnInChatMsgArrivesListener>();
    }

    public void onInMsgsArrives(JSONArray jsonArray) {
        Log.d(LOG_TAG, "onInMsgsArrives");
        Log.d(LOG_TAG, "jsonArray.toString() = " + jsonArray.toString());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = getJSONObject(jsonArray, i);
            String typeAsString = getType(jsonObject);
            switch (Consts.MsgType.type(typeAsString)) {
                case IN_CHAT:
                    onInChatMsgArrives(getMsgContent(jsonObject));
                    break;
            }
        }
    }

    private JSONObject getMsgContent(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONObject(Consts.MsgFetcher.CONTENT_PARAM);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private String getType(JSONObject jsonObject) {
        try {
            return jsonObject.getString(Consts.MsgFetcher.TYPE_PARAM);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private JSONObject getJSONObject(JSONArray jsonArray, int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void addOnChatMsgFromServerArrivesListener(OnInChatMsgArrivesListener listener) {
        mOnMsgFromServerArrivesArrayList.add(listener);
    }

    private void onInChatMsgArrives(JSONObject msgContent) {
        Log.d(LOG_TAG, "onInChatMsgArrives()");
        Log.d(LOG_TAG, "msgContent = "+msgContent.toString());
        InChatMsg inChatMsg = new InChatMsg(msgContent);
        for (OnInChatMsgArrivesListener onInChatMsgArrivesFromServerArrives : mOnMsgFromServerArrivesArrayList ) {
            onInChatMsgArrivesFromServerArrives.onInChatMsgArrives(inChatMsg);
        }
    }


}
