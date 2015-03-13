package cm.android.util;

import android.app.Activity;

import java.util.LinkedList;

import cm.java.util.ObjectUtil;

/**
 * Activity栈
 */
public final class ActivityStack {

    private static final LinkedList<Activity> activityList = ObjectUtil.newLinkedList();

    private ActivityStack() {
    }

    /**
     * 单例模式中获取唯一的实例
     */
    public static ActivityStack getInstance() {
        return ActivityStackHolder.INSTANCE;
    }

    /**
     * 添加Activity到栈中中
     */
    public synchronized void addActivity(Activity activity) {
        activityList.addFirst(activity);
    }

    /**
     * 从栈中移除Activity
     */
    public synchronized void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 移除栈顶的Activity
     */
    public synchronized void removeTopActivity() {
        if (activityList.size() > 0) {
            Activity currentActivity = activityList.removeFirst();
            currentActivity.finish();
        }
    }

    /**
     * 遍历所有Activity并finish
     */
    public synchronized void finishAll() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    private static final class ActivityStackHolder {

        private static final ActivityStack INSTANCE = new ActivityStack();
    }
}
