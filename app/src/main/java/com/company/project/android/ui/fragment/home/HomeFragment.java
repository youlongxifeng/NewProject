package com.company.project.android.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.company.project.android.R;
import com.company.project.android.base.BaseMvpFragment;
import com.company.project.android.mvp.BasePresenter;

/**
 * Created by xiexie on 2018/9/16.
 */

public class HomeFragment extends BaseMvpFragment {

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (findChildFragment(HomeFragment.class) == null) {
            loadRootFragment(R.id.fl_home_container, HomeFragment.newInstance());
        }
    }
}
