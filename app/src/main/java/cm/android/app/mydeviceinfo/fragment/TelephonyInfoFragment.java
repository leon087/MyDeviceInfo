package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import java.util.List;
import java.util.Map;

import cm.android.app.core.MyManager;
import cm.android.app.info.Info;
import cm.android.app.info.InfoCollector;
import cm.android.sdk.content.BaseBroadcastReceiver;
import cm.java.util.ObjectUtil;

public class TelephonyInfoFragment extends ListInfoFragment {

    public static TelephonyInfoFragment newInstance(int sectionNumber) {
        TelephonyInfoFragment fragment = new TelephonyInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private TelephonyReceiver telephonyReceiver = new TelephonyReceiver();

    @Override
    protected List getListData() {
        //
        Info.TelephonyInfo info = new InfoCollector().collectTelephonyInfo();
        String json = JSON.toJSONString(info);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        List list = ObjectUtil.newArrayList();
        list.addAll(map.entrySet());

        return list;
    }

    @Override
    public void onResume() {
        super.onResume();
        register();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregister();
    }

    private void register() {
        TelephonyManager tm = (TelephonyManager) MyManager.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);

        int event = PhoneStateListener.LISTEN_SERVICE_STATE
                | PhoneStateListener.LISTEN_MESSAGE_WAITING_INDICATOR
                | PhoneStateListener.LISTEN_CALL_FORWARDING_INDICATOR
                | PhoneStateListener.LISTEN_CELL_LOCATION
                | PhoneStateListener.LISTEN_CALL_STATE
                | PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
                | PhoneStateListener.LISTEN_DATA_ACTIVITY
                | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
                | PhoneStateListener.LISTEN_CELL_INFO;
        tm.listen(mPhoneStateListener, event);

        telephonyReceiver.register(getActivity());
    }

    private void unregister() {
        TelephonyManager tm = (TelephonyManager) MyManager.getApp()
                .getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(null, PhoneStateListener.LISTEN_NONE);

        telephonyReceiver.unregister();
    }

    private class TelephonyReceiver extends BaseBroadcastReceiver {

        private final static String ACTION_SIM_STATE_CHANGED
                = "android.intent.action.SIM_STATE_CHANGED";

        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }

        @Override
        public IntentFilter createIntentFilter() {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ACTION_SIM_STATE_CHANGED);
            return filter;
        }
    }

    private PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            refresh();
        }

        @Override
        public void onMessageWaitingIndicatorChanged(boolean mwi) {
            super.onMessageWaitingIndicatorChanged(mwi);
            refresh();
        }

        @Override
        public void onCallForwardingIndicatorChanged(boolean cfi) {
            super.onCallForwardingIndicatorChanged(cfi);
        }

        @Override
        public void onCellLocationChanged(CellLocation location) {
            super.onCellLocationChanged(location);
            refresh();
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
        }

        @Override
        public void onDataConnectionStateChanged(int state) {
            super.onDataConnectionStateChanged(state);
            refresh();
        }

        @Override
        public void onDataConnectionStateChanged(int state, int networkType) {
            super.onDataConnectionStateChanged(state, networkType);
            refresh();
        }

        @Override
        public void onDataActivity(int direction) {
            super.onDataActivity(direction);
            refresh();
        }

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            refresh();
        }

        @Override
        public void onCellInfoChanged(List<CellInfo> cellInfo) {
            super.onCellInfoChanged(cellInfo);
            refresh();
        }
    };
}
