package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.List;
import java.util.Map;

import cm.android.app.info.InfoCollector;
import cm.java.util.ObjectUtil;

public class DisplayInfoFragment extends ListInfoFragment {

    public static DisplayInfoFragment newInstance(int sectionNumber) {
        DisplayInfoFragment fragment = new DisplayInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected List getListData() {
        DisplayMetrics info = new InfoCollector().collectDisplayInfo();

        String json = JSON.toJSONString(info);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        List list = ObjectUtil.newArrayList();
        list.addAll(map.entrySet());
        return list;
    }
}
