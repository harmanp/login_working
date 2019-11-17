package ca.georgebrown.comp3074.login_working;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private  String Response;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }

    @SerializedName("name")
    private String Name;
}
