package solutions.logpro;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getName();

    NavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationManager = new NavigationManager(this);
        if(savedInstanceState == null){
            InitSecondaryWindowFragments();
        }
        InitNavigationDrawerMenu();
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

    public int getNavigationDrawerMenuItemId(){
        return R.id.nav_main;
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
