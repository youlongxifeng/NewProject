package com.company.project.android.ui.main;

import android.util.Log;

import com.company.project.android.bean.Gank;
import com.company.project.android.mvp.rx.RxSchedulers;
import com.company.project.android.utils.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.main
 * @class describe
 * @time 2018/2/8 16:57
 * @change
 * @class describe
 */


public class MainPresenter extends MainContract.Presenter {


    public MainPresenter(/*MainContract.View view*/) {
        //this.mView = view;
        this.mModel = new MainModel();
    }

    @Override
    public String login(Map maps) {
      /*  String name = mModel.loginSuccess();
        */
        Log.i("TAG", "=====active=" + Thread.currentThread().getName());
        String name = mModel.loginSuccess();
        Log.i("TAG", "=====active=" + name + "  =" + (mView != null));
        if (mView != null) {
            mView.setLogin(name);
        }
        return "";
    }

    private Observable<Boolean> getObservable() {
        return Observable.just(true);
    }


    private CompositeDisposable mDisposables = new CompositeDisposable();

    @Override
    public void getGank() {
        DisposableObserver<Gank> mObserver = getDisposableObserver();

        // getObservable()//被观察者
       /* Disposable disposable=mModel.getGank()
              .compose(RxSchedulers.<Gank>switchObservableThread())
               .subscribe(mObserver,  new RxException<Throwable> );*/
        //  .subscribe(mObserver);
        mModel.getGank()
              .compose(RxSchedulers.<Gank>switchObservableThread())
              .subscribe(mObserver);
        addSubscribe(mObserver);

    }

    @Override
    public void accessToken(String app_id, String secret_key) {
        DisposableObserver<JSONObject> mObserver = getDisposableObserver2();

        try {
            JSONObject requestData = new JSONObject();
            requestData.put("appid",app_id);
            requestData.put("secret",secret_key);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),requestData.toString());
            mModel.accessToken( requestBody)
                  .compose(RxSchedulers.<JSONObject>switchObservableThread())
                  .subscribe(mObserver);
            addSubscribe(mObserver);
        } catch (JSONException e) {
            e.printStackTrace();
            LogUtils.i("TAG","e======0="+e);
        }

    }

    private DisposableObserver<Gank> getDisposableObserver() {
        return new DisposableObserver<Gank>() {
            @Override
            public void onNext(Gank aBoolean) {
                LogUtils.i("TAG","aBoolean==="+aBoolean);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    public DisposableObserver<JSONObject> getDisposableObserver2() {
        return new DisposableObserver<JSONObject>() {
            @Override
            public void onNext(JSONObject aBoolean) {
                LogUtils.i("TAG","aBoolean==="+aBoolean);

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i("TAG","aBoolean==e="+e);
            }

            @Override
            public void onComplete() {
                LogUtils.i("TAG","onComplete=");
            }
        };
    }
}
