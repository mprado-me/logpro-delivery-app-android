package solutions.logpro.reportproblem;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import solutions.logpro.MainActivity;
import solutions.logpro.R;
import solutions.logpro.utils.MyEditText;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 04/02/2016.
 */
public class ReportProblemByMessageFragment extends ReportProblemTabContentFragment {

    private MyEditText mMessageEditText;

    public ReportProblemByMessageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.report_problem_by_message_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMessageEditText = getMessageEditText();
        setMessageEditTextOnFocusChangeListener();
        setMessageEditTextOnBackPressedListener();
        mMessageEditText.clearFocus();
    }

    public MyEditText getMessageEditText() {
        return (MyEditText) getView().findViewById(R.id.message_edit_text);
    }

    private void setMessageEditTextOnFocusChangeListener() {
        mMessageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d(LOG_TAG, "mMessageEditText.onFocusChange(), hasFocus = " + hasFocus);
                mMessageEditText.setSelected(hasFocus);
                mMessageEditText.setCursorVisible(hasFocus);
            }
        });
    }

    private void setMessageEditTextOnBackPressedListener() {
        mMessageEditText.setOnBackPressedListener(new MyEditText.OnBackPressedListener(){
            @Override
            public void onBackPressed() {
                mMessageEditText.clearFocus();
            }
        });
    }
}
