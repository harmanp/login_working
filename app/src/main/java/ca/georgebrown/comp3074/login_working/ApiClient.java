package ca.georgebrown.comp3074.login_working;

import android.accounts.AuthenticatorException;
import android.app.AuthenticationRequiredException;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.lang.reflect.Proxy;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.nio.charset.Charset;




public class ApiClient {

    public static final String BASE_URL = "https://f9team1.gblearn.com/loginapp/";
    public static Retrofit retrofit = null;

    public static Retrofit getApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                       Credentials.basic("f9team1", "Stushare127", Charset.forName("UTF8")));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();


        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;

    }

}
