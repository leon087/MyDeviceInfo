package cm.android.app.info;

import android.content.pm.PackageInfo;

import java.util.List;

import cm.android.app.core.MyManager;
import cm.android.applications.AppUtil;
import cm.java.util.ObjectUtil;

public class InstalledAppInfoCollector implements Runnable {

    @Override
    public void run() {

    }

    public List<InstalledAppInfo> collect() {
        List<InstalledAppInfo> apps = ObjectUtil.newArrayList();

        List<PackageInfo> packageInfoList = AppUtil
                .getInstalledPackages(MyManager.getApp().getPackageManager());
        for (PackageInfo packageInfo : packageInfoList) {
            InstalledAppInfo app = new InstalledAppInfo();
            app.appName = packageInfo.applicationInfo
                    .loadLabel(MyManager.getApp().getPackageManager())
                    .toString();
            app.packageName = packageInfo.packageName;
            app.versionCode = packageInfo.versionCode;
            app.versionName = packageInfo.versionName;
            app.firstInstallTime = packageInfo.firstInstallTime;
            app.lastUpdateTime = packageInfo.lastUpdateTime;
            app.sharedUserId = packageInfo.sharedUserId;
            app.sharedUserLabel = packageInfo.sharedUserLabel;

            apps.add(app);
        }

        return apps;
    }
}
