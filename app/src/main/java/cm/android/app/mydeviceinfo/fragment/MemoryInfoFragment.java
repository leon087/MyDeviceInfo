package cm.android.app.mydeviceinfo.fragment;

import com.alibaba.fastjson.JSON;

import android.app.ActivityManager;
import android.os.Bundle;

import java.util.List;
import java.util.Map;

import cm.android.app.info.InfoCollector;
import cm.java.util.ObjectUtil;

public class MemoryInfoFragment extends ListInfoFragment {

    public static MemoryInfoFragment newInstance(int sectionNumber) {
        MemoryInfoFragment fragment = new MemoryInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getStrData() {
        String info = new InfoCollector().collectMemoryInfoStr();
        return info;
    }

    @Override
    protected List getListData() {
        ActivityManager.MemoryInfo info = new InfoCollector().collectMemoryInfo();

        String json = JSON.toJSONString(info);
        Map<String, String> map = JSON.parseObject(json, Map.class);
        List list = ObjectUtil.newArrayList();
        list.addAll(map.entrySet());
        return list;
    }
}
