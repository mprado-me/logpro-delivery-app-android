package solutions.logpro.reportproblem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import solutions.logpro.R;
import solutions.logpro.msg.chat.OutChatMsg;
import solutions.logpro.msgexchange.HttpsReqsManager;
import solutions.logpro.msgexchange.OnFinishHttpsPostReqListener;

/**
 * Created by MarcoAurelio on 23/02/2016.
 */
public class ChatFragment extends ReportProblemTabContentFragment implements OnFinishHttpsPostReqListener{

    private EditText mEditText;
    private Button mButton;
    private HttpsReqsManager mHttpsReqsManager;

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
                }
        });
    }

    private void sendChatMsg() {
        // TODO: pegar as informações corretas do usuário
        OutChatMsg outChatMsg = new OutChatMsg("armstrong@gmail.com", "Esse é um pequeno passo para o desenvolvimento, mas um grande passo para a LogPRO");
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
}
