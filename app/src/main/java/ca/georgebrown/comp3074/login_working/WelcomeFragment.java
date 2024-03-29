package ca.georgebrown.comp3074.login_working;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {
    private TextView textview;
    private Button BtnLogout;
    OnLogoutListener onLogoutListener;

    public interface OnLogoutListener{
        public void logoutPerformed();
    }


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        textview = view.findViewById(R.id.txtWelcome);
        textview.setText("Welcome " + MainActivity.prefConfig.readName());
        BtnLogout = view.findViewById(R.id.btn_logout);
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogoutListener.logoutPerformed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onLogoutListener = (OnLogoutListener) activity;
    }
}
