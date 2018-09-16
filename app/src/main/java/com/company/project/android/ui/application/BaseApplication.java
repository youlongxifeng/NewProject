package com.company.project.android.ui.application;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

import com.company.project.android.observer.Events;
import com.company.project.android.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.NoSubscriberEvent;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.application
 * @class describe
 * @time 2018/2/8 17:03
 * @change
 * @class describe
 */

public class BaseApplication extends Application implements ActivityLifecycleCallbacks {
    private static BaseApplication mContext;
    private EventBus mEventsBus;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Events.register(this);
        Events.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("=======================");
            }
        });
    }

    public static BaseApplication getInstance() {
        return mContext;
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(NoSubscriberEvent event) {
        android.util.Log.w("zxj", String.format("event no Subscriber %s , %s ", event.originalEvent.getClass(), event.originalEvent.toString()));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static BaseApplication getContext() {
        return mContext;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

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
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}
