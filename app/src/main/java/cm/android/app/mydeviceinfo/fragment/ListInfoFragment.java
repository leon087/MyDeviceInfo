package cm.android.app.mydeviceinfo.fragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import cm.android.app.mydeviceinfo.ListInfoAdapter;
import cm.android.sdk.MyHandler;
import cm.android.util.UIUtils;
import cm.java.util.ObjectUtil;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public abstract class ListInfoFragment extends BaseFragment {

    private static final Logger logger = LoggerFactory.getLogger("list_info");

    private ListInfoAdapter adapter;

    private ListView listView;

    public ListInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list_info, container, false);

        listView = UIUtils.findView(rootView, R.id.list_info);
        adapter = new ListInfoAdapter(getActivity());
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        refresh();

//        TextView textView = new TextView(getActivity());
//        String str = getStrData();
//        textView.setText(str);
//        textView.setTextSize(18);
//
//        listView.addFooterView(textView);
    }

    protected List getListData() {
        return ObjectUtil.newArrayList();
    }

    protected String getStrData() {
        return "";
    }

    protected void refresh() {
        myHandler.getHandler().obtainMessage(0).sendToTarget();
    }

    private MyHandler myHandler = new MyHandler() {
        @Override
        public void handleMessage(Message msg) {
            List list = getListData();
            adapter.update(list);
            adapter.notifyDataSetChanged();
        }
    };

}
