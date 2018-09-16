package com.company.project.android.ui.fragment.signin;

import com.company.project.android.mvp.BaseModel;
import com.company.project.android.mvp.BasePresenter;
import com.company.project.android.mvp.BaseView;

/**
 * Created by xiexie on 2018/9/16.
 */

public interface SigninContract {
    interface View extends BaseView {

    }

    interface Model extends BaseModel {



    }

    abstract class Presenter extends BasePresenter<View, Model> {



    }
}
