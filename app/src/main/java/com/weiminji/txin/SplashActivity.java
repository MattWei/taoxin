package com.weiminji.txin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.wukong.auth.AuthService;
import com.wmj.lifecoterie.login.LoginActivity;

/**
 * @author hugozhu on 9/23/14.
 */
public class SplashActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (!AuthService.getInstance().isLogin()) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}
