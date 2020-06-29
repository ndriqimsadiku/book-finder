package com.bmn.bookfinder.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefUtils {
    private static SharedPreferences sharedPreferences;

    public static void storeFirstTimeUsedTimestamp(Context context, long timestamp) {
        sharedPreferences = context.getSharedPreferences(Constants.SharedPrefs.FIRST_TIME_USED, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(Constants.SharedPrefs.FIRST_TIME_USED_VALUE, timestamp);
        editor.apply();
    }

    public static long loadFirstTimeUsedTimestamp(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.SharedPrefs.FIRST_TIME_USED, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(Constants.SharedPrefs.FIRST_TIME_USED_VALUE, 0L);
    }
}
