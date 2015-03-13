package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

import cm.android.app.core.MyManager;
import cm.android.app.mydeviceinfo.ListInfoAdapter;
import cm.android.sdk.MyHandler;
import cm.android.sdk.content.BatteryReceiver;
import cm.android.util.UIUtils;
import cm.java.util.ObjectUtil;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class BatteryInfoFragment extends BaseFragment {

    public static BatteryInfoFragment newInstance(int sectionNumber) {
        BatteryInfoFragment fragment = new BatteryInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private ListInfoAdapter adapter;

    private BatteryReceiver batteryReceiver = new BatteryReceiver(
            new BatteryReceiver.BatteryChangedListener() {
                @Override
                public void batteryChanged(BatteryReceiver.BatteryInfo batteryInfo) {
                    Message message = new Message();
                    message.what = 0;
                    message.obj = batteryInfo;
                    mMyHandler.getHandler().sendMessage(message);
                }
            });

    @Override
    public void onResume() {
        super.onResume();
        batteryReceiver.register(MyManager.getApp());
    }

    @Override
    public void onPause() {
        super.onPause();
        batteryReceiver.unregister();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_info, container, false);

        ListView listView = UIUtils.findView(rootView, R.id.list_info);
        adapter = new ListInfoAdapter(getActivity());
        listView.setAdapter(adapter);
        return rootView;
    }

    private MyHandler mMyHandler = new MyHandler() {
        @Override
        public void handleMessage(Message msg) {
            BatteryReceiver.BatteryInfo info = (BatteryReceiver.BatteryInfo) msg.obj;

            String json = JSON.toJSONString(info);
            Map<String, String> map = JSON.parseObject(json, Map.class);
            List list = ObjectUtil.newArrayList();
            list.addAll(map.entrySet());

            adapter.update(list);
            adapter.notifyDataSetChanged();
        }
    };
}
