/*
 * Project: Laiwang
 * 
 * File Created at 2015-01-19
 * 
 * Copyright 2013 Alibaba.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Alibaba Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Alibaba.com.
 */
package com.wmj.taoxin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.alibaba.wukong.AuthConstants;
import com.alibaba.wukong.im.utils.AndTools;
//import com.wmj.lifecoterie.login.LoginActivity;
//import com.wmj.lifecoterie.util.AndTools;

/**
 * Description.
 *
 * @author zhongqian.wzq
 */
public class AuthReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (AuthConstants.Event.EVENT_AUTH_KICKOUT.equals(action)) {  // 其它终端登录
            clearLocalCache();
            //AndTools.showToast(context, R.string.kickout);
            goToLogin(context);
        }else if(AuthConstants.Event.EVENT_AUTH_LOGOUT.equals(action)){
            goToLogin(context);

        }
    }

    private void goToLogin(Context context) {
        /*
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(context.getPackageName(),LoginActivity.class.getName());
        context.startActivity(intent);
        */
    }

    /**
     * 用户登出，清除本地缓存数据
     */
    private void clearLocalCache() {
        // 可以在这里清理原登录帐号的内存和持久化数据
    }
}
