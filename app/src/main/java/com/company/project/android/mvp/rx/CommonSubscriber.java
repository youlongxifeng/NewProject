package com.company.project.android.mvp.rx;

import android.text.TextUtils;

import com.company.project.android.mvp.BaseView;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.mvp.rx
 * @class describe
 * @time 2018/2/10 14:31
 * @change
 * @class describe
 */

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T>  {
    private BaseView mView;
    private String mErrorMsg;
    private boolean isShowErrorState = true;

    protected CommonSubscriber(BaseView view){
        this.mView = view;
    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
           // mView.showErrorMsg(mErrorMsg);
     //   } else if (e instanceof ApiException) {
          //  mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
           // mView.showErrorMsg("数据加载失败");
        } else {
           // mView.showErrorMsg("未知错误");
        }
        if (isShowErrorState) {
         //   mView.stateError();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  mView.stateLoading();
    }

    @Override
    public void onComplete() {
    //    mView.stateMain();
    }
}
