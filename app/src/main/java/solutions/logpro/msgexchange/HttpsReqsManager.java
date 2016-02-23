package solutions.logpro.msgexchange;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import solutions.logpro.msg.InMsg;
import solutions.logpro.msg.OutMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class HttpsReqsManager {
    private static String LOG_TAG = HttpsReqsManager.class.getName() + Consts.GENERAL_LOG_TAG;

    private Map<String, HttpsGetReq> mGetsById;
    private Map<String, HttpsPostReq> mPostsById;

    public HttpsReqsManager(){
        mGetsById = new HashMap<String, HttpsGetReq>();
        mPostsById = new HashMap<String, HttpsPostReq>();
    }

    public void finishHttpGetReq(String id){
        mGetsById.remove(id);
    }

    public <T extends InMsg> void makeGet(OnFinishHttpGetReqListener<T> finishHttpGetReqListener, OutMsg outMsg, InMsg inMsg){
        Log.d(LOG_TAG, "makeGet()");
        HttpsGetReq<T> req = new HttpsGetReq();
        mGetsById.put(outMsg.getId(), req);
        HttpsGetReqParam<T> param = new HttpsGetReqParam();
        param.httpsReqsManager = this;
        param.finishHttpGetReqListener = finishHttpGetReqListener;
        param.outMsg = outMsg;
        param.inMsg = inMsg;
        req.execute(param);
    }

    public void makePost(InMsg inMsg){

    }

    public void cancelAll(){
        for (HttpsGetReq httpsGetReq : mGetsById.values() ) {
            httpsGetReq.cancel(true);
        }
    }
}
