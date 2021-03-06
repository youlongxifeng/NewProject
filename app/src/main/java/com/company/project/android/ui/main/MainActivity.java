package com.company.project.android.ui.main;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import com.company.project.android.R;
import com.company.project.android.base.BaseActivity;
import com.company.project.android.base.BaseMvpFragment;
import com.company.project.android.bean.Gank;
import com.company.project.android.ui.fragment.home.HomeFragment;
import com.company.project.android.ui.fragment.signin.SigninFragment;
import com.company.project.android.utils.LogUtils;
import com.company.project.android.widget.BottomBar;
import com.company.project.android.widget.BottomBarTab;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;


public class MainActivity extends BaseActivity<MainPresenter>
        implements MainContract.View ,  BaseMvpFragment.OnBackToFirstListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOURTH = 3;

    private SupportFragment[] mFragments = new SupportFragment[4];
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

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
        SupportFragment firstFragment = findFragment(HomeFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = SigninFragment.newInstance();
            mFragments[SECOND] = HomeFragment.newInstance();
            mFragments[THIRD] = HomeFragment.newInstance();
            mFragments[FOURTH] = HomeFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findFragment(HomeFragment.class);
            mFragments[THIRD] = findFragment(HomeFragment.class);
            mFragments[FOURTH] = findFragment(HomeFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this, R.mipmap.ic_home_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_discover_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_message_white_24dp))
                .addItem(new BottomBarTab(this, R.mipmap.ic_account_circle_white_24dp));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
                final SupportFragment currentFragment = mFragments[position];
                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();

                // 如果不在该类别Fragment的主页,则回到主页;
                if (count > 1) {
                    if (currentFragment instanceof SigninFragment) {
                        currentFragment.popToChild(SigninFragment.class, false);
                    } else if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    } else if (currentFragment instanceof HomeFragment) {
                        currentFragment.popToChild(HomeFragment.class, false);
                    }
                    return;
                }


                // 这里推荐使用EventBus来实现 -> 解耦
                if (count == 1) {
                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
                    EventBusActivityScope.getDefault(MainActivity.this).post(new TabSelectedEvent(position));
                }
            }
        });

    }

    @Override
    public void initDate() {
        mPresenter.getGank();
        //     mPresenter.accessToken("f2a9d153188d87e18adc233ca8ee30da", "564f939a8f8a5befa67d62bdf79e6fa5");

    }

    @Override
    public void setLogin(String active) {
        LogUtils.i(TAG, active);
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
    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            ActivityCompat.finishAfterTransition(this);
        }
    }

    @Override
    public void onBackToFirstFragment() {
        mBottomBar.setCurrentItem(0);
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
