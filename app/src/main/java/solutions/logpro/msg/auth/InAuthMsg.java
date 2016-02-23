package solutions.logpro.msg.auth;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import solutions.logpro.msg.InMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class InAuthMsg extends InMsg {

    private boolean authOk;
    private NoAuthReason noAuthReason;

    private static final String LOG_TAG =  InAuthMsg.class.getName()+ Consts.GENERAL_LOG_TAG;

    @Override
    protected void setParams(JSONObject jsonObject) {
        Log.d(LOG_TAG, jsonObject.toString());

        try {
            authOk = jsonObject.getBoolean(Consts.Auth.AUTH_OK_PARAM);
            if(!authOk){
                noAuthReason = Consts.Auth.getNoAuthReason(jsonObject.getString(Consts.Auth.NO_AUTH_REASON_PARAM));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG, "authOk = " + authOk);
        Log.d(LOG_TAG, "noAuthReason = " + noAuthReason);
    }

    public boolean getAuthOk() {
        return authOk;
    }

    public NoAuthReason getNoAuthReason() {
        return noAuthReason;
    }
}
