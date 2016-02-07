package solutions.logpro.extract;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import solutions.logpro.R;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 06/02/2016.
 */
public class MonthExtractContentFragment extends Fragment {
    private ListView mListView;

    private int mMonth;
    private int mYear;

    private static final String LOG_TAG = MonthExtractContentFragment.class.getName() + Utils.GENERAL_LOG_TAG;
    private static final String MONTH_KEY = MonthExtractContentFragment.class.getName() + "MONTH_KEY";
    private static final String YEAR_KEY = MonthExtractContentFragment.class.getName() + "YEAR_KEY";

    public static MonthExtractContentFragment newInstance(int month, int year) {
        Log.d(LOG_TAG, "MonthExtractContentFragment.newIntance(" + month + ", " + year + ")");
        MonthExtractContentFragment frag = new MonthExtractContentFragment();
        Bundle args = new Bundle();
        args.putInt(MONTH_KEY, month);
        args.putInt(YEAR_KEY, year);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMonth = getArguments().getInt(MONTH_KEY);
        mYear = getArguments().getInt(YEAR_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.month_extract_content, container, false);
        mListView = (ListView) root.findViewById(R.id.month_extract_list_view);
        String[] data = new String[]{
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
                "Domino’s Pizza;Dia 27 –4,7 km –17 min;R$ 8,70",
        };
        MonthExtractArrayAdapter adapter = new MonthExtractArrayAdapter(getContext(), data, "RS 1240,50;982 km;126 h");
        mListView.setAdapter(adapter);
        return root;
    }
}
