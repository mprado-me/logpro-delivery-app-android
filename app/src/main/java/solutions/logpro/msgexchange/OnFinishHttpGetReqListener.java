package solutions.logpro.msgexchange;

import solutions.logpro.msg.InMsg;

/**
 * Created by MarcoAurelio on 22/02/2016.
 */
public interface OnFinishHttpGetReqListener<T extends InMsg> {
    public void OnFinishHttpGetReq(T inMsg);
}
