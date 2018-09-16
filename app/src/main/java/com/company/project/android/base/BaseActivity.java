package com.company.project.android.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.base
 * @class describe
 * @time 2018/2/8 16:53
 * @change
 * @class describe
 */


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    public P mPresenter;
    Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResID = getlayoutId();
        if (layoutResID != 0) {
            setContentView(layoutResID);
            mUnbinder = ButterKnife.bind(this);
            mPresenter = setPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
        initView();
        initDate();

    }

    //由于某些工具类，需要在setContentView的顺序前或者后来编辑
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

    }

    public abstract P setPresenter();

    public abstract int getlayoutId();

    public abstract void initView();

    public abstract void initDate();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.unSubscribe();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
