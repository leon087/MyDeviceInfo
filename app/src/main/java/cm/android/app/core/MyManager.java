package cm.android.app.core;

import android.content.SharedPreferences;

public class MyManager {

    public static MainApp getApp() {
        return MainApp.getApp();
    }

    public static SharedPreferences getAppPreference() {
        return MyPreferences.getInstance()
                .getPreference(MyManager.getApp(), MyManager.getApp().getPackageName());
    }
}
