package solutions.logpro.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import solutions.logpro.MainActivity;
import solutions.logpro.R;
import solutions.logpro.msg.InMsg;
import solutions.logpro.msg.auth.InAuthMsg;
import solutions.logpro.msg.auth.OutAuthMsg;
import solutions.logpro.msgexchange.HttpsReqsManager;
import solutions.logpro.msgexchange.OnFinishHttpGetReqListener;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
// TODO: Criar a classe ReqActivity que já possui um HttpsReqsManager, fazer com que herde LoginActivity de ReqActivity
// TODO: Criar a classe ReqFragment que possui o mesmo objetivo da classe ReqActivity
public class LoginActivity extends Activity implements OnFinishHttpGetReqListener<InAuthMsg> {

    private static String LOG_TAG = LoginActivity.class.getName() + Consts.GENERAL_LOG_TAG;

    private EditText mEmail;
    private EditText mPassword;
    private ProgressBar mProgressBar;
    private Button mLoginButton;
    private TextView mInfoTextView;
    private boolean mAuthenticated;
    private HttpsReqsManager mHttpsReqsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);

        mHttpsReqsManager = new HttpsReqsManager();
        initViewsRefs();
        initButtonListener();
    }

    @Override
    protected void onStop(){
        mHttpsReqsManager.cancelAll();
        super.onStop();
    }

    private void initViewsRefs() {
        mProgressBar = (ProgressBar) findViewById(R.id.authenticating_progress_bar);
        mEmail = (EditText) findViewById(R.id.email_edit_text);
        mPassword = (EditText) findViewById(R.id.password_edit_text);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mInfoTextView = (TextView) findViewById(R.id.not_successful_login_info);
    }

    private void initButtonListener() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initAuthentication();
            }
        });
    }

    private void initAuthentication() {
        Log.d(LOG_TAG, "initAuthentication()");
        mLoginButton.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();
        OutAuthMsg outAuthMsg = new OutAuthMsg(email, password);
        mHttpsReqsManager.makeGet(this, outAuthMsg, new InAuthMsg());
    }

    public void OnFinishHttpGetReq(InAuthMsg inAuthMsg) {
        Log.d(LOG_TAG, "OnFinishHttpGetReq called");

        if(inAuthMsg == null) {
            hideProgressAndShowLoginButton();
            setLoginInfoText(R.string.no_connection);
        } else if(inAuthMsg.getAuthOk()) {
            launchMainActivity();
        } else{
            switch (inAuthMsg.getNoAuthReason()){
                case EMAIL:
                    hideProgressAndShowLoginButton();
                    setLoginInfoText(R.string.email_not_registered);
                    break;
                case PASSWORD:
                    hideProgressAndShowLoginButton();
                    setLoginInfoText(R.string.incorrect_password);
                    break;
            }
        }
    }

    private void hideProgressAndShowLoginButton() {
        mLoginButton.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void launchMainActivity(){
        // TODO: mAuthenticated será true quando o usuário for autenticado e não quando a MainActivity ser lançada
        mAuthenticated = true;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
        this.finish();
    }

    public void setLoginInfoText(int stringId) {
        mInfoTextView.setText(getResources().getString(stringId));
    }
}
