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
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class HttpsGetReq<T extends InMsg> extends AsyncTask<HttpsGetReqParam<T>, Void, Void>{
    private static String LOG_TAG = HttpsGetReq.class.getName() + Consts.GENERAL_LOG_TAG;

    private HttpsGetReqParam mHttpsGetReqParam;

    // Retorna null quando não for possível obter a mensagem de resposta
    @Override
    protected Void doInBackground(HttpsGetReqParam... params) {
        Log.d(LOG_TAG, "doInBackground()");
        mHttpsGetReqParam = params[0];

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(mHttpsGetReqParam.outMsg.getUri().toString());

            Log.d(LOG_TAG, "url = " + url.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            line = reader.readLine();
            buffer.append(line);
            while ((line = reader.readLine()) != null) {
                buffer.append("\n"+line);
            }

            Log.d(LOG_TAG, "buffer.toString() = " + buffer.toString());

            mHttpsGetReqParam.inMsg.initParams(buffer.toString());
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "MalformedURLException");
            mHttpsGetReqParam.inMsg = null;
        } catch (ProtocolException e) {
            Log.d(LOG_TAG, "ProtocolException");
            mHttpsGetReqParam.inMsg = null;
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException");
            mHttpsGetReqParam.inMsg = null;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v){
        Log.d(LOG_TAG, "onPostExecute");
        mHttpsGetReqParam.finishHttpGetReqListener.OnFinishHttpGetReq(mHttpsGetReqParam.inMsg);
        mHttpsGetReqParam.httpsReqsManager.finishHttpGetReq(mHttpsGetReqParam.outMsg.getId());
    }
}
