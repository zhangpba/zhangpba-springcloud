package com.data.chain.wechat.common;

import com.data.chain.wechat.entity.WechatUserDetail;

public class CurrentThreadLocal {
    private static ThreadLocal<WechatUserDetail> userThread = new ThreadLocal<>();

    public static void set(WechatUserDetail user) {
        userThread.set(user);
    }

    public static WechatUserDetail currentUser() {
        return userThread.get();
    }

    //防止内存泄漏
    public static void remove() {
        userThread.remove();
    }
}