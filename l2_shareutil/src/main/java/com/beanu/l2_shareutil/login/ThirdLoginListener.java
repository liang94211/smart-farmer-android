package com.beanu.l2_shareutil.login;


import com.umeng.socialize.bean.SHARE_MEDIA;

public abstract class ThirdLoginListener {

    public abstract void loginStart(int platform);

    public abstract void loginSuccess(BaseUser result, int platform);

    public abstract void loginFailure(Throwable e);

    public abstract void loginCancel();

    public abstract int getLoginPlatform(SHARE_MEDIA share_media);

}
