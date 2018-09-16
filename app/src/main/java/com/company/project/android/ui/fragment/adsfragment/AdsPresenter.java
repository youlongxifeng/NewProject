package com.company.project.android.ui.fragment.adsfragment;

import com.company.project.android.bean.DataResponse;
import com.company.project.android.mvp.rx.CommonSubscriber;
import com.company.project.android.mvp.rx.RxSchedulers;

import io.reactivex.observers.DisposableObserver;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.fragment.adsfragment
 * @class describe
 * @time 2018/3/12 17:37
 * @change
 * @class describe
 */



public class AdsPresenter extends AdsContract.Presenter {

    public AdsPresenter() {
        mModel = new AdsModel();
    }


    @Override
    public void requestConfig(String device_id, String version, String ads_support, long timetoken) {
        DisposableObserver<DataResponse> mObserver = getDisposableObserver();
        mModel.getConfigInfo(device_id, version, ads_support, timetoken)
              .compose(RxSchedulers.<DataResponse>switchObservableThread())
              .subscribe(mObserver);
        addSubscribe(mObserver);

        new CommonSubscriber<DataResponse>(mView){
            @Override
            public void onNext(DataResponse dataResponse) {

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        };


    }

    private DisposableObserver<DataResponse> getDisposableObserver() {
        return new DisposableObserver<DataResponse>() {
            @Override
            public void onNext(DataResponse response) {
                // LogUtils.i("TAG", "aBoolean===" + response);
              /*  LogUtils.i("TAG", "aBoolean===" +isViewAttached());
                if (isViewAttached() ) {
                    getView().onSucceed(response);
                }*/
              if(mView!=null){
                  mView.onSucceed(response);
              }

            }

            @Override
            public void onError(Throwable e) {
                if(mView!=null){
                    mView.onFail(e.getMessage());
                }

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
