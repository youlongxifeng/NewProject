package com.company.project.android.ui.main;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.company.project.android.R;
import com.company.project.android.base.BaseActivity;
import com.company.project.android.bean.Gank;

import java.lang.ref.WeakReference;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View {

    @BindView(R.id.return_value_name)
    TextView mRvNameTv;

    @Override
    public MainPresenter setPresenter() {
        return new MainPresenter();
    }

    @Override
    public int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {


    }

    @Override
    public void initDate() {
        mPresenter.getGank();
   //     mPresenter.accessToken("f2a9d153188d87e18adc233ca8ee30da", "564f939a8f8a5befa67d62bdf79e6fa5");

    }

    @Override
    public void setLogin(String active) {
        mRvNameTv.setText(active);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void onSucceed(Gank data) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void hideDialog() {

    }

    /**
     * 安卓Handler当做内部类，导致内存泄露的问题 自己来记录
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     */
    private static class MyHandler extends Handler {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);

    /**
     * Instances of anonymous classes do not hold an implicit
     * reference to their outer class when they are "static".
     */
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() { /* ... */ }
    };


}
