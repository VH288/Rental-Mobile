package com.vincenthandoko.rental.Preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public static final String IS_LOGIN = "isLogin";
    public static final String KEY_USERID = "userId";
    public static final String KEY_ROLE = "role";

    public UserPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userPreferences",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLogin(String userId, String role){
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(KEY_USERID,userId);
        editor.putString(KEY_ROLE,role);
        editor.commit();
    }
    public String getUserId(){
        String userId;
        userId = sharedPreferences.getString(KEY_USERID,null);
        return userId;
    }
    public String getRole(){
        String role;
        role = sharedPreferences.getString(KEY_ROLE,null);
        return role;
    }

    public boolean checkLogin(){
        return sharedPreferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }
}
