package solutions.logpro.msgexchange;

import solutions.logpro.msg.InMsg;
import solutions.logpro.msg.OutMsg;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public class HttpsGetReqParam<T extends InMsg> {
    public OutMsg outMsg;
    public InMsg inMsg;
    public OnFinishHttpGetReqListener<T> finishHttpGetReqListener;
    public HttpsReqsManager httpsReqsManager;
}
