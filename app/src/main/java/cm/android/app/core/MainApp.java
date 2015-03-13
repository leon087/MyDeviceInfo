package cm.android.app.core;

import android.app.Application;

import ch.qos.logback.classic.Level;
import cm.android.log.LogConfig;
import cm.android.util.AndroidUtils;
import cm.android.util.BuildConfigUtil;
import cm.android.util.EnvironmentUtil;
import cm.android.util.ExitHolder;

public class MainApp extends Application {

    public static final String ACTION_EXIT = "cm.android.intent.ACTION_EXIT";

    private static MainApp sMainApp;

    public static MainApp getApp() {
        return sMainApp;
    }

    public static boolean isDebug() {
        if (sMainApp == null) {
            return false;
        }
        return BuildConfigUtil.isDebug(sMainApp);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sMainApp = this;
        LogConfig.configLogback(Level.ALL, EnvironmentUtil.getDiskCacheDir(this, "log"));
    }

    public void exit() {
        ExitHolder.exit(this);
    }

    public void exitProcess() {
        exit();
        AndroidUtils.killProcess(this);
    }
}