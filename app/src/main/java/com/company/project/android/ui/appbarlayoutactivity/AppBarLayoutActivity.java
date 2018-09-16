package com.company.project.android.ui.appbarlayoutactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.company.project.android.R;
import com.company.project.android.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.appbarLayoutactivity
 * @class describe
 * @time 2018/7/20 10:31
 * @change
 * @class describe 应用程序栏布局
 */

public class AppBarLayoutActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.iv_head)
    ImageView mHeadImage;
    @BindView(R.id.subscription_title)
    TextView mTitle;
    @BindView(R.id.test)
    TextView test;
    @BindView(R.id.tv_search)
    TextView tvSearch;


    private float screenW;
    private float toolbarHeight;
    private float initHeight;
    private float mSelfHeight = 0;  //用以判断是否得到正确的宽高值
    private float mHeadImageScale;//图片
    private float mHeadImageScaleY;//图片
    private float mHeadImageScaleX;//图片
    private float mTitleScale;      //标题缩放值
    private float mTestScaleY;      //测试按钮y轴缩放值
    private float mTestScaleX;      //测试按钮x轴缩放值
    private float mHeadImgScale;    //头像缩放值

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appbarlayout_activity);
        ButterKnife.bind(this);

        screenW = getResources().getDisplayMetrics().widthPixels;
        toolbarHeight = getResources().getDimension(R.dimen.tool_bar_height);
        initHeight = getResources().getDimension(R.dimen.app_bar_height);
        /**
         *   移动效果值／最终效果值 =  移动距离／ 能移动总距离（确定）
         */
        mAppBar.addOnOffsetChangedListener(this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {//垂直偏移
        if (mSelfHeight == 0) {
            //获取标题高度
            mSelfHeight = mTitle.getHeight();
            //得到图片的高度差
            float distanceImage = mHeadImage.getTop() - (toolbarHeight - mHeadImage.getHeight()) / 2.0f;
            //得到标题的高度差
            float distanceTitle = mTitle.getTop() - (toolbarHeight - mTitle.getHeight()) / 2.0f;
            //得到测试按钮的高度差
            float distanceTest = test.getY() - (toolbarHeight - test.getHeight()) / 2.0f;
            //得到图片的高度差
            float distanceHeadImg = mHeadImage.getY() - (toolbarHeight - mHeadImage.getHeight()) / 2.0f;
            //得到测试按钮的水平差值  屏幕宽度一半 - 按钮宽度一半
            float distanceSubscribeX = screenW / 2.0f - (test.getWidth() / 2.0f);

            //得到高度差缩放比值  高度差／能滑动总长度 以此类推
            mHeadImageScale = distanceImage / (initHeight - toolbarHeight);
            mTitleScale = distanceTitle / (initHeight - toolbarHeight);
            mHeadImageScaleY = distanceTest / (initHeight - toolbarHeight);
            mTestScaleY = distanceTest / (initHeight - toolbarHeight);
            mHeadImgScale = distanceHeadImg / (initHeight - toolbarHeight);
            mTestScaleX = distanceSubscribeX / (initHeight - toolbarHeight);
            mHeadImageScaleX = distanceSubscribeX / (initHeight - toolbarHeight);
        }
        //得到文本框、头像缩放值 不透明 ->透明  文本框x跟y缩放
        float scale = 1.0f - (-verticalOffset) / (initHeight - toolbarHeight);
        LogUtils.i("EEE", "scale====" + scale + "  mTestScaleY=" + mTestScaleY + "    mTestScaleX=" + mTestScaleX + "  verticalOffset=" + verticalOffset + "  mHeadImgScale=" + mHeadImgScale);
        tvSearch.setScaleX(scale);
        tvSearch.setScaleY(scale);
        tvSearch.setAlpha(scale);
         /*  mHeadImage.setScaleX(scale);
             mHeadImage.setScaleY(scale);*/

        mHeadImage.setTranslationY(-mHeadImageScaleY * verticalOffset);
        mHeadImage.setTranslationX(mHeadImageScaleX * verticalOffset);

        //设置头像y轴平移
        mHeadImage.setTranslationY(mHeadImgScale * verticalOffset);
        //设置标题y轴平移
        mTitle.setTranslationY(mTitleScale * verticalOffset);
        //设置测试按钮x跟y平移
        test.setTranslationY(mTestScaleY * verticalOffset);
        test.setTranslationX(-mTestScaleX * verticalOffset);
    }
}
