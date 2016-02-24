package solutions.logpro;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.HashMap;

import solutions.logpro.msgexchange.MsgFetcher;
import solutions.logpro.msgexchange.MsgFromServerHandler;
import solutions.logpro.reportproblem.ReportProblemFragment;
import solutions.logpro.utils.Consts;
import solutions.logpro.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getName() + Consts.GENERAL_LOG_TAG;

    NavigationManager mNavigationManager;
    private MsgFetcher mMsgFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);

        // Explicação da linha: SecondaryWindowFragment.setMainActivity(this);
        // Qualquer SecondaryWindowFragment precisa de uma referencia para a MainActivity, pois
        // ao ser exibido, onStart(), precisa mudar o título na barra superior e o item selecionao
        // no NavigationDrawer
        SecondaryWindowFragment.setMainActivity(this);
        mNavigationManager = new NavigationManager(this);
        InitNavigationDrawerMenu();
        mMsgFetcher = new MsgFetcher(this, new MsgFromServerHandler());
}


    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "onStart");
        // Não mudar a ordem de chamada de onSelected() e super.onStart(). onSelected() DEVE ser chamado primeiro
        // Como super.onStart() chama o onStart() dos SecondaryWindowFragments ativos, para que a seleção no
        // menu de navegação e o preenchimento do título na barra superior sejam preenchidos corretamente
        // quando um SecondaryWindowFragment estiver ativo, é necessário primeiro chamar o onStart()
        // da MainActivity e depois o onStart() do SecondaryWindowFragment ativo.
        onSelected();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");

        mNavigationManager = null;
        SecondaryWindowFragment.destroyMainActivityReference();
    }

    private void InitNavigationDrawerMenu() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(mNavigationManager);
    }

    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_main;
    }

    public void onSelected() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        MenuItem menuItem = (MenuItem) navigationView.getMenu().findItem(getNavigationDrawerMenuItemId());
        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
    }

    @Override
    public void onBackPressed() {
        Log.d(LOG_TAG, "onBackPressed");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final MainActivity mainActivity = this;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(mNavigationManager.getCurrentSelectedId() == getNavigationDrawerMenuItemId()){
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Fechando o aplicativo")
                        .setMessage("Caso esteja numa corrida, ela será cancelada. Você tem certeza que deseja sair?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }

                        })
                        .setNegativeButton("Não", null)
                        .show();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}
