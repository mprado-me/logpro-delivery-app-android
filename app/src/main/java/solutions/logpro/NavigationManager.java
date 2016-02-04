package solutions.logpro;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by MarcoAurelio on 31/01/2016.
 */
public class NavigationManager implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = this.getClass().getName() + MainActivity.GENERAL_LOG_TAG;
    private final String CURRENT_SELECTED_ID_KEY = "CURRENT_SELECTED_ID_KEY";

    private MainActivity mMainActivity;
    private HashMap<Integer, SecondaryWindowFragment> mFragmentsById;
    private int mCurrentSelectedId;
    // Na função onNotPrincipalItemSelected, quando é um janela secundária que está ativa, ao
    // retirarmos o fragmento atual, mMainActivity.getSupportFragmentManager().popBackStack(), a
    // função onBackStackChanged() será chamada com a stack do FragmentManager vazia, isso acarreta
    // na alteração de mCurrentSelectedId, mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId(),
    // o que gera um bug ao ficar alterando entre dois SecondaryWindowFragment múltiplas vezes.
    // Para evitar esse bug, a variável mTempStackChange é utilizada.
    // TODO: Encontrar um forma de não precisar mais usar esta variável
    private boolean mTempStackChange = false;

    public NavigationManager(MainActivity mainActivity,
                             HashMap<Integer, SecondaryWindowFragment> fragmentsById,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "NavigationManager constructor called");
        mFragmentsById = fragmentsById;
        mMainActivity = mainActivity;
        initOnFragmentManagerBackStackChangedListenner();
        if (savedInstanceState == null) {
            Log.d(LOG_TAG, "savedInstanceState == null");
            mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId();
        } else {
            Log.d(LOG_TAG, "savedInstanceState != null");
            mCurrentSelectedId = savedInstanceState.getInt(this.getClass().getName() + CURRENT_SELECTED_ID_KEY);
            Log.d(LOG_TAG, "mCurrentSelectedId: " + mMainActivity.getResources().getResourceName(mCurrentSelectedId));
            if (mCurrentSelectedId != mMainActivity.getNavigationDrawerMenuItemId()) {
                onNotPrincipalItemSelected(mFragmentsById.get(mCurrentSelectedId));
            }
        }
    }

    private void initOnFragmentManagerBackStackChangedListenner() {
        mMainActivity.getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (mTempStackChange) {
                            mTempStackChange = false;
                            return;
                        }
                        if (mMainActivity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                            mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId();
                            mMainActivity.onSelected();
                        }
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(LOG_TAG, item.getTitle() + " selected");

        int id = item.getItemId();

        if (id == mCurrentSelectedId) {
            return closeDrawer();
        }
        // A partir daqui, certamente o conteúdo exibido na janela principal será alterado

        if (id == mMainActivity.getNavigationDrawerMenuItemId()) {
            mMainActivity.getSupportFragmentManager().popBackStack();
        } else {
            onNotPrincipalItemSelected(mFragmentsById.get(id));
        }

        return closeDrawer();
    }

    private boolean closeDrawer() {
        DrawerLayout drawer = (DrawerLayout) mMainActivity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onNotPrincipalItemSelected(SecondaryWindowFragment notPrincipalSelectedFragment) {
        if (mCurrentSelectedId != mMainActivity.getNavigationDrawerMenuItemId()) {
            mTempStackChange = true;
            mMainActivity.getSupportFragmentManager().popBackStack();
        }

        mMainActivity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, notPrincipalSelectedFragment)
                .addToBackStack(null)
                .commit();

        mCurrentSelectedId = notPrincipalSelectedFragment.getNavigationDrawerMenuItemId();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(this.getClass().getName() + CURRENT_SELECTED_ID_KEY, mCurrentSelectedId);
    }
}