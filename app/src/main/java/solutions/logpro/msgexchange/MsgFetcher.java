package solutions.logpro.msgexchange;

import android.app.Activity;
import android.util.Log;

import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class MsgFetcher {
    private static final String LOG_TAG = MsgFetcher.class.getName()+ Consts.GENERAL_LOG_TAG;

    private MsgFetcherThread mMsgFetcherThread;
    private Activity mActivity;
    private MsgFromServerHandler mMsgFromServerHandler;

    public MsgFetcher(Activity activity){
        mActivity = activity;
        mMsgFromServerHandler = new MsgFromServerHandler();
    }

    public void onStart(){
        mMsgFetcherThread = new MsgFetcherThread(mActivity, mMsgFromServerHandler);
        mMsgFetcherThread.start();
    }

    public void onStop(){
        Log.d(LOG_TAG, "MsgFetcher.onStop");
        mMsgFetcherThread.interrupt();
    }

    public MsgFromServerHandler handler(){
        return mMsgFromServerHandler;
    }
}
