package cm.android.app.mydeviceinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cm.android.app.core.MyManager;
import cm.android.app.global.Tag;
import cm.android.app.mydeviceinfo.fragment.AppsInfoFragment;
import cm.android.app.mydeviceinfo.fragment.BatteryInfoFragment;
import cm.android.app.mydeviceinfo.fragment.CpuInfoFragment;
import cm.android.app.mydeviceinfo.fragment.DeviceInfoFragment;
import cm.android.app.mydeviceinfo.fragment.DiskInfoFragment;
import cm.android.app.mydeviceinfo.fragment.DisplayInfoFragment;
import cm.android.app.mydeviceinfo.fragment.MemoryInfoFragment;
import cm.android.app.mydeviceinfo.fragment.TelephonyInfoFragment;
import cm.android.app.mydeviceinfo.fragment.WifiInfoFragment;
import cm.android.util.UIUtils;
import cm.java.util.Utils;
import mydeviceinfo.ggg.android.mydeviceinfo.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final Logger logger = LoggerFactory.getLogger("MainActivity");

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_ab_drawer);
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        setCusTheme(R.color.colorPrimary);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        int num = position + 1;
        switch (num) {
            case 1:
                showFragment(DeviceInfoFragment.newInstance(num));
                break;
            case 2:
                showFragment(CpuInfoFragment.newInstance(num));
                break;
            case 3:
                showFragment(DiskInfoFragment.newInstance(num));
                break;
            case 4:
                showFragment(DisplayInfoFragment.newInstance(num));
                break;
            case 5:
                showFragment(MemoryInfoFragment.newInstance(num));
                break;
            case 6:
                showFragment(BatteryInfoFragment.newInstance(num));
                break;
            case 7:
                showFragment(TelephonyInfoFragment.newInstance(num));
                break;
            case 8:
                showFragment(WifiInfoFragment.newInstance(num));
                break;
            case 9:
                showFragment(AppsInfoFragment.newInstance(num));
                break;
            default:
                break;
        }
    }

    private void showFragment(final Fragment fragment) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            }
        }, 300);
    }

    public void onSectionAttached(int number) {
        mTitle = getTags()[number - 1];
    }

    public static String[] getTags() {
        return new String[]{
                MyManager.getApp().getString(R.string.title_info_device),
                MyManager.getApp().getString(R.string.title_info_cpu),
                MyManager.getApp().getString(R.string.title_info_disk),
                MyManager.getApp().getString(R.string.title_info_screen),
                MyManager.getApp().getString(R.string.title_info_memory),
                MyManager.getApp().getString(R.string.title_info_battery),
                MyManager.getApp().getString(R.string.title_info_telephony),
                MyManager.getApp().getString(R.string.title_info_wifi),
                MyManager.getApp().getString(R.string.title_info_apps),
        };
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_theme_random) {
            randomTheme();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setCusTheme(int colorResId) {
        int color = getResources().getColor(colorResId);
        MyManager.getAppPreference().edit().putInt(Tag.THEME_COLOR, color).commit();

        View fragmentContainerView = UIUtils.findView(this, R.id.navigation_drawer);
        fragmentContainerView.setBackgroundColor(color);
        toolbar.setBackgroundColor(color);
    }

    private int[] colorResIds = new int[]{R.color.android_blue, R.color.default_color,
            R.color.black, R.color.button_original, R.color.text_name, R.color.red,
            R.color.ripple_red, R.color.material_teal,
            R.color.ripple_material_teal, R.color.blue, R.color.grey};

    private void randomTheme() {
        int index = Utils.getRandom(1000 % colorResIds.length);
        setCusTheme(colorResIds[index]);
    }
}
