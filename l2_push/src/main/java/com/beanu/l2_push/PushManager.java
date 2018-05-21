package com.beanu.l2_push;

import android.content.Context;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * 推送管理(极光)
 */

public class PushManager {

    private static final String TAG = "PushManager";

    /**
     * 注册
     */
    public static void register(Context context, boolean debug) {
        if (context == null)
            return;
        JPushInterface.init(context);
        JPushInterface.setDebugMode(debug);
    }


    /**
     * 设置别名
     *
     * @param alias    每次调用设置有效的别名，覆盖之前的设置。
     *                 有效的别名组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。
     *                 限制：alias 命名长度限制为 40 字节。（判断长度需采用UTF-8编码）
     * @param sequence 用户自定义的操作序列号, 同操作结果一起返回，用来标识一次操作的唯一性。
     */
    public static void setAlias(final Context context, int sequence, String alias) {
        JPushInterface.setAlias(context, sequence, alias);
    }

    /**
     * 删除别名
     *
     * @param sequence 用户自定义的操作序列号, 同操作结果一起返回，用来标识一次操作的唯一性。
     */
    public static void deleteAlias(Context context, int sequence) {
        JPushInterface.deleteAlias(context, sequence);
    }


    /**
     * 设置标签
     *
     * @param sequence 用户自定义的操作序列号, 同操作结果一起返回，用来标识一次操作的唯一性。
     * @param tags     每次调用至少设置一个 tag，覆盖之前的设置，不是新增。
     *                 有效的标签组成：字母（区分大小写）、数字、下划线、汉字、特殊字符@!#$&*+=.|。
     *                 限制：每个 tag 命名长度限制为 40 字节，最多支持设置 1000 个 tag，且单次操作总长度不得超过5000字节。（判断长度需采用UTF-8编码）
     *                 单个设备最多支持设置 1000 个 tag。App 全局 tag 数量无限制。
     */
    public static void setTags(Context context, int sequence, Set<String> tags) {
        JPushInterface.setTags(context, sequence, tags);
    }

    /**
     * 添加标签
     */
    public static void addTags(Context context, int sequence, Set<String> tags) {
        JPushInterface.addTags(context, sequence, tags);
    }

    /**
     * 删除指定标签
     */
    public static void deleteTags(Context context, int sequence, Set<String> tags) {
        JPushInterface.deleteTags(context, sequence, tags);
    }

    /**
     * 清空标签
     */
    public static void cleanTags(Context context, int sequence) {
        JPushInterface.cleanTags(context, sequence);
    }


    /**
     * 停止推送
     *
     * @param context 应用的 ApplicationContext
     */
    public static void pause(Context context) {
        JPushInterface.stopPush(context);
    }


    /**
     * 恢复推送
     *
     * @param context 应用的 ApplicationContext
     */
    public static void resume(Context context) {
        JPushInterface.resumePush(context);
    }


    /**
     * 推送是否已停止
     *
     * @param context 应用的 ApplicationContext
     */
    public static boolean isStoped(Context context) {
        return JPushInterface.isPushStopped(context);
    }

}
