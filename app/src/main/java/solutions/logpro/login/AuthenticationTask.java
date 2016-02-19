package solutions.logpro.login;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import solutions.logpro.utils.Consts;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class AuthenticationTask extends AsyncTask<Void, Void, AuthStatus> {

    private static final String LOG_TAG = AuthenticationTask.class.getName()+ Consts.GENERAL_LOG_TAG;

    private OnAuthenticationFinishListener mFinishListener;

    public AuthenticationTask(OnAuthenticationFinishListener finishListener) {
        super();
        mFinishListener = finishListener;
    }

    @Override
    protected AuthStatus doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(Consts.Login.DOMAIN_URL+Consts.Login.AUTH_USER_PATH);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.d(LOG_TAG, "inputStream == null");
                return AuthStatus.NO_CONNECTION;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            line = reader.readLine();
            buffer.append(line);
            while ((line = reader.readLine()) != null) {
                buffer.append("\n"+line);
            }

            Log.d(LOG_TAG, "buffer.toString() = " + buffer.toString());
            return AuthStatus.valueOf(buffer.toString());
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, e.getMessage());
            return AuthStatus.NO_CONNECTION;
        } catch (ProtocolException e) {
            Log.d(LOG_TAG, e.getMessage());
            return AuthStatus.NO_CONNECTION;
        } catch (IOException e) {
            Log.d(LOG_TAG, e.getMessage());
            return AuthStatus.NO_CONNECTION;
        }
    }

    @Override
    protected void onPostExecute(AuthStatus result) {
        super.onPostExecute(result);
        mFinishListener.onAuthenticationFinish(result);
    }
}
