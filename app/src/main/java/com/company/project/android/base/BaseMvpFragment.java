package com.company.project.android.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.base
 * @class describe
 * @time 2018/3/12 17:39
 * @change
 * @class describe
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;

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
    }

    protected abstract P createPresenter();

    protected abstract int getlayoutId();

    protected abstract void initView(View view);

    protected abstract void initDate();
}
