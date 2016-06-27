package com.dou361.social.share.wechat;

/**
 * Created by zhanghailong-ms on 2015/7/21.
 */
public interface IWXShareCallback {

    void onSuccess();

    void onCancel();

    void onFailure(Exception e);
}
