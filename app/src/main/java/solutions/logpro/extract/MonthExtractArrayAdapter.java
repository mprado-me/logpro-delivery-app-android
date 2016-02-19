package solutions.logpro.extract;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import solutions.logpro.R;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 06/02/2016.
 */
public class MonthExtractArrayAdapter extends ArrayAdapter<String> {

    private String mTotal;

    private final static String LOG_TAG = MonthExtractArrayAdapter.class.getName() + Consts.GENERAL_LOG_TAG;

    public MonthExtractArrayAdapter(Context context, String[] objects, String total) {
        super(context, 0, objects);
        mTotal = total;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (position == 0) {
            String data = mTotal;

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.month_extract_total, parent, false);
            convertView.setTag("month_extract_total");

            TextView tvTotalMoney = (TextView) convertView.findViewById(R.id.total_money_text_view);
            TextView tvTotalMileage = (TextView) convertView.findViewById(R.id.total_mileage_text_view);
            TextView tvTotalHours = (TextView) convertView.findViewById(R.id.total_hours_text_view);

            Log.d(LOG_TAG, "data:");
            Log.d(LOG_TAG, data);

            String[] fields = data.split(";");

            Log.d(LOG_TAG, "Fields:");
            Log.d(LOG_TAG, fields[0]);
            Log.d(LOG_TAG, fields[1]);
            Log.d(LOG_TAG, fields[2]);

            tvTotalMoney.setText(fields[0]);
            tvTotalMileage.setText(fields[1]);
            tvTotalHours.setText(fields[2]);

            return convertView;
        } else {
            String data = getItem(position-1);

            if (convertView == null || convertView.getTag() == "month_extract_total" ) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.month_extract_item, parent, false);
            }
            TextView tvEstablishment = (TextView) convertView.findViewById(R.id.establishment_text_view);
            TextView tvRaceData = (TextView) convertView.findViewById(R.id.race_data_text_view);
            TextView tvEarnedMoney = (TextView) convertView.findViewById(R.id.earned_money_text_view);

            Log.d(LOG_TAG, "data:");
            Log.d(LOG_TAG, data);

            String[] fields = data.split(";");

            Log.d(LOG_TAG, "Fields:");
            Log.d(LOG_TAG, fields[0]);
            Log.d(LOG_TAG, fields[1]);
            Log.d(LOG_TAG, fields[2]);

            tvEstablishment.setText(fields[0]);
            tvRaceData.setText(fields[1]);
            tvEarnedMoney.setText(fields[2]);

            return convertView;
        }


    }
}
