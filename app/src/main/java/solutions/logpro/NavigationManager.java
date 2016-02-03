package solutions.logpro;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import java.util.HashSet;

/**
 * Created by MarcoAurelio on 31/01/2016.
 */
public class NavigationManager implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = this.getClass().getName();

    private MainActivity mMainActivity;
    private HashSet<SecondaryWindowFragment> mFragments;
    private int mCurrentSelectedId;
    // Na função onNotPrincipalItemSelected, quando é um janela secundária que está ativa, ao
    // retirarmos o fragmento atual, mMainActivity.getSupportFragmentManager().popBackStack(), a
    // função onBackStackChanged() será chamado com a stack do FragmentManager vazia, isso acarreta
    // na alteração de mCurrentSelectedId, mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId(),
    // o que gera um bug ao ficar alterando entre dois SecondaryWindowFragment múltiplas vezes.
    // Para evitar esse bug, a variável mTempStackChange é utilizada.
    // TODO: Encontrar um forma de não precisar mais usar esta variável
    private boolean mTempStackChange = false;

    public NavigationManager(MainActivity mainActivity) {
        mFragments = new HashSet<SecondaryWindowFragment>();
        mMainActivity = mainActivity;
        initOnFragmentManagerBackStackChangedListenner();
        mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId();
    }

    private void initOnFragmentManagerBackStackChangedListenner() {
        mMainActivity.getSupportFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    public void onBackStackChanged() {
                        if (mTempStackChange) {
                            mTempStackChange = false;
                            return;
                        }
                        if (mMainActivity.getSupportFragmentManager().getBackStackEntryCount() == 0)
                            mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId();
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
            for (SecondaryWindowFragment fragment : mFragments) {
                if (id == fragment.getNavigationDrawerMenuItemId()) {
                    onNotPrincipalItemSelected(fragment);
                }
            }
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

    public void addSecondaryWindowFragment(SecondaryWindowFragment secondaryFragment) {
        mFragments.add(secondaryFragment);
    }
}