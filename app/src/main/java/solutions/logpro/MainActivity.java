package solutions.logpro;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public static final String GENERAL_LOG_TAG = "MPDEBUG";
    private final String LOG_TAG = this.getClass().getName() + GENERAL_LOG_TAG;

    NavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);

        mNavigationManager = new NavigationManager(this);

        InitSecondaryWindowFragments();
        InitNavigationDrawerMenu();
    }

    // Não mudar a ordem de chamada de onSelected() e super.onStart(). onSelected() DEVE vir primeiro
    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "onStart");
        // Como super.onStart chama o onStart dos SecondaryWindowFragments ativos, para que a seleção no
        // menu de navegação e o preenchimento do título na barra superior sejam preenchidos corretamente
        // quando um SecondaryWindowFragment estiver ativos, é necessário primeiro chamar o onStart()
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

    private void InitSecondaryWindowFragments() {
        SecondaryWindowFragment.setMainActivity(this);
        mNavigationManager.addSecondaryWindowFragment(new ProfileFragment());
        mNavigationManager.addSecondaryWindowFragment(new ExtractFragment());
        mNavigationManager.addSecondaryWindowFragment(new ReportProblemFragment());
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
