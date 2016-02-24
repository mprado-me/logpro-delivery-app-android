package solutions.logpro.msgexchange;

import android.app.Activity;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class MsgFetcher {
    public MsgFetcher(Activity activity, MsgFromServerHandler msgFromServerHandler){
        MsgFetcherThread msgFetcherThread = new MsgFetcherThread(activity, msgFromServerHandler);
        msgFetcherThread.start();
    }
}
