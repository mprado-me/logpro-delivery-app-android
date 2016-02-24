package solutions.logpro.msgexchange;

import solutions.logpro.msg.chat.InChatMsg;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public interface OnInChatMsgArrivesListener {
    public void onInChatMsgArrives(InChatMsg inChatMsg);
}
