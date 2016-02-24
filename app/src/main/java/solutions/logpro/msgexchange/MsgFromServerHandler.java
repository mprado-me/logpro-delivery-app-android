package solutions.logpro.msgexchange;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class MsgFromServerHandler {
    private static final String LOG_TAG = MsgFromServerHandler.class.getName()+ Consts.GENERAL_LOG_TAG;

    public void onInMsgsArrives(JSONArray jsonArray){
        Log.d(LOG_TAG, "onInMsgsArrives");
        Log.d(LOG_TAG, "jsonArray.toString() = " + jsonArray.toString());
    }
}
