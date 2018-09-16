package com.company.project.android.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;
import com.company.project.android.ui.fragment.home.HomeFragment;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.base
 * @class describe
 * @time 2018/3/12 17:39
 * @change
 * @class describe
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends SupportFragment implements BaseView {
    protected P mPresenter;
    protected OnBackToFirstListener _mBackToFirstListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackToFirstListener) {
            _mBackToFirstListener = (OnBackToFirstListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnBackToFirstListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();//创建Presenter
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getlayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDate();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        _mBackToFirstListener = null;
    }

    protected abstract P createPresenter();

    protected abstract int getlayoutId();

    protected abstract void initView(View view);

    protected abstract void initDate();

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            popChild();
        } else {
            if (this instanceof HomeFragment) {   // 如果是 第一个Fragment 则退出app
                _mActivity.finish();
            } else {                                    // 如果不是,则回到第一个Fragment
                _mBackToFirstListener.onBackToFirstFragment();
            }
        }
        return true;
    }

    public interface OnBackToFirstListener {
        void onBackToFirstFragment();
    }
}
