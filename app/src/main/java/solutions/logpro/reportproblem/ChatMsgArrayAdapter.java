package solutions.logpro.reportproblem;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import solutions.logpro.R;
import solutions.logpro.msg.chat.InChatMsg;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 24/02/2016.
 */
public class ChatMsgArrayAdapter extends ArrayAdapter<InChatMsg> {

    // TODO: Criar a minha classe de Log que recebe uma referencia para a classe, um this, e dai extrai o nome da classe
    public static final String LOG_TAG = ChatMsgArrayAdapter.class.getName()+ Consts.GENERAL_LOG_TAG;

    public ChatMsgArrayAdapter(Context context) {
        super(context, R.layout.chat_msg_item, new ArrayList<InChatMsg>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(LOG_TAG, "getView()");

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_msg_item, parent, false);
        }
        InChatMsg inChatMsg = getItem(position);
        TextView from = (TextView) convertView.findViewById(R.id.from_text_view);
        TextView content = (TextView) convertView.findViewById(R.id.content_text_view);
        from.setText("From: " + inChatMsg.from());
        content.setText(inChatMsg.content());

        if(position%2==0){
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.blue1));
        }else{
            convertView.setBackgroundColor(getContext().getResources().getColor(R.color.blue2));
        }

        return convertView;
    }
}
