package solutions.logpro.msgexchange;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import solutions.logpro.msg.InMsg;
import solutions.logpro.msg.OutMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class HttpsPostReq extends AsyncTask<HttpsPostReqParam, Void, Boolean> {
    private static String LOG_TAG = HttpsGetReq.class.getName() + Consts.GENERAL_LOG_TAG;

    HttpsPostReqParam mHttpsPostReqParam;

    @Override
    protected Boolean doInBackground(HttpsPostReqParam... params) {
        Log.d(LOG_TAG, "doInBackground()");
        mHttpsPostReqParam = params[0];
        OutMsg outMsg = mHttpsPostReqParam.outMsg;

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(outMsg.getUri().toString());

            Log.d(LOG_TAG, "url = " + url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.connect();

            // TODO: Ver se tem alguma outra de forma de fazer o POST, sem usar urlConnection.getInputStream();
            urlConnection.getInputStream();

            return true;
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "MalformedURLException");
            return false;
        } catch (ProtocolException e) {
            Log.d(LOG_TAG, "ProtocolException");
            return false;
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException");
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean sent){
        Log.d(LOG_TAG, "onPostExecute");
        mHttpsPostReqParam.finishHttpsPostReqListener.OnFinishHttpsPostReq(sent);
        mHttpsPostReqParam.httpsReqsManager.finishHttpGetReq(mHttpsPostReqParam.outMsg.getId());
    }
}