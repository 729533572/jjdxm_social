package com.dou361.jjdxm_social;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dou361.social.SocialSDK;
import com.dou361.social.model.SocialToken;
import com.dou361.social.model.SocialUser;
import com.dou361.social.otto.BusProvider;
import com.dou361.social.otto.SSOBusEvent;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dou361.jjdxm_social.R;

public class SsoAllActivity extends BaseActivity {

    private static final String TAG = "SsoAllActivity";

    @Bind(R.id.sso_all_btn_login_all)
    Button btnLoginAll;
    @Bind(R.id.sso_all_btn_logout_all)
    Button btnLogoutAll;

    @OnClick(R.id.sso_all_btn_login_all)
    public void login(View view) {
        SocialSDK.setDebugMode(true);
        SocialSDK.init("wx3ecc7ffe590fd845", "1b3f07fa99d82232d360c359f6504980", "1633462674", "1104664609");
        SocialSDK.oauth(SsoAllActivity.this);
    }

    @OnClick(R.id.sso_all_btn_logout_all)
    public void logout(View view) {
        SocialSDK.revoke(SsoAllActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sso_all);
        ButterKnife.bind(this);


        /**************************************************/
        BusProvider.getInstance().register(this);
        /**************************************************/
    }

    @Subscribe
    public void onOauthResult(SSOBusEvent event) {
        switch (event.getType()) {
            case SSOBusEvent.TYPE_GET_TOKEN:
                SocialToken token = event.getToken();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_TOKEN " + token.toString());
                break;
            case SSOBusEvent.TYPE_GET_USER:
                SocialUser user = event.getUser();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_USER " + user.toString());
                Toast.makeText(SsoAllActivity.this, "ShareBusEvent.TYPE_GET_USER \n\r" + user.toString(), Toast.LENGTH_SHORT).show();
                break;
            case SSOBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_FAILURE " + e.toString());
                break;
            case SSOBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_CANCEL");
                break;
        }
    }


    @Override
    protected void onDestroy() {
        /*********************************************/
        BusProvider.getInstance().unregister(this);
        /*********************************************/
        super.onDestroy();
    }
}
