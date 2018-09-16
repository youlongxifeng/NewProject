package com.company.project.android.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.preferences
 * @class describe
 * @time 2018/4/11 11:19
 * @change
 * @class describe
 */

public class SharedPrefercesUtils {
    public static final String ISLOGINED = "islogined";
    public static final String COOKIE = "cookie";

    public static void saveCookiePreference(Context context, String value) {
        SharedPreferences preference = context.getSharedPreferences(ISLOGINED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(COOKIE, value);
        editor.apply();
    }
    public static String getCookiePreference(Context context) {
        SharedPreferences preference = context.getSharedPreferences(ISLOGINED, Context.MODE_PRIVATE);
        String s = preference.getString(COOKIE, "");
        return s;
    }
}
