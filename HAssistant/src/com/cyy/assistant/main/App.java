package com.cyy.assistant.main;

import android.app.Application;
import android.os.Bundle;


import com.umeng.socialize.PlatformConfig;

/**
 * Created by ntop on 15/7/8.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    //鍚勪釜骞冲彴鐨勯厤缃紝寤鸿鏀惧湪鍏ㄥ眬Application鎴栬�呯▼搴忓叆鍙�
    {
        //寰�?    wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        //璞嗙摚RENREN骞冲彴鐩墠鍙兘鍦ㄦ湇鍔�?�櫒绔厤缃�?
        //鏂版氮寰崥
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        //鏄撲�?
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
    }
}
