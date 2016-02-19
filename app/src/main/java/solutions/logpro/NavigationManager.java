package solutions.logpro;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;

import solutions.logpro.extract.ExtractFragment;
import solutions.logpro.reportproblem.ReportProblemFragment;
import solutions.logpro.utils.Consts;

/**
 * Created by MarcoAurelio on 31/01/2016.
 */
public class NavigationManager implements NavigationView.OnNavigationItemSelectedListener {

    private final String LOG_TAG = this.getClass().getName() + Consts.GENERAL_LOG_TAG;

    private MainActivity mMainActivity;
    private int mCurrentSelectedId;
    // Na função onNotPrincipalItemSelected, quando é um janela secundária que está ativa, ao
    // retirarmos o fragmento atual, mMainActivity.getSupportFragmentManager().popBackStack(), a
    // função onBackStackChanged() será chamada com a stack do FragmentManager vazia, isso acarreta
    // na alteração de mCurrentSelectedId, mCurrentSelectedId = mMainActivity.getNavigationDrawerMenuItemId(),
    // o que gera um bug ao ficar alterando entre dois SecondaryWindowFragment múltiplas vezes.
    // Para evitar esse bug, a variável mTempStackChange é utilizada.
    // TODO: Encontrar um forma de não precisar mais usar esta variável
    private boolean mTempStackChange = false;

    public NavigationManager(MainActivity mainActivity) {
        Log.d(LOG_TAG, "NavigationManager constructor called");
        mMainActivity = mainActivity;
        mCurrentSelectedId = mainActivity.getNavigationDrawerMenuItemId();
        initOnFragmentManagerBackStackChangedListenner();
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
            onNotPrincipalItemSelected(getFragmentById(id));
        }

        return closeDrawer();
    }

    // TODO: Encontrar uma forma mais elegente de implementar esse método
    private SecondaryWindowFragment getFragmentById(int id) {
        switch (id){
            case R.id.nav_profile:
                return new ProfileFragment();
            case R.id.nav_extract:
                return new ExtractFragment();
            case R.id.nav_problem:
                return new ReportProblemFragment();
            default:
                return null;
        }
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

    public int getCurrentSelectedId(){
        return mCurrentSelectedId;
    }
}