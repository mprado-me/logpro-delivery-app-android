package solutions.logpro.msg;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public abstract class InMsg {

    private static final String LOG_TAG = InMsg.class.getName()+ Consts.GENERAL_LOG_TAG;

    public void initParams(String jsonText) {
        try {
            JSONObject jsonObject = new JSONObject(jsonText);
            setParams(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected abstract void setParams(JSONObject jsonObject);
}
