package com.company.project.android.ui.fragment.adsfragment;

import com.company.project.android.bean.DataResponse;
import com.company.project.android.bean.User;
import com.company.project.android.mvp.BaseModel;
import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;

import java.io.File;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.fragment.adsfragment
 * @class describe
 * @time 2018/3/12 17:37
 * @change
 * @class describe
 */

public interface AdsContract {
    interface View extends BaseView {

        /**
         * 数据返回成功
         *
         * @param data
         */
        void onSucceed(DataResponse data);

        /**
         * 数据返回失败
         *
         * @param err
         */
        void onFail(String err);


    }

    interface Model extends BaseModel {


        Observable<DataResponse> getConfigInfo(String device_id, String version, String ads_support, long timetoken);

        Observable<User> uploadFile(File file, String fileType, Map<String, Object> map);

        Observable<User> uploadFilesWithParts(File file, String fileType, Map<String, Object> map);

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 此接口提供给服务组获取信息使用,门禁机通过请求服务组获取信息
         */
        public abstract void requestConfig(String device_id, String version, String ads_support, long timetoken);


    }
}
