package solutions.logpro.reportproblem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import solutions.logpro.R;
import solutions.logpro.login.LoginInfo;
import solutions.logpro.msg.chat.InChatMsg;
import solutions.logpro.msg.chat.OutChatMsg;
import solutions.logpro.msgexchange.HttpsReqsManager;
import solutions.logpro.msgexchange.MsgFetcher;
import solutions.logpro.msgexchange.OnInChatMsgArrivesListener;
import solutions.logpro.msgexchange.OnFinishHttpsPostReqListener;

/**
 * Created by MarcoAurelio on 23/02/2016.
 */
public class ChatFragment extends ReportProblemTabContentFragment implements OnFinishHttpsPostReqListener, OnInChatMsgArrivesListener {

    private EditText mEditText;
    private Button mButton;
    private HttpsReqsManager mHttpsReqsManager;
    private MsgFetcher mMsgFetcher;
    private ChatMsgArrayAdapter mChatMsgArrayAdapter;

    @Override
    public void onStart() {
        super.onStart();
        mEditText = (EditText) getView().findViewById(R.id.chatMsgEditText);
        mButton = (Button) getView().findViewById(R.id.sendChatMsgButton);
        mHttpsReqsManager = new HttpsReqsManager();
        final ChatFragment chatFragment = this;
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChatMsg();
                mEditText.getText().clear();
                mEditText.clearFocus();
            }
        });
        mChatMsgArrayAdapter = new ChatMsgArrayAdapter(getActivity());
        mMsgFetcher = new MsgFetcher(getActivity());
        mMsgFetcher.onStart();
        mMsgFetcher.handler().addOnChatMsgFromServerArrivesListener(this);
        setListViewAdapter();
    }

    private void setListViewAdapter() {
        ListView listView = (ListView) getView().findViewById(R.id.listViewChat);
        listView.setAdapter(mChatMsgArrayAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        mMsgFetcher.onStop();
    }

    private void sendChatMsg() {
        OutChatMsg outChatMsg = new OutChatMsg(LoginInfo.email(getActivity()), mEditText.getText().toString());
        mHttpsReqsManager.makePost(this, outChatMsg);
    }

    @Override
    public void OnFinishHttpsPostReq(boolean sent) {
        Log.d(LOG_TAG, "OnFinishHttpsPostReq()");
        Log.d(LOG_TAG, "sent = " + sent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.chat_fragment, container, false);
    }

    @Override
    public void onInChatMsgArrives(InChatMsg inChatMsg) {
        Log.d(LOG_TAG, "onInChatMsgArrives()");
        mChatMsgArrayAdapter.add(inChatMsg);
        mChatMsgArrayAdapter.notifyDataSetChanged();
    }
}
