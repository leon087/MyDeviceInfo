package cm.android.app.mydeviceinfo.fragment;

import android.app.Activity;
import android.app.Fragment;

import cm.android.app.mydeviceinfo.MainActivity;

public class BaseFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";

    public BaseFragment() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
