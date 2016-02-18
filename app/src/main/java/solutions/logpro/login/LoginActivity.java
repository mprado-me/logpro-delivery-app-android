package solutions.logpro.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import solutions.logpro.MainActivity;
import solutions.logpro.R;
import solutions.logpro.utils.Consts;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 17/02/2016.
 */
public class LoginActivity extends Activity implements OnAuthenticationFinishListener {

    private static String LOG_TAG = LoginActivity.class.getName() + Utils.GENERAL_LOG_TAG;

    private EditText mEmail;
    private EditText mPassword;
    private ProgressBar mProgressBar;
    private Button mLoginButton;
    private TextView mInfoTextView;
    private boolean mAuthenticated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);

        if(!alreadyLogged()){
            initViewsRefs();
            initButtonListener();
        } else {
            launchMainActivity();
        }

    }

    private boolean alreadyLogged() {
        return false;
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
        mLoginButton.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        AuthenticationTask authenticationTask = new AuthenticationTask(this);
        authenticationTask.execute();
    }

    @Override
    public void onAuthenticationFinish(int result) {
        switch(result){
            case Consts.Login.NO_CONNECTION:
                hideProgressAndShowLoginButton();
                setLoginInfoText(R.string.no_connection);
                break;
            case Consts.Login.SUCCESS:
                launchMainActivity();
                break;
            case Consts.Login.EMAIL_NOT_REGISTERED:
                hideProgressAndShowLoginButton();
                setLoginInfoText(R.string.email_not_registered);
                break;
            case Consts.Login.INCORRECT_PASSWORD:
                hideProgressAndShowLoginButton();
                setLoginInfoText(R.string.incorrect_password);
                break;
            default:
                break;
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

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        Log.d(LOG_TAG, "onSaveInstanceState");
        // Save the user's current game state
        savedInstanceState.putBoolean("oio LoginActivity", false);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void setLoginInfoText(int stringId) {
        mInfoTextView.setText(getResources().getString(stringId));
    }
}
