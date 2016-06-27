package com.dou361.jjdxm_social;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.dou361.jjdxm_social.R;

public class SocialActivity extends BaseActivity {

    @Bind(R.id.main_btn_sso)
    Button btnSso;
    @Bind(R.id.main_btn_share)
    Button btnShare;
    @Bind(R.id.main_btn_share_all)
    Button btnShareAll;
    @Bind(R.id.main_btn_sso_all)
    Button btnSsoAll;

    @OnClick({R.id.main_btn_share, R.id.main_btn_sso, R.id.main_btn_share_all, R.id.main_btn_sso_all})
    public void startActivity(View view) {

        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.main_btn_sso:
                intent.setClass(SocialActivity.this, SsoActivity.class);
                break;

            case R.id.main_btn_share:
                intent.setClass(SocialActivity.this, ShareActivity.class);
                break;

            case R.id.main_btn_share_all:
                intent.setClass(SocialActivity.this, ShareAllActivity.class);
                break;

            case R.id.main_btn_sso_all:
                intent.setClass(SocialActivity.this, SsoAllActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

}
