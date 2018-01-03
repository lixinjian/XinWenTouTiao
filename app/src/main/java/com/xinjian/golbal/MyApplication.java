package com.xinjian.golbal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lxj on 2017/12/21 0021.
 */

public class MyApplication extends MultiDexApplication/*TODO:不知道为啥非得继承这个MultiDexApplication,如果继承了系统的Application就会报错,找不到activity*/ {

    /**
     * 上下文对象
     */
    private Context mContext;

    /**
     * Lint是一个静态检查器，它围绕Android项目的正确性、安全性、性能、
     * 可用性以及可访问性进行分析。它检查的对象包括XML资源、
     * 位图、ProGuard配置文件、源文件甚至编译后的字节码。
     * 这一版本的Lint包含了API版本检查、性能检查以及其他诸多特性。
     * 其中还有一个重要的改动是Lint可以使用@SuppressLint标注忽略指定的警告。
     */
    @SuppressLint("StaticFieldLeak")
    private static MyApplication sMyApplication;

    /**
     * 存放活动状态的(未被销毁)的Activity列表
     */
    private Set<Activity> activitys;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        sMyApplication = this;
    }

    public static Context getContext() {
        return getInstance().mContext;
    }

    public static MyApplication getInstance() {
        return sMyApplication;
    }

    /**
     * 添加Activity到容器中
     */
    public void addActivity(Activity activity) {
        if (activitys == null) activitys = new HashSet<>();
        activitys.add(activity);
    }

    /**
     * 遍历所有Activity并finish
     */
    public void exit() {
        if (activitys != null) {
            synchronized (MyApplication.class) {
                for (Activity act : activitys) {
                    act.finish();
                }
            }
        }
    }

    public void removeActivity(Activity activity) {
        if (activitys != null) activitys.remove(activity);
    }

    private void initDomain() {
        //获取本应用程序信息
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
