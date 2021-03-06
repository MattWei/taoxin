package com.wmj.taoxin;

import android.app.Application;
import android.app.NotificationManager;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.alibaba.wukong.AuthConstants;
import com.alibaba.wukong.Callback;
import com.alibaba.wukong.WKConstants;
import com.alibaba.wukong.WKManager;
import com.alibaba.wukong.auth.AuthInfo;
import com.alibaba.wukong.auth.AuthService;
import com.alibaba.wukong.im.Conversation;
import com.alibaba.wukong.im.ConversationService;
import com.alibaba.wukong.im.IMEngine;
import com.alibaba.wukong.im.Message;
import com.alibaba.wukong.im.MessageListener;
import com.alibaba.wukong.im.MessageService;

import java.util.List;


/**
 * 在Application中初始化IMEngine
 * Created by hugozhu on 9/17/14.
 */
public class TXinApplication extends Application {
    private static final String TAG = "TXinApplication";

    private static TXinApplication instance;

    private AuthReceiver mAuthReceiver;

    private NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initWukongIM();

        initIMUtil();

        registerMessageListener();
        registerAuthReceiver();
        //RouteRegister.bootwrapped();
    }

    public static TXinApplication getInstance(){
        return instance;
    }

    /**
     * 初始化 Wukong IM
     */
    private void initWukongIM(){
        // 只有用户profile（如：nickname、gender、mobile等）信息放在悟空时，才需要设置成true，默认false
        IMEngine.setUserAvailable(true);
        // 设置为上海沙箱环境
//        IMEngine.setEnvironment(WKConstants.Environment.ONLINE);
        WKManager.setEnvironment(WKConstants.Environment.SANDBOX);
        //初始化IMEngine
        IMEngine.launch(this);
        //自动登录上一次的账户
        AuthInfo authInfo = AuthService.getInstance().latestAuthInfo();
        if(authInfo != null){
            AuthService.getInstance().autoLogin(authInfo.getOpenId());
        }
    }

    /**
     * 初始化IM工具包
     */
    private void initIMUtil(){
        /*
        MessageSenderImpl.getInstance().init(this);
        AvatarMagicianImpl.getInstance().init(this);
        AvatarMagicianImpl.getInstance().setAvatarShape(AvatarMagician.CIRCLE_AVATAR_SHAPE);
    */
    }

    /**
     * 注册账号异常的广播监听
     */
    public void registerAuthReceiver() {
        if (mAuthReceiver == null) {
            mAuthReceiver = new AuthReceiver();
        }
        IntentFilter accountFilter = new IntentFilter();
        accountFilter.addAction(AuthConstants.Event.EVENT_AUTH_LOGOUT);
        accountFilter.addAction(AuthConstants.Event.EVENT_AUTH_KICKOUT);
        accountFilter.addAction(AuthConstants.Event.EVENT_AUTH_LOGIN);
        LocalBroadcastManager.getInstance(this).registerReceiver(mAuthReceiver, accountFilter);
    }

    /**
     * 注册接收消息监听器，用于更改消息未读数
     * 放在这里的原因:杀掉进程重启的时候未进入MainActivity就接收到消息了，
     * 所以如果放在主页处理会出现未读消息数异常
     */
    private void registerMessageListener(){
        /*
        IMEngine.getIMService(MessageService.class).addMessageListener(new MessageListener() {
            @Override
            public void onAdded(List<Message> list, DataType dataType) {
                String currentChatCid = ChatWindowManager.getInstance().getCurrentChatCid();
                for (Message msg : list) {
                    if(msg.senderId() == DemoUtil.currentOpenId()){
                        continue;   //发送人是自己的时候，未读数不增加
                    }

                    Conversation conversation = msg.conversation();
                    if(conversation == null){
                        continue;
                    }

                    //如果消息不属于当前会话则将累加未读数
                    if (currentChatCid == null||!currentChatCid.equals(conversation.conversationId())) {
                        msg.conversation().addUnreadCount(1);
                        newMessageNotify();
                        if (msg.isAt()) {
                            conversation.updateAtMeStatus(true);
                        }
                    }
                }
            }

            @Override
            public void onRemoved(List<Message> list) {
            }

            @Override
            public void onChanged(List<Message> list) {
            }
        });
        */
    }

    private void newMessageNotify(){
        /*
        if(DemoUtil.isAppForeground(LifeConterieApplication.getInstance())) {
            return;
        }

        IMEngine.getIMService(ConversationService.class).getTotalUnreadCount(
            new Callback<Integer>() {
                @Override
                public void onSuccess(Integer unReadCount) {
                    DemoUtil.sendNotification(unReadCount);
                }

                @Override
                public void onException(String code, String reason) {

                }

                @Override
                public void onProgress(Integer data, int progress) {

                }
            }, false);
            */
    }

    @Override
    public void onTerminate() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mAuthReceiver);
        super.onTerminate();
    }
}