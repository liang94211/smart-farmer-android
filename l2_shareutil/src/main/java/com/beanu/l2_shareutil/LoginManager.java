package com.beanu.l2_shareutil;

import android.app.Activity;
import android.content.Context;

import com.beanu.l2_shareutil.login.BaseUser;
import com.beanu.l2_shareutil.login.ThirdLoginListener;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


/**
 * Created by shaohui on 2016/12/3.
 */

public class LoginManager {

    private UMShareAPI mUMShareAPI;
    private ThirdLoginListener mLoginListener;

    private UMAuthListener mAuthListener = new UMAuthListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {
            if (mLoginListener != null) {
                mLoginListener.loginStart(mLoginListener.getLoginPlatform(share_media));
            }
        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            if (mLoginListener != null) {
                mLoginListener.loginSuccess(BaseUser.getBaseUser(map), mLoginListener.getLoginPlatform(share_media));
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            if (mLoginListener != null) {
                mLoginListener.loginFailure(throwable);
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            if (mLoginListener != null) {
                mLoginListener.loginCancel();
            }
        }
    };

    public LoginManager(Context context, ThirdLoginListener loginListener) {
        this.mUMShareAPI = UMShareAPI.get(context);
        mLoginListener = loginListener;
    }

    public void login(Activity activity, SHARE_MEDIA share_media) {
        mUMShareAPI.getPlatformInfo(activity, share_media, mAuthListener);
    }

    public void loginWithWechat(Activity activity) {
        login(activity, SHARE_MEDIA.WEIXIN);
    }

    public void loginWithQQ(Activity activity) {
        login(activity, SHARE_MEDIA.QQ);
    }

    public void loginWithSina(Activity activity) {
        login(activity, SHARE_MEDIA.SINA);
    }

}
