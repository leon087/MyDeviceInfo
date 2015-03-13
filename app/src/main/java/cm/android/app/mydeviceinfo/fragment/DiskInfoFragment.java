package cm.android.app.mydeviceinfo.fragment;

import android.os.Bundle;

import cm.android.app.info.InfoCollector;

public class DiskInfoFragment extends TextInfoFragment {

    public static DiskInfoFragment newInstance(int sectionNumber) {
        DiskInfoFragment fragment = new DiskInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getData() {
        String info = new InfoCollector().collectDiskInfo();
        return info;
    }
}
