package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.os.Bundle;

import java.util.List;
import java.util.Map;

import cm.android.app.info.Info;
import cm.android.app.info.InfoCollector;
import cm.java.util.ObjectUtil;

public class DeviceInfoFragment extends ListInfoFragment {

    public static DeviceInfoFragment newInstance(int sectionNumber) {
        DeviceInfoFragment fragment = new DeviceInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List getListData() {
        //
        Info.BasicInfo basicInfo = new InfoCollector().collectBasicInfo();
        String json = JSON.toJSONString(basicInfo);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        List list = ObjectUtil.newArrayList();
        list.addAll(map.entrySet());

        return list;
    }
}
