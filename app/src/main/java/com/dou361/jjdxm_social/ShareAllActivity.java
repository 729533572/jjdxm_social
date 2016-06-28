package com.dou361.jjdxm_social;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dou361.social.SocialSDK;
import com.dou361.social.model.SocialShareScene;
import com.dou361.social.otto.BusProvider;
import com.dou361.social.otto.ShareBusEvent;
import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dou361.jjdxm_social.R;

public class ShareAllActivity extends BaseActivity {

    private static final String TAG = "ShareAllActivity";

    private SocialShareScene scene = new SocialShareScene(0, "ESSocialSDK", "ESSocialSDK:Android社会化授权登录和分享工具",
            "社交登录授权，分享SDK。支持微信、微博、QQ登录授权；微信好友、微信朋友圈、微博、QQ好友、QQ空间分享以及系统默认分享",
            "http://cdn.v2ex.co/gravatar/becb0d5c59469a34a54156caef738e90?s=73&d=retro", "http://blog.elbbbird.com/2015/12/15/hello-essocialsdk/");

    @Bind(R.id.share_all_btn_share_all)
    Button btnShareAll;

    @OnClick(R.id.share_all_btn_share_all)
    public void share(View view) {
        SocialSDK.setDebugMode(true);
        SocialSDK.init("wx7ab13347c45dda06", "1633462674", "1104664609");
        SocialSDK.shareTo(ShareAllActivity.this, scene);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_all);
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
                Toast.makeText(ShareAllActivity.this, "ShareBusEvent.TYPE_SUCCESS", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.e(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + event.getPlatform() + " " + e.toString());
                Toast.makeText(ShareAllActivity.this, "ShareBusEvent.TYPE_FAILURE", Toast.LENGTH_SHORT).show();
                break;
            case ShareBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL " + event.getPlatform() + " ");
                Toast.makeText(ShareAllActivity.this, "ShareBusEvent.TYPE_CANCEL", Toast.LENGTH_SHORT).show();
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
