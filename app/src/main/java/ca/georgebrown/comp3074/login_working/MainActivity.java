package ca.georgebrown.comp3074.login_working;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnLoginFormActivityListener,
        WelcomeFragment.OnLogoutListener
{

    //for login
    public static PrefConfig prefConfig;
    //for registration
    public  static ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if(findViewById(R.id.fragmentContainer)!=null){
            if(savedInstanceState!=null) {
                return;
            }
            if(prefConfig.readLoginStatus()) {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new WelcomeFragment()).commit();
            }
            else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new LoginFragment()).commit();
            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WelcomeFragment()).commit();

    }

    @Override
    public void logoutPerformed() {
        //resetting the shared prefrences
        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new LoginFragment()).commit();
    }
}
