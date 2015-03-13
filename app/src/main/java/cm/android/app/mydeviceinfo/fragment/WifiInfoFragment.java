package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.util.List;
import java.util.Map;

import cm.android.app.info.Info;
import cm.android.app.info.InfoCollector;
import cm.android.sdk.content.BaseBroadcastReceiver;
import cm.java.util.ObjectUtil;

public class WifiInfoFragment extends ListInfoFragment {

    public static WifiInfoFragment newInstance(int sectionNumber) {
        WifiInfoFragment fragment = new WifiInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private WifiReceiver wifiReceiver = new WifiReceiver();

    @Override
    protected List getListData() {
        //
        Info.WifiInfo info = new InfoCollector().collectWifiInfo();
        String json = JSON.toJSONString(info);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        List list = ObjectUtil.newArrayList();
        list.addAll(map.entrySet());

        return list;
    }

    private class WifiReceiver extends BaseBroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }

        @Override
        public IntentFilter createIntentFilter() {
            IntentFilter filter = new IntentFilter();
            filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
            filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
            return filter;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        wifiReceiver.register(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        wifiReceiver.unregister();
    }
}
