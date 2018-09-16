package com.company.project.android.ui.fragment.signin;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.bilibili.magicasakura.widgets.KeyEditText;
import com.bilibili.magicasakura.widgets.TintButton;
import com.company.project.android.R;
import com.company.project.android.base.BaseMvpFragment;
import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.utils.CommonUtil;

import butterknife.BindView;

/**
 * Created by xiexie on 2018/9/16.
 */

public class SigninFragment extends BaseMvpFragment<SigninPresenter> implements KeyEditText.KeyPreImeListener {
    private final static String TAG = SigninFragment.class.getSimpleName();
    @BindView(R.id.et_login_name)
    KeyEditText mLoginName;
    @BindView(R.id.et_login_pwd)
    KeyEditText mLoginPwd;
    @BindView(R.id.login_btn)
    TintButton login_btn;

    public static SigninFragment newInstance() {
        Bundle args = new Bundle();
        SigninFragment fragment = new SigninFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected SigninPresenter createPresenter() {
        return new SigninPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_signin_main;
    }

    @Override
    protected void initView(View view) {
        mLoginName.setKeyPreImeListener(this);
        mLoginName.addTextChangedListener(watcher);
        mLoginPwd.setKeyPreImeListener(this);
        mLoginPwd.addTextChangedListener(watcher);

    }

    @Override
    protected void initDate() {

    }

    @Override
    public void onKeyPreImeUp(int keyCode, KeyEvent event) {
        mLoginName.clearFocus();
        mLoginPwd.clearFocus();
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (CommonUtil.isEmpty(mLoginName.getText().toString()) && CommonUtil.isEmpty(mLoginPwd.getText().toString())) {
                login_btn.isEnabled();
            }
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
