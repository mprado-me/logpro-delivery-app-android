package solutions.logpro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExtractFragment extends SecondaryWindowFragment {

    public ExtractFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.extract_fragment, container, false);
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_extract;
    }
}