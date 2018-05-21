package com.beanu.l2_shareutil;


import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class ShareManager {


    public static void init(Context context) {
        UMConfigure.init(context, "5afba177a40fa342120000ce", "umeng", UMConfigure.DEVICE_TYPE_PHONE, null);
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
}
