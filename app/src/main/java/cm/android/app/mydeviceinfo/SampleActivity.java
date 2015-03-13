package cm.android.app.mydeviceinfo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import cm.android.app.core.MyManager;
import cm.android.app.global.Tag;
import cm.android.widget.SlidingTabLayout;
import mydeviceinfo.ggg.android.mydeviceinfo.R;

public class SampleActivity extends ActionBarActivity {

    ViewPager pager;

    private String titles[] = new String[]{"Tab1", "Tab2", "Tab3"};

    SlidingTabLayout slidingTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_detail);

        pager = (ViewPager) findViewById(R.id.viewpager);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles));
        slidingTabLayout.setViewPager(pager);

        int color = MyManager.getAppPreference()
                .getInt(Tag.THEME_COLOR, getResources().getColor(R.color.colorPrimary));
        slidingTabLayout.setBackgroundColor(color);
    }
}
