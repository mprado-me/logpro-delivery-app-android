package solutions.logpro;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

/**
 * Created by MarcoAurelio on 02/02/2016.
 */
public abstract class SecondaryWindowFragment extends Fragment {

    private final String LOG_TAG = this.getClass().getName();

    private static MainActivity mMainActivity = null;

    public abstract int getNavigationDrawerMenuItemId();

    public static void setMainActivity(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void onStart() {
        super.onStart();
        NavigationView navigationView = (NavigationView) mMainActivity.findViewById(R.id.nav_view);
        MenuItem menuItem = (MenuItem) navigationView.getMenu().findItem(getNavigationDrawerMenuItemId());
        menuItem.setChecked(true);
        mMainActivity.setTitle(menuItem.getTitle());
    }
}
