package cm.android.app.mydeviceinfo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import cm.android.app.global.Tag;
import cm.android.app.mydeviceinfo.SampleActivity;
import cm.android.app.mydeviceinfo.adapter.AppsAdapter;
import cm.android.common.am.ui.ApplicationsState;
import cm.android.common.am.ui.DefaultHolder;
import cm.android.util.UIUtils;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class AppsInfoFragment extends BaseFragment implements AdapterView.OnItemClickListener {

    public static AppsInfoFragment newInstance(int sectionNumber) {
        AppsInfoFragment fragment = new AppsInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private DefaultHolder defaultHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        defaultHolder = new DefaultHolder();
        defaultHolder.init(getActivity().getApplication());
    }

    @Override
    public void onResume() {
        super.onResume();
        defaultHolder.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        defaultHolder = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        defaultHolder.pause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_list_info, container, false);
        ListView listView = UIUtils.findView(rootView, R.id.list_info);

        AppsAdapter adapter = new AppsAdapter(getActivity()
                .getApplicationContext(),
                ApplicationsState.getInstance(getActivity().getApplication()),
                defaultHolder.filterMode);

        defaultHolder.initView(adapter, listView);
        listView.setOnItemClickListener(this);
        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getActivity(), SampleActivity.class);
        intent.addFlags(
                Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        Bundle bundle = new Bundle();
        bundle.putString(Tag.PACKAGE_NAME,
                defaultHolder.getAdapter().getAppEntry(i).info.packageName);
        intent.putExtras(bundle);
        this.startActivity(intent);
    }
}
