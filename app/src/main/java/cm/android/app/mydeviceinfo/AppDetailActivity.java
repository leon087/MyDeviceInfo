package cm.android.app.mydeviceinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.widget.TextView;

import cm.android.app.global.Tag;
import cm.android.applications.AppUtil;
import cm.android.util.UIUtils;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class AppDetailActivity extends Activity {

    private static final Logger logger = LoggerFactory.getLogger(Tag.LOG_APPS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_app_detail_tmp);

        Bundle bundle = getIntent().getExtras();
        String packageName = bundle.getString(Tag.PACKAGE_NAME);

        PackageInfo packageInfo = AppUtil.getPackageInfo(this.getPackageManager(), packageName);
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;

        logger.error("ggg paackageInfo = " + packageInfo);

//        packageInfo.versionCode

        StringBuilder sb = new StringBuilder();

        sb.append(":" + packageInfo.packageName + "\n");
        sb.append(":" + packageInfo.versionName + "\n");
        sb.append(":" + packageInfo.versionCode + "\n");
        sb.append(":" + packageInfo.sharedUserId + "\n");
        sb.append(":" + packageInfo.sharedUserLabel + "\n");
        sb.append(":" + packageInfo.firstInstallTime + "\n");
        sb.append(":" + packageInfo.lastUpdateTime + "\n");

        sb.append(":" + applicationInfo.loadLabel(getPackageManager()) + "\n");
        sb.append(":" + applicationInfo.flags + "\n");
        sb.append(":" + applicationInfo.requiresSmallestWidthDp + "\n");
        sb.append(":" + applicationInfo.compatibleWidthLimitDp + "\n");
        sb.append(":" + applicationInfo.largestWidthLimitDp + "\n");
        sb.append(":" + applicationInfo.sourceDir + "\n");
        sb.append(":" + applicationInfo.publicSourceDir + "\n");
        sb.append(":" + applicationInfo.sharedLibraryFiles + "\n");
        sb.append(":" + applicationInfo.dataDir + "\n");
        sb.append(":" + applicationInfo.nativeLibraryDir + "\n");
        sb.append(":" + applicationInfo.uid + "\n");
        sb.append(":" + applicationInfo.targetSdkVersion + "\n");
        sb.append(":" + applicationInfo.enabled + "\n");
        sb.append(":" + applicationInfo.taskAffinity + "\n");
        sb.append(":" + applicationInfo.permission + "\n");
        sb.append(":" + applicationInfo.processName + "\n");
        sb.append(":" + applicationInfo.className + "\n");
        sb.append(":" + applicationInfo.descriptionRes + "\n");
        sb.append(":" + applicationInfo.theme + "\n");
        sb.append(":" + applicationInfo.manageSpaceActivityName + "\n");
        sb.append(":" + applicationInfo.backupAgentName + "\n");
        sb.append(":" + applicationInfo.uiOptions + "\n");

        sb.append("\n");

        sb.append("activities:========================\n");
        ActivityInfo[] activities = packageInfo.activities;
        if (activities != null && activities.length > 0) {
            for (ActivityInfo activityInfo : activities) {
                sb.append(":" + activityInfo.name + "\n");
//                sb.append(":" + activityInfo.parentActivityName + "\n");
//                sb.append(":" + activityInfo.permission + "\n");
//                sb.append(":" + activityInfo.targetActivity + "\n");
                sb.append(":" + activityInfo.taskAffinity + "\n");
                sb.append(":" + activityInfo.flags + "\n");
                sb.append(":" + activityInfo.launchMode + "\n");
                sb.append(":" + activityInfo.configChanges + "\n");
            }
        }

        sb.append("gids:========================\n");
        int[] gids = packageInfo.gids;
        if (gids != null && gids.length > 0) {
            for (int gid : gids) {
                sb.append(":" + gid + "\n");
            }
        }

        sb.append("permissionInfos:========================\n");
        PermissionInfo[] permissionInfos = packageInfo.permissions;
        if (permissionInfos != null && permissionInfos.length > 0) {
            for (PermissionInfo info : permissionInfos) {
                sb.append(":" + info.name + "\n");
                sb.append(":" + info.group + "\n");
                sb.append(":" + info.loadDescription(getPackageManager()) + "\n");
                sb.append(":" + info.nonLocalizedDescription + "\n");
                sb.append(":" + info.protectionLevel + "\n");
            }
        }

        sb.append("providerInfos:========================\n");
        ProviderInfo[] providerInfos = packageInfo.providers;
        if (providerInfos != null && providerInfos.length > 0) {
            for (ProviderInfo info : providerInfos) {
                sb.append(":" + info.name + "\n");
                sb.append(":" + info.authority + "\n");
                sb.append(":" + info.readPermission + "\n");
                sb.append(":" + info.writePermission + "\n");
                sb.append(":" + info.grantUriPermissions + "\n");
                sb.append(":" + info.multiprocess + "\n");
                sb.append(":" + info.pathPermissions + "\n");
                sb.append(":" + info.uriPermissionPatterns + "\n");
                sb.append(":" + info.grantUriPermissions + "\n");
                sb.append(":" + info.exported + "\n");
                sb.append(":" + info.enabled + "\n");
                sb.append(":" + info.initOrder + "\n");
            }
        }

        sb.append("receivers:========================\n");
        ActivityInfo[] receivers = packageInfo.receivers;
        if (receivers != null && receivers.length > 0) {
            for (ActivityInfo activityInfo : receivers) {
                sb.append(":" + activityInfo.name + "\n");
                sb.append(":" + activityInfo.parentActivityName + "\n");
                sb.append(":" + activityInfo.permission + "\n");
                sb.append(":" + activityInfo.targetActivity + "\n");
                sb.append(":" + activityInfo.taskAffinity + "\n");
                sb.append(":" + activityInfo.flags + "\n");
                sb.append(":" + activityInfo.launchMode + "\n");
                sb.append(":" + activityInfo.configChanges + "\n");
            }
        }

        sb.append("reqFeatures:========================\n");
        FeatureInfo[] featureInfos = packageInfo.reqFeatures;
        if (featureInfos != null && featureInfos.length > 0) {
            for (FeatureInfo info : featureInfos) {
                sb.append(":" + info.name + "\n");
                sb.append(":" + info.getGlEsVersion() + "\n");
                sb.append(":" + info.reqGlEsVersion + "\n");
            }
        }

        sb.append("requestedPermissions:========================\n");
        String[] requestedPermissions = packageInfo.requestedPermissions;
        if (requestedPermissions != null && requestedPermissions.length > 0) {
            for (String info : requestedPermissions) {
                sb.append(":" + info + "\n");
            }
        }

        sb.append("services:========================\n");
        ServiceInfo[] serviceInfos = packageInfo.services;
        if (serviceInfos != null && serviceInfos.length > 0) {
            for (ServiceInfo info : serviceInfos) {
                sb.append(":" + info.name + "\n");
                sb.append(":" + info.enabled + "\n");
                sb.append(":" + info.exported + "\n");
                sb.append(":" + info.permission + "\n");
                sb.append(":" + info.flags + "\n");
            }
        }

        sb.append("configPreferences:========================\n");
        ConfigurationInfo[] configPreferences = packageInfo.configPreferences;
        if (configPreferences != null && configPreferences.length > 0) {
            for (ConfigurationInfo info : configPreferences) {
                sb.append(":" + info.getGlEsVersion() + "\n");
                sb.append(":" + info.reqGlEsVersion + "\n");
                sb.append(":" + info.reqInputFeatures + "\n");
                sb.append(":" + info.reqKeyboardType + "\n");
                sb.append(":" + info.reqNavigation + "\n");
                sb.append(":" + info.reqTouchScreen + "\n");
            }
        }

        sb.append("sharedLibraryFiles:========================\n");
        String[] sharedLibraryFiles = applicationInfo.sharedLibraryFiles;
        if (sharedLibraryFiles != null && sharedLibraryFiles.length > 0) {
            for (String info : sharedLibraryFiles) {
                sb.append(":" + info + "\n");
            }
        }

        String txt = sb.toString();

        TextView textView = UIUtils.findView(this, R.id.app_info);
        textView.setText(txt);
    }

}
