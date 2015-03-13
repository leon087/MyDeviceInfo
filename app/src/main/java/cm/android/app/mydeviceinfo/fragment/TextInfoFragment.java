package cm.android.app.mydeviceinfo.fragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cm.android.util.UIUtils;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public abstract class TextInfoFragment extends BaseFragment {

    private static final Logger logger = LoggerFactory.getLogger("list_info");

    private TextView textView;

    public TextInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_info, container, false);

        textView = UIUtils.findView(rootView, R.id.text_info);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        String text = getData();
        textView.setText(text);
    }

    abstract protected String getData();
}
