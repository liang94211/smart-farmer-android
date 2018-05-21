package com.beanu.l2_shareutil;

import android.app.Activity;

import com.beanu.l2_shareutil.share.ShareListener;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

/**
 * Created by shaohui on 2016/11/18.
 */

public class ShareUtil {

    private static SHARE_MEDIA[] PLATFORMS = new SHARE_MEDIA[]{SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA};


    public static void shareText(Activity activity, String text, ShareListener listener) {
        shareText(activity, text, PLATFORMS, listener);
    }

    public static void shareText(Activity activity, String text, SHARE_MEDIA[] platforms, ShareListener listener) {
        new ShareAction(activity)
                .withText(text)
                .setDisplayList(platforms)
                .setCallback(listener)
                .share();
    }

    public static void shareImage(Activity activity, SHARE_MEDIA[] platforms, final String url, ShareListener listener) {
        UMImage image = new UMImage(activity, url);//网络图片
//        UMImage thumb =  new UMImage(activity, R.drawable.thumb);
//        image.setThumb(thumb);
        new ShareAction(activity)
                .setDisplayList(platforms)
                .withMedia(image)
                .share();
    }

    public static void shareImage(Activity activity, final String url, ShareListener listener) {
        shareImage(activity, PLATFORMS, url, listener);
    }


    public static void shareImage(Activity activity, SHARE_MEDIA[] platforms, final int imgRes, ShareListener listener) {
        UMImage image = new UMImage(activity, imgRes);//网络图片
//        UMImage thumb =  new UMImage(activity, R.drawable.thumb);
//        image.setThumb(thumb);
        new ShareAction(activity)
                .setDisplayList(platforms)
                .withMedia(image)
                .share();
    }

    public static void shareImage(Activity activity, final int imgRes, ShareListener listener) {
        shareImage(activity, PLATFORMS, imgRes, listener);
    }

    public static void shareWeb(Activity activity, SHARE_MEDIA[] platforms, String url, String title, String description, int imgRes, ShareListener listener) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(new UMImage(activity, imgRes));  //缩略图
        web.setDescription(description);//描述

        new ShareAction(activity)
                .setDisplayList(platforms)
                .withMedia(web)
                .share();
    }

    public static void shareWeb(Activity activity, String url, String title, String description, int imgRes, ShareListener listener) {
        shareWeb(activity, PLATFORMS, url, title, description, imgRes, listener);
    }

}
