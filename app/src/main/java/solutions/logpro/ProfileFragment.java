package solutions.logpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends SecondaryWindowFragment {

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        setIntentOnClickChangeProfileDataTelNumber();
    }

    private void setIntentOnClickChangeProfileDataTelNumber() {
        final TextView changeProfileDataTelNumber = (TextView) getView().findViewById(R.id.change_profile_data_number);
        final String telNumber = formatTelNumber(changeProfileDataTelNumber.getText());
        changeProfileDataTelNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + telNumber));
                startActivity(intent);
                changeProfileDataTelNumber.setTextColor(getResources().getColor(R.color.purple));
            }
        });
    }

    private String formatTelNumber(CharSequence text) {
        String tel = "";
        for (int i = 0; i < text.length(); i++){
            Character c = text.charAt(i);
            if( Character.isDigit(c) )
                tel += c;
        }
        return tel;
    }

    @Override
    public int getNavigationDrawerMenuItemId() {
        return R.id.nav_profile;
    }
}