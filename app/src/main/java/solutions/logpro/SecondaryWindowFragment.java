package solutions.logpro;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;

import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 02/02/2016.
 */
public abstract class SecondaryWindowFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getName() + Utils.GENERAL_LOG_TAG;

    private static MainActivity mMainActivity = null;

    public abstract int getNavigationDrawerMenuItemId();

    public static void setMainActivity(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState: " + savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
        NavigationView navigationView = (NavigationView) mMainActivity.findViewById(R.id.nav_view);
        MenuItem menuItem = navigationView.getMenu().findItem(getNavigationDrawerMenuItemId());
        menuItem.setChecked(true);
        mMainActivity.setTitle(menuItem.getTitle());
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public static void destroyMainActivityReference() {
        mMainActivity = null;
    }
}
