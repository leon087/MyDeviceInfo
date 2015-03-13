package cm.android.app.mydeviceinfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cm.android.common.am.ui.ApplicationsState;
import cm.android.common.am.ui.BaseAppAdapter;
import cm.android.util.MyFormatter;
import cm.android.util.UIUtils;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class AppsAdapter extends BaseAppAdapter {

    public AppsAdapter(Context context,
            ApplicationsState state, int filterMode) {
        super(context, state, filterMode);
    }

    @Override
    protected void updateView(ViewHolder viewHolder, int position,
            ApplicationsState.AppEntry data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.icon.setBackground(data.icon);
        holder.name.setText(data.label);
        holder.size.setText(MyFormatter.formateFileSize(context,
                data.apkFile.length()));
        holder.version.setText(data.info.versionName);
    }

    @Override
    protected ViewHolder initView(View convertView) {
        MyViewHolder holder = new MyViewHolder();
        holder.icon = UIUtils.findView(convertView, R.id.apk_icon);
        holder.name = UIUtils.findView(convertView, R.id.apk_name);
        holder.size = UIUtils.findView(convertView, R.id.apk_size);
        holder.version = UIUtils.findView(convertView, R.id.apk_version);

        holder.name.setTextColor(Color.BLACK);
        return holder;
    }

    @Override
    protected View createRootView(int position, ViewGroup parent) {
        return createRootView(R.layout.adapter_apps);
    }

    private static final class MyViewHolder extends ViewHolder {

        ImageView icon;

        TextView name;

        TextView size;

        TextView version;
    }
}
