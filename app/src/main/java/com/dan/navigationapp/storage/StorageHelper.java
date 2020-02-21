package com.dan.navigationapp.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class StorageHelper {

    private static final String MENU_POSITION_KEY = "menu_position";
    private Activity activity;

    public StorageHelper(Activity activity){
        this.activity = activity;

    }

    public void saveLastMenuTitle(String menuTitle){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(MENU_POSITION_KEY, menuTitle);
        editor.commit();
    }
    public String getLastMenuTitle(){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String menuTitle = sharedPref.getString(MENU_POSITION_KEY,null);

        return menuTitle;
    }



}
