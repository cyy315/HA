package com.cyy.assistant.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.cyy.assistant.R;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 * Created by umeng on 15/9/14.
 */
public class ShareActivity extends Activity{
    private CheckBox cb;

    public void onClick(View view) {
        UMImage image = new UMImage(ShareActivity.this, "http://www.umeng.com/images/pic/social/integrated_3.png");
        UMusic music = new UMusic("http://music.huoxing.com/upload/20130330/1364651263157_1085.mp3");
        music.setTitle("This is music title");
        music.setThumb(new UMImage(ShareActivity.this, "http://www.umeng.com/images/pic/social/chart_1.png"));
        UMVideo video = new UMVideo("http://video.sina.com.cn/p/sports/cba/v/2013-10-22/144463050817.html");
        String url = "http://www.umeng.com";

        switch (view.getId()){
           case R.id.app_open_share:
               /**shareboard  need the platform all you want and callbacklistener,then open it**/
               new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                       .withText("来自友盟分享面板")
                       .withMedia(image)
                       .setCallback(umShareListener)
                       .open();
               break;
            case R.id.app_open_share_custom:
                new ShareAction(this).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .addButton("umeng_sharebutton_custom","umeng_sharebutton_custom","info_icon_1","info_icon_1")
                        .setShareboardclickCallback(new ShareBoardlistener() {
                            @Override
                            public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                                if (snsPlatform.mShowWord.equals("umeng_sharebutton_custom")){
                                    Toast.makeText(ShareActivity.this,"自定义按�?",Toast.LENGTH_LONG).show();
                                }else {
                                    new ShareAction(ShareActivity.this).withText("来自友盟自定义分享面�?")
                                            .setPlatform(share_media)
                                            .setCallback(umShareListener)
                                            .share();
                                }
                            }
                        }).open();
                break;
           case R.id.app_share_sina:
               /** shareaction need setplatform , callbacklistener,and content(text,image).then share it **/
               new ShareAction(this).setPlatform(SHARE_MEDIA.SINA).setCallback(umShareListener)
                       .withText("umeng")
                       .withMedia(image)
//                       .withExtra(new UMImage(ShareActivity.this,R.drawable.ic_launcher))
                       .withTargetUrl("http://dev.umeng.com")
                        .share();
               break;
           case R.id.app_share_douban:
               new ShareAction(this).setPlatform(SHARE_MEDIA.DOUBAN).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(music)
                       .share();
               break;

           case R.id.app_share_email:
               new ShareAction(this).setPlatform(SHARE_MEDIA.EMAIL).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(music)
                       .withTitle("dddddd")
                       .share();
               break;
           case R.id.app_share_wx:

               new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN).setCallback(umShareListener)
                        .withMedia(image)
                       //.withMedia(new UMEmoji(ShareActivity.this,"http://img.newyx.net/news_img/201306/20/1371714170_1812223777.gif"))
                        .withText("hello umeng")
//                        .withTargetUrl("http://dev.umeng.com")
                       .share();
               break;
           case R.id.app_share_wx_circle:
               new ShareAction(this).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).setCallback(umShareListener)
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_sms:
               new ShareAction(this).setPlatform(SHARE_MEDIA.SMS).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_qq:
               new ShareAction(this).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withTargetUrl(url)
                       .withTitle("qqshare")
                       .share();
               break;
           case R.id.app_share_qzone:
               new ShareAction(this).setPlatform(SHARE_MEDIA.QZONE).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_yixin:
               new ShareAction(this).setPlatform(SHARE_MEDIA.YIXIN).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_yixin_circle:
               new ShareAction(this).setPlatform(SHARE_MEDIA.YIXIN_CIRCLE).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_ynote:
               new ShareAction(this).setPlatform(SHARE_MEDIA.YNOTE).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_evernote:
               new ShareAction(this).setPlatform(SHARE_MEDIA.EVERNOTE).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_facebook:
               new ShareAction(this).setPlatform(SHARE_MEDIA.FACEBOOK).setCallback(umShareListener)
                       .withTitle("This is title")
                       .withText("This is text")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_laiwang:
               new ShareAction(this).setPlatform(SHARE_MEDIA.LAIWANG).setCallback(umShareListener)
                       .withText("hello umeng")
                      .withMedia(image)
                       .share();
               break;
           case R.id.app_share_line:
               new ShareAction(this).setPlatform(SHARE_MEDIA.LINE).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_linkedin:
               new ShareAction(this).setPlatform(SHARE_MEDIA.LINKEDIN).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withTitle("this is cool")
                       .withTargetUrl("https://www.baidu.com/")
                       .share();
               break;
           case R.id.app_share_twitter:
               new ShareAction(this).setPlatform(SHARE_MEDIA.TWITTER).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_tencent:
               new ShareAction(this).setPlatform(SHARE_MEDIA.TENCENT).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_kakao:
               new ShareAction(this).setPlatform(SHARE_MEDIA.KAKAO).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
               break;
           case R.id.app_share_alipay:
               new ShareAction(this).setPlatform(SHARE_MEDIA.ALIPAY).setCallback(umShareListener)
                       .withText("hello umeng")
                       .withMedia(image)
                       .share();
//               new ShareAction(this).setPlatform(SHARE_MEDIA.RENREN).setCallback(umShareListener)
//                       .withText("hello umeng")
//                       .withMedia(image)
//                       .share();
               break;
       }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_share);
        /** need not init ,but must config App.java**/

        cb = (CheckBox) findViewById(R.id.checkBox_close_editor);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Config.OpenEditor = true;
                } else {
                    Config.OpenEditor = false;//open editpage
                }
            }
        });
    }

    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            Toast.makeText(ShareActivity.this, platform + " 分享成功�?", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ShareActivity.this,platform + " 分享失败�?", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ShareActivity.this,platform + " 分享取消�?", Toast.LENGTH_SHORT).show();
        }
    };

    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {

        @Override
        public void onclick(SnsPlatform snsPlatform,SHARE_MEDIA share_media) {
            new ShareAction(ShareActivity.this).setPlatform(share_media).setCallback(umShareListener)
                    .withText("多平台分�?")
                    .share();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /** attention to this below ,must add this**/
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        //Log.d("result","onActivityResult");
    }

}
