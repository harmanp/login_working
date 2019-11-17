package ca.georgebrown.comp3074.login_working;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    private EditText Name, UserName, UserPassword;
    private Button  BtnRegister;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        Name = view.findViewById(R.id.txt_name);
        UserName = view.findViewById(R.id.txt_username);
        UserPassword = view.findViewById(R.id.txt_password);
        BtnRegister = view.findViewById(R.id.btn_register);
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });

        return  view;
    }

    private void performRegistration(){
        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // using display toast method
                // checking the response from register.php

                if(response.body().getResponse().equals("ok")){
                    MainActivity.prefConfig.displayToast("Registration success ....");
                }
                else if(response.body().getResponse().equals("exist")){
                    MainActivity.prefConfig.displayToast("User already exist ....");
                }
                else if(response.body().getResponse().equals("error")){
                    MainActivity.prefConfig.displayToast("something went wrong....");
                }
//                else {
//                    MainActivity.prefConfig.displayToast("something hvdshvj went wrong....");
//                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");

    }

}
