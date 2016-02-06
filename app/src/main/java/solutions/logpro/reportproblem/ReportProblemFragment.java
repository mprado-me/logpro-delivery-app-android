package solutions.logpro.reportproblem;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solutions.logpro.R;
import solutions.logpro.SecondaryWindowFragment;
import solutions.logpro.utils.Utils;

public class ReportProblemFragment extends SecondaryWindowFragment {

    private FragmentTabHost mTabHost;
    private String mInitialSelectedTabTag;

    private final String MESSAGE_SPEC = "MESSAGE_SPEC";
    private final String PHONE_SPEC = "PHONE_SPEC";
    private final String LOG_TAG = this.getClass().getName()+ Utils.GENERAL_LOG_TAG;
    private final String CURRENT_TAB_TAG_KEY = "CURRENT_TAB_TAG_KEY";

    public ReportProblemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "savedInstanceState = " + savedInstanceState);
        if( savedInstanceState != null && savedInstanceState.containsKey(this.getClass().getName()+CURRENT_TAB_TAG_KEY) ){
            mInitialSelectedTabTag = savedInstanceState.getString(this.getClass().getName()+CURRENT_TAB_TAG_KEY);
        }
        else{
            mInitialSelectedTabTag = MESSAGE_SPEC;
        }
        Log.d(LOG_TAG, "mInitialSelectedTabTag = " + mInitialSelectedTabTag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.report_problem_fragment, container, false);

        mTabHost = (FragmentTabHost) rootView.findViewById(R.id.report_problem_tab_host);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.report_problem_content);

        mTabHost.addTab(
                mTabHost.newTabSpec(MESSAGE_SPEC).setIndicator(getResources().getString(R.string.report_problem_message_tab_title)),
                ReportProblemByMessageFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec(PHONE_SPEC).setIndicator(getResources().getString(R.string.report_problem_phone_tab_title)),
                ReportProblemByPhoneFragment.class, null);

        return rootView;
    }

    @Override
    public void onPause(){
        super.onPause();
        mInitialSelectedTabTag = mTabHost.getCurrentTabTag();
    }

    @Override
    public void onResume(){
        super.onResume();
        mTabHost.setCurrentTabByTag(mInitialSelectedTabTag);
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_problem;
    }

    //TODO: Descobrir porque não é chamado automaticamente
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(LOG_TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString(this.getClass().getName() + CURRENT_TAB_TAG_KEY, mTabHost.getCurrentTabTag());
        Log.d(LOG_TAG, "outState(after insert CURRENT_TAB_TAG_KEY) = " + outState);
    }
}