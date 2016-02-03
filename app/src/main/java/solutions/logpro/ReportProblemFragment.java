package solutions.logpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReportProblemFragment extends SecondaryWindowFragment {

    public ReportProblemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.report_problem_fragment, container, false);
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_problem;
    }
}