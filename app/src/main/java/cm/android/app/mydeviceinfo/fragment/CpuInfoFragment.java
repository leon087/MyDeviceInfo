package cm.android.app.mydeviceinfo.fragment;

import android.os.Bundle;

import cm.android.app.info.InfoCollector;

public class CpuInfoFragment extends TextInfoFragment {

    public static CpuInfoFragment newInstance(int sectionNumber) {
        CpuInfoFragment fragment = new CpuInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getData() {
        String info = new InfoCollector().collectCpuInfoStr();
        return info;
    }
}
