package com.company.project.android.ui.main;

import com.company.project.android.api.ApiEngine;
import com.company.project.android.bean.Gank;

import org.json.JSONObject;
import okhttp3.RequestBody;
import io.reactivex.Observable;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.main
 * @class describe
 * @time 2018/2/8 16:56
 * @change
 * @class describe
 */

public class MainModel implements MainContract.Model {

    @Override
    public String loginSuccess() {
        return "成功了";
    }

    @Override
    public Observable<Gank> getGank() {
        return ApiEngine.getInstance().getApiService()
                        .getGank("1");

    }

    @Override
    public Observable<JSONObject> accessToken(RequestBody requestBody) {
        return ApiEngine.getInstance().getApiService().accessToken(  requestBody);
    }
}
