package cm.android.app.info;

import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;

import java.util.List;

import cm.java.proguard.annotations.Keep;

public class Info {

    public static class TelephonyInfo implements Cloneable {

        public String deviceSoftwareVersion;

        public String deviceId;

        public List<NeighboringCellInfo> neighboringCellInfo;

        public CellLocation cellLocation;

        public int phoneType;

        public String networkOperatorName;

        public String networkOperator;

        public boolean isNetworkRoaming;

        public String networkCountryIso;

        public int networkType;

        public boolean hasIccCard;

        public int simState;

        public String simOperator;

        public String simOperatorName;

        public String simCountryIso;

        public String simSerialNumber;

        public String subscriberId;

        public String groupIdLevel1;

        public String line1Number;

        public String voiceMailNumber;

        public String voiceMailAlphaTag;

        public int callState;

        public int dataActivity;

        public int dataState;

        public List<android.telephony.CellInfo> allCellInfo;

        public String mmsUserAgent;

        public String mmsUAProfUrl;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public static class WifiInfo implements Cloneable {

        @Keep
        public int state;//

        @Keep
        public String bssid;//获取BSSID地址。

        @Keep
        public String ssid;//获取SSID地址。  需要连接网络的ID

        @Keep
        public int ipAddress;//获取IP地址。4字节Int, XXX.XXX.XXX.XXX 每个XXX为一个字节

        @Keep
        public String macAddress;//获取MAC地址。

        @Keep
        public int networkId;//获取网络ID。

        @Keep
        public int linkSpeed;//获取连接速度，可以让用户获知这一信息。

        @Keep
        public int rssi;//获取RSSI，RSSI就是接受信号强度指示

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "WifiInfo{" +
                    "state=" + state +
                    ", bssid='" + bssid + '\'' +
                    ", ssid='" + ssid + '\'' +
                    ", ipAddress=" + ipAddress +
                    ", macAddress='" + macAddress + '\'' +
                    ", networkId=" + networkId +
                    ", linkSpeed=" + linkSpeed +
                    ", rssi=" + rssi +
                    '}';
        }
    }

    @Keep
    public static class BetteryInfo implements Cloneable {

        @Keep
        public String level;

        @Keep
        public String voltage;

        @Keep
        public String temperature;

        @Keep
        public String status;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "BetteryInfo{" +
                    "level='" + level + '\'' +
                    ", voltage='" + voltage + '\'' +
                    ", temperature='" + temperature + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }

    @Keep
    public static class ApkInfo implements Cloneable {

        @Keep
        public String packageName;

        @Keep
        public String versionName;

        @Keep
        public int versionCode;

        @Keep
        public long firstInstallTime;

        @Keep
        public long lastUpdateTime;

        @Keep
        public int sharedUserLabel;

        @Keep
        public String sharedUserId;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "ApkInfo{" +
                    "packageName='" + packageName + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", versionCode=" + versionCode +
                    ", firstInstallTime=" + firstInstallTime +
                    ", lastUpdateTime=" + lastUpdateTime +
                    ", sharedUserLabel=" + sharedUserLabel +
                    ", sharedUserId='" + sharedUserId + '\'' +
                    '}';
        }
    }

    @Keep
    public static class BasicInfo implements Cloneable {

        @Keep
        public String uuid;

        @Keep
        public String imei;

        @Keep
        public String imsi;

        @Keep
        public String kernel_version;

        @Keep
        public boolean root;

        @Keep
        public String model;

        @Keep
        public String manufacturer;

        @Keep
        public String board;

        @Keep
        public String fingerprint;

        @Keep
        public String bootloader;

        @Keep
        public String brand;

        @Keep
        public String cpu_abi;

        @Keep
        public String cpu_abi2;

        @Keep
        public String device;

        @Keep
        public String display;

        @Keep
        public String hardware;

        @Keep
        public String host;

        @Keep
        public String id;

        @Keep
        public String product;

        @Keep
        public String serial;

        @Keep
        public String tags;

        @Keep
        public String type;

        @Keep
        public String user;

        @Keep
        public long time;

        @Keep
        public int version_sdk_int;

        @Keep
        public String version_codename;

        @Keep
        public String version_release;

        @Keep
        public String version_incremental;

        @Keep
        public String net_hostname;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "BasicInfo{" +
                    "uuid='" + uuid + '\'' +
                    ", imei='" + imei + '\'' +
                    ", imsi='" + imsi + '\'' +
                    ", kernel_version='" + kernel_version + '\'' +
                    ", root=" + root +
                    ", model='" + model + '\'' +
                    ", manufacturer='" + manufacturer + '\'' +
                    ", board='" + board + '\'' +
                    ", fingerprint='" + fingerprint + '\'' +
                    ", bootloader='" + bootloader + '\'' +
                    ", brand='" + brand + '\'' +
                    ", cpu_abi='" + cpu_abi + '\'' +
                    ", cpu_abi2='" + cpu_abi2 + '\'' +
                    ", device='" + device + '\'' +
                    ", display='" + display + '\'' +
                    ", hardware='" + hardware + '\'' +
                    ", host='" + host + '\'' +
                    ", id='" + id + '\'' +
                    ", product='" + product + '\'' +
                    ", serial='" + serial + '\'' +
                    ", tags='" + tags + '\'' +
                    ", type='" + type + '\'' +
                    ", user='" + user + '\'' +
                    ", time=" + time +
                    ", version_sdk_int=" + version_sdk_int +
                    ", version_codename='" + version_codename + '\'' +
                    ", version_release='" + version_release + '\'' +
                    ", version_incremental='" + version_incremental + '\'' +
                    ", net_hostname='" + net_hostname + '\'' +
                    '}';
        }
    }

    @Keep
    public static class CpuInfo implements Cloneable {

        @Keep
        public String processor;

        @Keep
        public String features;

        @Keep
        public String cpu_implementer;

        @Keep
        public String cpu_architecture;

        @Keep
        public String cpu_variant;

        @Keep
        public String cpu_part;

        @Keep
        public String cpu_revision;

        @Keep
        public String hardware;

        @Keep
        public String revision;

        @Keep
        public String serial;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return "CpuInfo{" +
                    "processor='" + processor + '\'' +
                    ", features='" + features + '\'' +
                    ", cpu_implementer='" + cpu_implementer + '\'' +
                    ", cpu_architecture='" + cpu_architecture + '\'' +
                    ", cpu_variant='" + cpu_variant + '\'' +
                    ", cpu_part='" + cpu_part + '\'' +
                    ", cpu_revision='" + cpu_revision + '\'' +
                    ", hardware='" + hardware + '\'' +
                    ", revision='" + revision + '\'' +
                    ", serial='" + serial + '\'' +
                    '}';
        }
    }
}
