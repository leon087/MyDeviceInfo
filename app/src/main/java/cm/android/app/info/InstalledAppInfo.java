package cm.android.app.info;

public class InstalledAppInfo {

    public String packageName;

    public int versionCode;

    public String versionName;

    public String appName;

    public long firstInstallTime;

    public long lastUpdateTime;

    public String sharedUserId;

    public long sharedUserLabel;

    public String signature;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "InstalledAppInfo{" +
                "packageName='" + packageName + '\'' +
                ", versionCode=" + versionCode +
                ", versionName='" + versionName + '\'' +
                ", appName='" + appName + '\'' +
                ", firstInstallTime=" + firstInstallTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", sharedUserId='" + sharedUserId + '\'' +
                ", sharedUserLabel=" + sharedUserLabel +
                ", signature='" + signature + '\'' +
                '}';
    }
}