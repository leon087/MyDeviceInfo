package cm.android.app.info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Map;

import cm.android.app.core.MyManager;
import cm.android.app.global.Tag;
import cm.android.applications.AppUtil;
import cm.android.util.DeviceUtil;
import cm.android.util.SystemInfoUtil;
import cm.android.util.SystemUtil;
import cm.java.util.IoUtil;
import cm.java.util.MapUtil;
import cm.java.util.ObjectUtil;
import cm.java.util.Utils;

public class InfoCollector implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Tag.LOG_INFO);

    @Override
    public void run() {
    }

    public Info.ApkInfo collectApkInfo() {
        Info.ApkInfo apkInfo = new Info.ApkInfo();
        apkInfo.packageName = MyManager.getApp().getPackageName();
        PackageInfo packageInfo = AppUtil.getPackageInfo(MyManager.getApp()
                .getPackageManager(), MyManager.getApp().getPackageName());
        apkInfo.versionCode = packageInfo.versionCode;
        apkInfo.versionName = packageInfo.versionName;
        apkInfo.lastUpdateTime = packageInfo.lastUpdateTime;
        apkInfo.firstInstallTime = packageInfo.firstInstallTime;
        apkInfo.sharedUserId = packageInfo.sharedUserId;
        apkInfo.sharedUserLabel = packageInfo.sharedUserLabel;
        return apkInfo;
    }

    public Info.BasicInfo collectBasicInfo() {
        Info.BasicInfo basicInfo = new Info.BasicInfo();
        basicInfo.uuid = DeviceUtil.getUUID(MyManager.getApp()).toString();
        basicInfo.imei = DeviceUtil.getIMEI(MyManager.getApp());
        basicInfo.imsi = DeviceUtil.getIMSI(MyManager.getApp());
        basicInfo.kernel_version = SystemInfoUtil.getVersionInfo().trim();
        basicInfo.root = SystemUtil.isRoot();

        basicInfo.board = Build.BOARD;
        basicInfo.bootloader = DeviceUtil.getBootloader();
        basicInfo.brand = Build.BRAND;
        basicInfo.cpu_abi = Build.CPU_ABI;
        basicInfo.cpu_abi2 = DeviceUtil.getCpuAbi2();
        basicInfo.device = Build.DEVICE;
        basicInfo.display = Build.DISPLAY;
        basicInfo.fingerprint = Build.FINGERPRINT;
        basicInfo.hardware = DeviceUtil.getHardware();
        basicInfo.host = Build.HOST;
        basicInfo.id = Build.ID;
        basicInfo.manufacturer = Build.MANUFACTURER;
        basicInfo.model = Build.MODEL;
        basicInfo.product = Build.PRODUCT;
        basicInfo.serial = DeviceUtil.getSerial();
        basicInfo.tags = Build.TAGS;
        basicInfo.type = Build.TYPE;
        basicInfo.time = Build.TIME;
        basicInfo.user = Build.USER;

        basicInfo.net_hostname = DeviceUtil.getHostName();

        basicInfo.version_codename = Build.VERSION.CODENAME;
        basicInfo.version_incremental = Build.VERSION.INCREMENTAL;
        basicInfo.version_release = Build.VERSION.RELEASE;
        basicInfo.version_sdk_int = Build.VERSION.SDK_INT;

        return basicInfo;
    }

    public Info.WifiInfo collectWifiInfo() {
        Info.WifiInfo wifiInfo = new Info.WifiInfo();

        WifiManager wifiService = (WifiManager) MyManager.getApp()
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiService.getConnectionInfo();

        wifiInfo.state = wifiService.getWifiState();
        wifiInfo.bssid = info.getBSSID();//获取BSSID地址。
        wifiInfo.ssid = info.getSSID();//获取SSID地址。  需要连接网络的ID
        wifiInfo.ipAddress = info.getIpAddress(); //获取IP地址。4字节Int, XXX.XXX.XXX.XXX 每个XXX为一个字节
        wifiInfo.macAddress = info.getMacAddress();//获取MAC地址。
        wifiInfo.networkId = info.getNetworkId();//获取网络ID。
        wifiInfo.linkSpeed = info.getLinkSpeed();//获取连接速度，可以让用户获知这一信息。
        wifiInfo.rssi = info.getRssi();
        return wifiInfo;
    }

    public Info.BetteryInfo collectBetteryInfo() {
        Info.BetteryInfo betteryInfo = new Info.BetteryInfo();

        //Map<String, String> map = getInfoMap(cpuInfoStr);

        return betteryInfo;
    }

    public String collectBetteryInfoStr() {
        String info = SystemInfoUtil.getDumpsysBattery();
        return info;
    }

    public String collectCpuInfoStr() {
        String info = SystemInfoUtil.getCpuInfo();
        return info;
    }

    public String collectMemoryInfoStr() {
        String info = SystemInfoUtil.getMemoryInfo(MyManager.getApp());
        return info;
    }

    public ActivityManager.MemoryInfo collectMemoryInfo() {
        ActivityManager actMgr = (ActivityManager) MyManager.getApp().getSystemService(
                Context.ACTIVITY_SERVICE);
        android.app.ActivityManager.MemoryInfo memoryinfo
                = new android.app.ActivityManager.MemoryInfo();
        actMgr.getMemoryInfo(memoryinfo);
        return memoryinfo;
    }

    public Info.CpuInfo collectCpuInfo() {
        Info.CpuInfo cpuInfo = new Info.CpuInfo();
        String cpuInfoStr = SystemInfoUtil.getCpuInfo();

        Map<String, String> map = getInfoMap(cpuInfoStr);

        cpuInfo.processor = MapUtil.getString(map, "Processor");
        cpuInfo.features = MapUtil.getString(map, "Features");
        cpuInfo.cpu_implementer = MapUtil.getString(map, "CPU implementer");
        cpuInfo.cpu_architecture = MapUtil.getString(map, "CPU architecture");
        cpuInfo.cpu_variant = MapUtil.getString(map, "CPU variant");
        cpuInfo.cpu_part = MapUtil.getString(map, "CPU part");
        cpuInfo.cpu_revision = MapUtil.getString(map, "CPU revision");
        cpuInfo.hardware = MapUtil.getString(map, "Hardware");
        cpuInfo.revision = MapUtil.getString(map, "Revision");
        cpuInfo.serial = MapUtil.getString(map, "Serial");
        return cpuInfo;
    }

    public Map<String, String> getInfoMap(String infoStr) {
        Map<String, String> map = ObjectUtil.newHashMap();
        InputStreamReader ir = new InputStreamReader(new ByteArrayInputStream(
                infoStr.getBytes()));
        LineNumberReader reader = new LineNumberReader(ir);

        String tempString = null;
        try {
            while ((tempString = reader.readLine()) != null) {
                if (Utils.isEmpty(tempString)) {
                    continue;
                }
                String[] tmp = tempString.split(":");
                String key = tmp[0].trim();
                String value = tmp[1].trim();
                map.put(key, value);
            }
        } catch (Exception e) {
            logger.error(infoStr, e);
        } finally {
            IoUtil.closeQuietly(reader);
        }
        return map;
    }

    public String collectDiskInfo() {
        String info = SystemInfoUtil.getDiskInfo();
        return info;
    }

    public DisplayMetrics collectDisplayInfo() {
        DisplayMetrics info = MyManager.getApp().getResources().getDisplayMetrics();
        return info;
    }

    public Info.TelephonyInfo collectTelephonyInfo() {
        Info.TelephonyInfo info = new Info.TelephonyInfo();

        TelephonyManager tm = (TelephonyManager) MyManager.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        info.allCellInfo = tm.getAllCellInfo();
        info.callState = tm.getCallState();
        info.cellLocation = tm.getCellLocation();
        info.dataActivity = tm.getDataActivity();
        info.dataState = tm.getDataState();
        info.deviceId = tm.getDeviceId();
        info.deviceSoftwareVersion = tm.getDeviceSoftwareVersion();
        info.groupIdLevel1 = tm.getGroupIdLevel1();
        info.hasIccCard = tm.hasIccCard();
        info.isNetworkRoaming = tm.isNetworkRoaming();
        info.line1Number = tm.getLine1Number();
        info.mmsUAProfUrl = tm.getMmsUAProfUrl();
        info.mmsUserAgent = tm.getMmsUserAgent();
        info.neighboringCellInfo = tm.getNeighboringCellInfo();
        info.networkCountryIso = tm.getNetworkCountryIso();
        info.networkOperator = tm.getNetworkOperator();
        info.networkOperatorName = tm.getNetworkOperatorName();
        info.networkType = tm.getNetworkType();
        info.phoneType = tm.getPhoneType();
        info.simCountryIso = tm.getSimCountryIso();
        info.simOperatorName = tm.getSimOperatorName();
        info.simSerialNumber = tm.getSimSerialNumber();
        info.simState = tm.getSimState();
        info.simOperator = tm.getSimOperator();
        info.subscriberId = tm.getSubscriberId();
        info.voiceMailAlphaTag = tm.getVoiceMailAlphaTag();
        info.voiceMailNumber = tm.getVoiceMailNumber();
        return info;
    }
}
