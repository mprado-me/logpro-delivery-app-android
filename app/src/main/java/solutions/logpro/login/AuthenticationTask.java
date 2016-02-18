package solutions.logpro.login;

import android.os.AsyncTask;
import android.view.View;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class AuthenticationTask extends AsyncTask<Void, Void, Integer> {

    private OnAuthenticationFinishListener mFinishListener;

    public AuthenticationTask(OnAuthenticationFinishListener finishListener) {
        super();
        mFinishListener = finishListener;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Consts.Login.SUCCESS;
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        mFinishListener.onAuthenticationFinish(result);
    }
}
