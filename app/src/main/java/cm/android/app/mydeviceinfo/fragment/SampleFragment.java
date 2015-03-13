package cm.android.app.mydeviceinfo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class SampleFragment extends Fragment {

    public static SampleFragment newInstance(int sectionNumber) {
        SampleFragment fragment = new SampleFragment();
        return fragment;
    }

    public SampleFragment() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(
                R.layout.activity_app_detail_tmp,
                container, false);
        return view;
    }
}
