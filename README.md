# [jjdxm_social][project] #

### Copyright notice ###

我在网上写的文章、项目都可以转载，但请注明出处，这是我唯一的要求。当然纯我个人原创的成果被转载了，不注明出处也是没有关系的，但是由我转载或者借鉴了别人的成果的请注明他人的出处，算是对前辈们的一种尊重吧！

虽然我支持写禁止转载的作者，这是他们的成果，他们有这个权利，但我不觉得强行扭转用户习惯会有一个很好的结果。纯属个人的观点，没有特别的意思。可能我是一个版权意识很差的人吧，所以以前用了前辈们的文章、项目有很多都没有注明出处，实在是抱歉！有想起或看到的我都会逐一补回去。

从一开始，就没指望从我写的文章、项目上获得什么回报，一方面是为了自己以后能够快速的回忆起曾经做过的事情，避免重复造轮子做无意义的事，另一方面是为了锻炼下写文档、文字组织的能力和经验。如果在方便自己的同时，对你们也有很大帮助，自然是求之不得的事了。要是有人转载或使用了我的东西觉得有帮助想要打赏给我，多少都行哈，心里却很开心，被人认可总归是件令人愉悦的事情。

站在了前辈们的肩膀上，才能走得更远视野更广。前辈们写的文章、项目给我带来了很多知识和帮助，我没有理由不去努力，没有理由不让自己成长的更好。写出好的东西于人于己都是好的，但是由于本人自身视野和能力水平有限，错误或者不好的望多多指点交流。

项目中如有不同程度的参考借鉴前辈们的文章、项目会在下面注明出处的，纯属为了个人以后开发工作或者文档能力的方便。如有侵犯到您的合法权益，对您造成了困惑，请联系协商解决，望多多谅解哈！若您也有共同的兴趣交流技术上的问题加入交流群QQ： 548545202

感谢作者[elbbbird][author]，本项目是基于[ESSocialSDK][url]项目

## Introduction ##

#### 社会化功能：授权登录、分享等主要功能。支持微信、微博、QQ登录授权、微信好友、微信朋友圈、微博、QQ好友、QQ空间分享以及系统默认分享。当前项目将微信、QQ和微博三个不同平台提供的SDK分别打包到jCenter上面，主要是为了避免使用本类库而造成和三方平台微信支付等其他功能的SDK架包发生冲突。 ####

## Screenshots ##

<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon01.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon02.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon01.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon02.png" width="300">
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon03.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon04.png" width="300">
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon05.png" width="300">

## Download ##

[demo apk下载][downapk]

Download or grab via Maven:

	<dependency>
	  <groupId>com.dou361.social</groupId>
	  <artifactId>jjdxm-social</artifactId>
	  <version>x.x.x</version>
	</dependency>

or Gradle:

	compile 'com.dou361.social:jjdxm-social:x.x.x'

历史版本

	compile 'com.dou361.social:jjdxm-social:1.0.0'

jjdxm-social requires at minimum Java 15 or Android 4.0.

[架包的打包引用以及冲突解决][jaraar]

## Proguard ##

根据你的混淆器配置和使用，您可能需要在你的proguard文件内配置以下内容：

	##微信
	-keep class com.tencent.mm.sdk.** {*;}

	##微博
	-keep public class com.sina.weibo.** {*;}
	-keep public class com.sina.sso.** {*;}

	##otto
	-keepattributes *Annotation*
	-keepclassmembers class ** {
	    @com.squareup.otto.Subscribe public *;
	    @com.squareup.otto.Produce public *;
	}


[AndroidStudio代码混淆注意的问题][minify]

## Get Started ##


### 1.设置Debug模式 ###

	SocialSDK.setDebugMode(true); //默认false

### 2.项目配置 ###
配置微博后台回调地址   
SDK的默认回调地址为[http://www.sina.com](http://www.sina.com)，需要在微博后台配置，否则会提示回调地址错误。   
如果在SocialSDK.init()方法自定义了回调地址，需要在后台配置为相应地址。

WXEntryActivity   
创建包名：

	package_name.wxapi
  
在该包名下创建类WXEntryActivity继承自WXCallbackActivity   


	package com.encore.actionnow.wxapi;
	public class WXEntryActivity extends WXCallbackActivity {
	
	}


	<activity
	    android:name=".wxapi.WXEntryActivity"
	    android:configChanges="keyboardHidden|orientation|screenSize"
	    android:exported="true"
	    android:screenOrientation="portrait"
	    android:theme="@android:style/Theme.Translucent.NoTitleBar" />
	<activity
	    android:name="com.tencent.tauth.AuthActivity"
	    android:launchMode="singleTask"
	    android:noHistory="true">
	    <intent-filter>
	        <action android:name="android.intent.action.VIEW" />
	
	        <category android:name="android.intent.category.DEFAULT" />
	        <category android:name="android.intent.category.BROWSABLE" />
	
	        <data android:scheme="tencentXXXXXXXXX" />
	    </intent-filter>
	</activity>
以上配置中的`XXXXXXXXX`换成app_id.

### 3.注册权限 ###

	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

### 4.一键分享功能（微博，微信，朋友圈，QQ，QQ空间） ###

SDK中SocialShareScene的定义

	/**
	 * 社会化分享数据类
	 */
	public class SocialShareScene implements Serializable {
	
	    public static final int SHARE_TYPE_DEFAULT = 0;
	    public static final int SHARE_TYPE_WEIBO = 1;
	    public static final int SHARE_TYPE_WECHAT = 2;
	    public static final int SHARE_TYPE_WECHAT_TIMELINE = 3;
	    public static final int SHARE_TYPE_QQ = 4;
	    public static final int SHARE_TYPE_QZONE = 5;
	
	    /**
	     * @param id        分享唯一标识符，可随意指定，会在分享结果ShareBusEvent中返回
	     * @param appName   分享到QQ时需要指定，会在分享弹窗中显示该字段
	     * @param type      分享类型
	     * @param title     标题
	     * @param desc      简短描述
	     * @param thumbnail 缩略图网址
	     * @param url       WEB网址
	     */
	    public SocialShareScene(int id, String appName, int type, String title, String desc, String thumbnail, String url) {
	        this.id = id;
	        this.appName = appName;
	        this.type = type;
	        this.title = title;
	        this.desc = desc;
	        this.thumbnail = thumbnail;
	        this.url = url;
	    }
	
	    public SocialShareScene(int id, String appName, String title, String desc, String thumbnail, String url) {
	    ....
	}

一键分享需要调用第二个构造函数，type类型在SDK内部自动指定

### 分享结果回调


	@Subscribe
	public void onShareResult(ShareBusEvent event) {
	    switch (event.getType()) {
	        case ShareBusEvent.TYPE_SUCCESS:
	            Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_SUCCESS " + event.getId());
	            break;
	        case ShareBusEvent.TYPE_FAILURE:
	            Exception e = event.getException();
	            Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + e.toString());
	            break;
	        case ShareBusEvent.TYPE_CANCEL:
	            Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL");
	            break;
	    }
	}

分享

	SocialSDK.setDebugMode(true);
	SocialSDK.init("wechat_app_id", "weibo_app_id", "qq_app_id");
	SocialSDK.shareTo(context, scene);

### 5.一键登录授权功能（微博，微信，QQ） ###



授权结果回调   
SDK使用了[Otto](http://square.github.io/otto/)作为事件库，用以组件通信。
在调用SocialSDK.oauth()接口Activity的onCreate()`方法内添加   


	BusProvider.getInstance().register(this);


在该Activity的onDestroy()方法添加   

	@Override
	protected void onDestroy() {
	    BusProvider.getInstance().unregister(this);
	    super.onDestroy();
	}

添加回调接口   

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

授权oauth

	SocialSDK.init("wechat_app_id", "wechat_app_secret", "weibo_app_id", "qq_app_id");
	SocialSDK.oauth(context);


移除授权

	SocialSDK.revoke(context);

### 6.按照平台去依赖SDK，在项目中主要将QQ、微信、微博三个平台提供的SDK打包到jCenter上面了，通过以下的方式进行的依赖的 ###

	compile 'tencent:jjdxm-tencent:1.0.0' 对应的架包open_sdk_r5756_lite.jar
	compile 'winchat:jjdxm-winchat:1.0.0' 对应版本 libammsdk.jar
	compile 'weibo:jjdxm-weibo:1.0.0' 对应的微博weiboSDKCore_3.1.4.jar

QQ: [jjdxm-tencent](https://github.com/jjdxmashl/jjdxm_tencent)

微信: [jjdxm-winchat](https://github.com/jjdxmashl/jjdxm_winchat)

微博: [jjdxm-weibo](https://github.com/jjdxmashl/jjdxm_weibo)


## More Actions ##

## ChangeLog ##

## About Author ##

#### 个人网站:[http://www.dou361.com][web] ####
#### GitHub:[jjdxmashl][github] ####
#### QQ:316988670 ####
#### 交流QQ群:548545202 ####


## License ##

    Copyright (C) dou361, The Framework Open Source Project
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
     	http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## (Frequently Asked Questions)FAQ ##
## Bugs Report and Help ##

If you find any bug when using project, please report [here][issues]. Thanks for helping us building a better one.



[web]:http://www.dou361.com
[github]:https://github.com/jjdxmashl/
[project]:https://github.com/jjdxmashl/jjdxm_social/
[issues]:https://github.com/jjdxmashl/jjdxm_social/issues/new
[downapk]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/apk/app-debug.apk
[lastaar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/release/jjdxm-social-1.0.0.aar
[lastjar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/release/jjdxm-social-1.0.0.jar
[icon01]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon01.png
[icon02]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_social/master/screenshots/icon02.png
[jaraar]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/架包的打包引用以及冲突解决.md
[minify]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/AndroidStudio代码混淆注意的问题.md
[author]:https://github.com/elbbbird
[url]:https://github.com/elbbbird/ESSocialSDK
