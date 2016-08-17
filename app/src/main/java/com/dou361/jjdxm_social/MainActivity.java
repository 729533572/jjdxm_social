package com.dou361.jjdxm_social;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dou361.social.SocialSDK;
import com.dou361.social.model.SocialShareScene;
import com.dou361.social.model.SocialToken;
import com.dou361.social.model.SocialUser;
import com.dou361.social.otto.BusProvider;
import com.dou361.social.otto.SSOBusEvent;
import com.dou361.social.otto.ShareBusEvent;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.main_btn_sso)
    Button btnSso;
    @Bind(R.id.main_btn_share)
    Button btnShare;
    @Bind(R.id.main_btn_share_all)
    Button btnShareAll;
    @Bind(R.id.main_btn_sso_all)
    Button btnSsoAll;
    private final String TAG = this.getClass().getSimpleName();

    private SocialShareScene scene = new SocialShareScene(0, "ESSocialSDK", "ESSocialSDK:Android社会化授权登录和分享工具",
            "社交登录授权，分享SDK。支持微信、微博、QQ登录授权；微信好友、微信朋友圈、微博、QQ好友、QQ空间分享以及系统默认分享",
            "http://cdn.v2ex.co/gravatar/becb0d5c59469a34a54156caef738e90?s=73&d=retro", "http://blog.elbbbird.com/2015/12/15/hello-essocialsdk/");

    @OnClick({R.id.main_btn_share, R.id.main_btn_sso, R.id.main_btn_share_all, R.id.main_btn_sso_all})
    public void startActivity(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_btn_sso:
                intent.setClass(MainActivity.this, SsoActivity.class);
                startActivity(intent);
                break;

            case R.id.main_btn_share:
                intent.setClass(MainActivity.this, ShareActivity.class);
                startActivity(intent);
                break;

            case R.id.main_btn_share_all:
                SocialSDK.setDebugMode(true);
                SocialSDK.init("wx7ab13347c45dda06", "1633462674", "1104664609");
                SocialSDK.shareTo(MainActivity.this, scene);
                break;

            case R.id.main_btn_sso_all:
                SocialSDK.setDebugMode(true);
                SocialSDK.init("wx3ecc7ffe590fd845", "1b3f07fa99d82232d360c359f6504980", "1633462674", "1104664609");
                SocialSDK.oauth(MainActivity.this);
                break;
            case R.id.sso_all_btn_logout_all:
                SocialSDK.revoke(MainActivity.this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /*********************************************/
        BusProvider.getInstance().register(this);
        /*********************************************/

    }

    @Subscribe
    public void onShareResult(ShareBusEvent event) {
        switch (event.getType()) {
            case ShareBusEvent.TYPE_SUCCESS:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_SUCCESS " + event.getPlatform() + " " + event.getId());
                Toast.makeText(MainActivity.this, "ShareBusEvent.TYPE_SUCCESS", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.e(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + event.getPlatform() + " " + e.toString());
                Toast.makeText(MainActivity.this, "ShareBusEvent.TYPE_FAILURE", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL " + event.getPlatform() + " ");
                Toast.makeText(MainActivity.this, "ShareBusEvent.TYPE_CANCEL", Toast.LENGTH_SHORT).show();
                break;
        }
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
                Toast.makeText(MainActivity.this, "ShareBusEvent.TYPE_GET_USER \n\r" + user.toString(), Toast.LENGTH_SHORT).show();
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
