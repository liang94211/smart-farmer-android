package com.beanu.l3_login.mvp.presenter;

import com.beanu.arad.Arad;
import com.beanu.arad.utils.ConvertUtils;
import com.beanu.arad.utils.EncryptUtils;
import com.beanu.l2_shareutil.login.BaseUser;
import com.beanu.l3_common.model.bean.EventModel;
import com.beanu.l3_common.model.bean.User;
import com.beanu.l3_common.util.AppHolder;
import com.beanu.l3_common.util.Constants;
import com.beanu.l3_login.model.bean.PreLogin;
import com.beanu.l3_login.mvp.contract.LoginContract;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;


/**
 * Created by Beanu on 2017/02/13
 */

public class LoginPresenterImpl extends LoginContract.Presenter {

    private BaseUser mBaseUser;

    @Override
    public void login(final String account, final String password, final String loginType, String token, int sex, String icon, String nickName) {

        mModel.httpLogin(account, password, loginType, token, sex, icon, nickName).subscribe(new Observer<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxManage.add(d);
            }

            @Override
            public void onNext(@NonNull User user) {
                AppHolder.getInstance().setUser(user);

                //保存到本地
                if ("0".equals(loginType)) {
                    Arad.preferences.putString(Constants.P_ACCOUNT, account);
                    Arad.preferences.putString(Constants.P_PWD, EncryptUtils.encryptDES2HexString(ConvertUtils.hexString2Bytes(password), Constants.DES_KEY));
                }
                Arad.preferences.putString(Constants.P_LOGIN_TYPE, loginType);
                Arad.preferences.putString(Constants.P_User_Id, user.getId());
                Arad.preferences.putBoolean(Constants.P_ISFIRSTLOAD, false);
                Arad.preferences.flush();


                //通知登录成功
                Arad.bus.post(new EventModel.LoginEvent(user));

            }

            @Override
            public void onError(@NonNull Throwable e) {
                mView.loginFailed(e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.loginSuccess();

            }
        });
    }

    @Override
    public void preLogin(BaseUser baseUser, int loginType) {
        mBaseUser = baseUser;

        mModel.preLogin(baseUser.getOpenId(), loginType)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showProgress();
                    }
                })
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        mView.hideProgress();
                    }
                })
                .subscribe(new Observer<PreLogin>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PreLogin preLogin) {
                        mView.preLogin(mBaseUser, preLogin.getIsBind() == 1);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}