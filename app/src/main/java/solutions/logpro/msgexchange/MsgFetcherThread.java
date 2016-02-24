package solutions.logpro.msgexchange;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class MsgFetcherThread extends Thread {
    private static final String LOG_TAG = MsgFetcherThread.class.getName()+Consts.GENERAL_LOG_TAG;

    Activity mActivity;
    MsgFromServerHandler mMsgFromServerHandler;

    public MsgFetcherThread(Activity activity, MsgFromServerHandler msgFromServerHandler){
        mActivity = activity;
        mMsgFromServerHandler = msgFromServerHandler;
    }

    @Override
    public void run() {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        while(true){
            try {
                Uri uri = new Uri.Builder()
                        .scheme(Consts.MSG_SCHEME)
                        .authority(Consts.MSG_AUTHORITY)
                        .path(Consts.MsgFetcher.MSG_FETCH_PATH)
                        .appendQueryParameter("email", "marco.pdsv@gmail.com")
                        .build();

                URL url = new URL(uri.toString());

                Log.d(LOG_TAG, "url = " + url.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    continue;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                line = reader.readLine();
                buffer.append(line);
                while ((line = reader.readLine()) != null) {
                    buffer.append("\n"+line);
                }

                Log.d(LOG_TAG, "buffer.toString() = " + buffer.toString());

                urlConnection.disconnect();

                try {
                    mMsgFromServerHandler.onInMsgsArrives(new JSONArray(buffer.toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                Log.d(LOG_TAG, "ProtocolException");
                continue;
            } catch (IOException e) {
                Log.d(LOG_TAG, "IOException");
                continue;
            }
        }
    }

}
