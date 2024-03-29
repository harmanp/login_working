package ca.georgebrown.comp3074.login_working;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView RegText;
    private EditText UserName, UserPassword;
    private Button LoginBn;
    OnLoginFormActivityListener loginFormActivityListener;
    public  interface  OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String name);
    }
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        UserName = view.findViewById(R.id.user_name);
        UserPassword = view.findViewById(R.id.user_pass);
        LoginBn = view.findViewById(R.id.btnlogin);

        LoginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
        RegText = view.findViewById(R.id.txt_user_register);
        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFormActivityListener.performRegister();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok")){
//if login is success then the login is stored in the perform login main activity
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());
                }
                else if(response.body().getResponse().equals("failed")){
                    MainActivity.prefConfig.displayToast("login Failed.. please try again..");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        UserName.setText("");
        UserPassword.setText("");
    }

}
