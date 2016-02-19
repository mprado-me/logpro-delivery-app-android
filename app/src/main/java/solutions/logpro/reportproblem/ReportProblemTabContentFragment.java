package solutions.logpro.reportproblem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 04/02/2016.
 */
public class ReportProblemTabContentFragment extends Fragment {

    protected final String LOG_TAG = this.getClass().getName()+ Consts.GENERAL_LOG_TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
