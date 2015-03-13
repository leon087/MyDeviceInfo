package cm.android.app.mydeviceinfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import cm.android.sdk.widget.adapter.MyBaseAdapter;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class ListInfoAdapter extends MyBaseAdapter<Map.Entry<String, Object>> {

    public ListInfoAdapter(Context context) {
        super(context);
    }

    @Override
    protected void updateView(ViewHolder viewHolder, int position,
            Map.Entry<String, Object> data) {
        DefaultViewHolder defaultViewHolder = (DefaultViewHolder) viewHolder;
        TextView name = defaultViewHolder.getView(R.id.info_name);
        TextView value = defaultViewHolder.getView(R.id.info_value);
        name.setText(data.getKey());
        value.setText(String.valueOf(data.getValue()));
    }

    @Override
    protected View createRootView(int position, ViewGroup parent) {
        return createRootView(R.layout.adapter_list_info);
    }
}
