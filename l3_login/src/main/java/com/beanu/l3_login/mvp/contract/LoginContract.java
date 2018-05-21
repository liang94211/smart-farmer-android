package com.beanu.l3_login.mvp.contract;

import com.beanu.arad.base.BaseModel;
import com.beanu.arad.base.BasePresenter;
import com.beanu.arad.base.BaseView;
import com.beanu.l2_shareutil.login.BaseUser;
import com.beanu.l3_common.model.bean.User;
import com.beanu.l3_login.model.bean.PreLogin;

import io.reactivex.Observable;


/**
 * 登录-契约类
 * Created by Beanu on 2017/2/13.
 */

public interface LoginContract {


    public interface View extends BaseView {

        void loginSuccess();

        void loginFailed(String error);

        void preLogin(BaseUser baseUser, boolean needBind);

    }

    public abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String account, String password, String loginType, String token, int sex, String icon, String nickName);

        public abstract void preLogin(BaseUser baseUser, int loginType);
    }

    public interface Model extends BaseModel {
        Observable<User> httpLogin(String account, String password, String loginType, String token, int sex, String icon, String nickName);

        Observable<PreLogin> preLogin(String token, int loginType);
    }


}