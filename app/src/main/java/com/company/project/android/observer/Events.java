package com.company.project.android.observer;

import android.os.Handler;
import android.os.Looper;


import com.discovery.protection.project.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.observer
 * @class describe
 * @time 2018/3/7 8:54
 * @change
 * @class describe
 */

public class Events {
    private final static Events mInstance = new Events();
    public static void post(Object obj){
        mInstance.mEventsBus.post(obj);
    }

    public static void post(final Object obj,long delay)
    {
        if(delay > 0){
            mInstance.mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mInstance.mEventsBus.post(obj);
                }
            },delay);
        }else{
            mInstance.mEventsBus.post(obj);
        }
    }

    public static void register(Object obj)
    {
        mInstance.mEventsBus.register(obj);
    }

    public static void unRegister(Object obj){
        mInstance.mEventsBus.unregister(obj);
    }

    private final Handler mHandler;
    private final EventBus mEventsBus;
    private Events()
    {
        //添加索引,添加索引后只有执行相关索引MyEventBusIndex才会有，做记录
        mEventsBus = EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        mHandler = new Handler(Looper.getMainLooper());
    }
}
