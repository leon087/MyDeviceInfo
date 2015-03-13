package cm.android.app.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import cm.java.util.ObjectUtil;

public class MyPreferences {

    private static MyPreferences instance;

    private static final Map<String, SharedPreferences> preferences = ObjectUtil.newHashMap();

    public static MyPreferences getInstance() {
        if (instance == null) {
            synchronized (MyPreferences.class) {
                if (instance == null) {
                    instance = new MyPreferences();
                }
            }
        }
        return instance;
    }

    public SharedPreferences getPreference(Context context, String tag) {
        SharedPreferences sharedPreferences = preferences.get(tag);
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
            preferences.put(tag, sharedPreferences);
        }

        return sharedPreferences;
    }
}
