package com.oubowu.slideback;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.Stack;

/**
 * Created by Oubowu on 2016/9/20 3:28.
 */
public class ActivityHelper implements Application.ActivityLifecycleCallbacks {

    private static Stack<Activity> mActivityStack;

    public ActivityHelper() {
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

        // Log.e("TAG", "ActivityHelper-销毁: " + activity);
        mActivityStack.remove(activity);

        if (mListener != null) {
            mListener.onDestroy(activity);
        }
    }

    /**
     * 获取上一个Activity
     */
    public Activity getPreActivity() {
        if (mActivityStack == null) {
            return null;
        }
        int size = mActivityStack.size();
        if (size < 2) {
            return null;
        }
        return mActivityStack.elementAt(size - 2);
    }

    /**
     * 可以用做退出APP
     */
    public void finishAllActivity() {
        if (mActivityStack == null) {
            return;
        }
        for (Activity activity : mActivityStack) {
            activity.finish();
        }
    }

    public void printAllActivity() {
        if (mActivityStack == null) {
            return;
        }
        for (int i = 0; i < mActivityStack.size(); i++) {
            Log.e("TAG", "位置" + i + ": " + mActivityStack.get(i));
        }
    }

    @Deprecated
    void setOnActivityDestroyListener(OnActivityDestroyListener listener) {
        mListener = listener;
    }

    @Deprecated
    private OnActivityDestroyListener mListener;

    @Deprecated
    interface OnActivityDestroyListener {
        void onDestroy(Activity activity);
    }

}
