package me.yokeyword.fragmentation.queue;

import android.os.Handler;
import android.os.Looper;

import java.util.LinkedList;
import java.util.Queue;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportHelper;

/**
 * The queue of perform action.
 * <p>
 * Created by YoKey on 17/12/29.
 */
public class ActionQueue {
    /**
     * 　　add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     　　remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     　　element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     　　offer       添加一个元素并返回true       如果队列已满，则返回false
     　　poll         移除并返问队列头部的元素    如果队列为空，则返回null
     　　peek       返回队列头部的元素             如果队列为空，则返回null
     　　put         添加一个元素                      如果队列满，则阻塞
     　　take        移除并返回队列头部的元素     如果队列为空，则阻塞
     */
    private Queue<Action> mQueue = new LinkedList<>();
    private Handler mMainHandler;

    public ActionQueue(Handler mainHandler) {
        this.mMainHandler = mainHandler;
    }

    public void enqueue(final Action action) {
        if (isThrottleBACK(action)) return;

        if (action.action == Action.ACTION_LOAD && mQueue.isEmpty()
                && Thread.currentThread() == Looper.getMainLooper().getThread()) {
            action.run();
            return;
        }

        mMainHandler.post(new Runnable() {
            @Override
            public void run() {
                enqueueAction(action);
            }
        });
    }

    private void enqueueAction(Action action) {
        mQueue.add(action);
        if (mQueue.size() == 1) {
            handleAction();
        }
    }

    private void handleAction() {
        if (mQueue.isEmpty()) return;

        Action action = mQueue.peek();
        action.run();

        executeNextAction(action);
    }

    private void executeNextAction(Action action) {
        if (action.action == Action.ACTION_POP) {
            ISupportFragment top = SupportHelper.getBackStackTopFragment(action.fragmentManager);
            action.duration = top == null ? Action.DEFAULT_POP_TIME : top.getSupportDelegate().getExitAnimDuration();
        }

        mMainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mQueue.poll();//移除并返问队列头部的元素    如果队列为空，则返回null
                handleAction();
            }
        }, action.duration);
    }

    private boolean isThrottleBACK(Action action) {
        if (action.action == Action.ACTION_BACK) {
            Action head = mQueue.peek();// 返回队列头部的元素             如果队列为空，则返回null
            if (head != null && head.action == Action.ACTION_POP) {
                return true;
            }
        }
        return false;
    }
}
