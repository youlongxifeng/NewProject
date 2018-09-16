package com.company.project.android.ui.main;

import com.company.project.android.bean.Gank;
import com.company.project.android.mvp.BaseModel;
import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.main
 * @class describe
 * @time 2018/2/8 16:56
 * @change
 * @class describe
 */

public interface MainContract {
    interface View extends BaseView {

        void setLogin(String active);

        void showDialog();

        void onSucceed(Gank data);

        void onFail(String err);

        void hideDialog();
    }

    interface Model extends BaseModel {

        String loginSuccess();//Success, failure
        Observable<Gank> getGank();

        Observable<JSONObject> accessToken(RequestBody requestBody);

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract String login(Map<String, String> maps);

        public abstract void getGank();

        public abstract void  accessToken(String app_id,String secret_key);

    }
}
