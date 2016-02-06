package solutions.logpro.reportproblem;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import solutions.logpro.R;
import solutions.logpro.utils.Utils;

/**
 * Created by MarcoAurelio on 04/02/2016.
 */
// TODO: Encontrar uma forma de lançar a intent da ligação apenas quando eu clico na aba telefone e não sempre que esse fragment se torna vísivel (onStart())
public class ReportProblemByPhoneFragment extends ReportProblemTabContentFragment {

    private String mIntentPhoneNumber;

    public ReportProblemByPhoneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.report_problem_by_phone_fragment, container, false);
    }

    private String getIntentPhoneNumber() {
        TextView phoneNumber = (TextView) getView().findViewById(R.id.report_problem_by_phone_text_view);
        return Utils.formatTelNumber(phoneNumber.getText());
    }

    @Override
    public void onStart() {
        super.onStart();
        mIntentPhoneNumber = getIntentPhoneNumber();
        setIntentOnClickPhoneNumber();
        launchCallIntent();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setIntentOnClickPhoneNumber() {
        final TextView phoneNumber = (TextView) getView().findViewById(R.id.report_problem_by_phone_text_view);
        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCallIntent();
                phoneNumber.setTextColor(getResources().getColor(R.color.purple));
            }
        });
    }

    private void launchCallIntent() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + mIntentPhoneNumber));
        startActivity(intent);
    }
}
