package com.company.project.android.ui.fragment.adsfragment;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.company.project.android.R;
import com.company.project.android.base.BaseMvpFragment;
import com.company.project.android.bean.DataResponse;
import com.company.project.android.utils.LogUtils;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.fragment.adsfragment
 * @class describe
 * @time 2018/3/12 17:37
 * @change
 * @class describe
 */


public class AdsFragment extends BaseMvpFragment<AdsPresenter> implements AdsContract.View {
    TextView txt_context;

    public static AdsFragment newInstance(String param) {
        AdsFragment fragment = new AdsFragment();
        Bundle args = new Bundle();
        args.putString("param", param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected AdsPresenter createPresenter() {
        return new AdsPresenter( );
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_ads;
    }

    @Override
    protected void initView(View view) {
        txt_context=view.findViewById(R.id.txt_context);
    }

    @Override
    protected void initDate() {
        LogUtils.i("TAG", "getDataDirectory==" + Environment.getDataDirectory());
        LogUtils.i("TAG", "getDataDirectory=" + Environment.getDataDirectory().getAbsolutePath());
        LogUtils.i("TAG", "getExternalStorageState=" + Environment.getExternalStorageState());
        LogUtils.i("TAG", "getExternalStorageDirectory=" + Environment.getExternalStorageDirectory().getAbsolutePath());
        LogUtils.i("TAG", "" + getActivity().getBaseContext().getFilesDir().getAbsolutePath());
        LogUtils.i("TAG", "" + getActivity().getBaseContext().getExternalCacheDir().getAbsolutePath());
        LogUtils.i("TAG", "" + getActivity().getBaseContext().getExternalFilesDir("files"));
        //device_id=DDD4001708-05744&version=1.0.07&ads_support=31&timetoken=1520839861518
        String device_id = "DDD4001708-05744";
        String version = "1.0.07";
        String ads_support = "31";
        long timetoken = System.currentTimeMillis();
        mPresenter.requestConfig(device_id, version, ads_support, timetoken);
    }

    @Override
    public void onSucceed(DataResponse data) {
        LogUtils.i("TAG", "data===" + data);
        txt_context.setText(data.toString());
    }

    @Override
    public void onFail(String err) {
        LogUtils.i("TAG", "data===" + err);
    }
}
