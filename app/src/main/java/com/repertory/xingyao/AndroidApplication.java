package com.repertory.xingyao;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.repertory.xingyao.inject.component.ApplicationComponent;
import com.repertory.xingyao.inject.component.DaggerApplicationComponent;
import com.repertory.xingyao.inject.module.ApplicationModule;

/**
 * @Title:AndroidApplication
 * @Package:com.repertory.xingyao
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1811:45
 */
public class AndroidApplication extends Application {
    private final static String TAG = "AndroidApplication";
    private static AndroidApplication instance = null;
    private static ApplicationComponent appComponent;

    public static AndroidApplication getInstance() {
        return instance;
    }

    public AndroidApplication getApplication() {
        return instance;
    }

    public static ApplicationComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initAppInstance();
        initInjector();
        initLogger();

    }


    /**
     * 提供ApplicationComponent全局单例的初始化
     */
    private void initInjector() {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .build();
    }

    /**
     * 提供AndroidApplication全局单例的初始化
     */
    private void initAppInstance() {
        instance = this;
    }

    /**
     * Logger 日志配置
     */
    private void initLogger() {
        Logger.init()               // default PRETTYLOGGER or use just init()
                .setMethodCount(3)            // default 2
                .hideThreadInfo()             // default shown
                .setLogLevel(LogLevel.FULL);  // default LogLevel.FULL
    }
}
