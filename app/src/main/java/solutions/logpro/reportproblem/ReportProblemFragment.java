package solutions.logpro.reportproblem;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import solutions.logpro.R;
import solutions.logpro.SecondaryWindowFragment;
import solutions.logpro.utils.Consts;

public class ReportProblemFragment extends SecondaryWindowFragment {

    private FragmentTabHost mTabHost;

    private final String MESSAGE_SPEC = "MESSAGE_SPEC";
    private final String PHONE_SPEC = "PHONE_SPEC";
    private final String CHAT_SPEC = "IN_CHAT";
    private final String LOG_TAG = this.getClass().getName()+ Consts.GENERAL_LOG_TAG;

    public ReportProblemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
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
        mTabHost.addTab(
                mTabHost.newTabSpec(CHAT_SPEC).setIndicator("Chat"),
                ChatFragment.class, null);

        return rootView;
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_problem;
    }
}