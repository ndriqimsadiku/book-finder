package com.bmn.bookfinder.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.bmn.bookfinder.helpers.Constants.SharedPrefs.SELECTED_TOPICS;
import static com.bmn.bookfinder.helpers.Constants.SharedPrefs.SELECTED_TOPICS_VALUE;

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

    public static void storeFavoriteIds(Context context, ArrayList<Integer> ids) {
        SharedPreferences preferences = context.getSharedPreferences(
                SELECTED_TOPICS,
                Context.MODE_PRIVATE
        );

        Gson gson = new Gson();
        String json = gson.toJson(ids);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_TOPICS_VALUE, json);
        editor.apply();
    }

    public static ArrayList<Integer> getFavoriteIds(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                SELECTED_TOPICS,
                Context.MODE_PRIVATE
        );
        Gson gson = new Gson();
        String json = preferences.getString(SELECTED_TOPICS_VALUE, "");

        Type type = new TypeToken<List<Integer>>() {
        }.getType();

        ArrayList<Integer> items = new ArrayList<>();

        if (!json.isEmpty()) {
            items = gson.fromJson(
                    json,
                    type
            );
        }
        return items;
    }

    public static void addFavoriteId(Context context, Integer id) {
        ArrayList<Integer> ids = getFavoriteIds(context);
        ids.add(id);
        storeFavoriteIds(context, ids);
    }

    public static void removeFavoriteId(Context context, Integer id) {
        ArrayList<Integer> ids = getFavoriteIds(context);
        if (!ids.isEmpty() && ids.contains(id)) {
            ids.remove(id);
            storeFavoriteIds(context, ids);
        }
    }
}