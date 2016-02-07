package solutions.logpro.extract;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import solutions.logpro.R;
import solutions.logpro.SecondaryWindowFragment;
import solutions.logpro.utils.Utils;

public class ExtractFragment extends SecondaryWindowFragment {

    private final static String LOG_TAG = ExtractFragment.class.getName()+ Utils.GENERAL_LOG_TAG;

    private FragmentStatePagerAdapter mAdapterViewPage;

    public static class MonthExtractPageAdapter extends FragmentStatePagerAdapter {

        public MonthExtractPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Date current = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(current);
            cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - position));
            Log.d(LOG_TAG, "getItem, position = " + position);
            return MonthExtractContentFragment.newInstance(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Date current = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(current);
            cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - position));
            String month = new DateFormatSymbols().getMonths()[cal.get(Calendar.MONTH)];
            month = Character.toUpperCase(month.charAt(0))+month.substring(1);
            String year = String.valueOf(cal.get(Calendar.YEAR));
            return month + " de " + year;
        }
    }

    public ExtractFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.extract_fragment, container, false);
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.vpPager);
        mAdapterViewPage = new MonthExtractPageAdapter(getChildFragmentManager()); // Pode ser o getChildFragmentManager()
        viewPager.setAdapter(mAdapterViewPage);
        return root;
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_extract;
    }
}