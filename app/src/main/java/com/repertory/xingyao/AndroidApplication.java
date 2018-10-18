package com.repertory.xingyao;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.repertory.xingyao.inject.component.ApplicationComponent;
import com.repertory.xingyao.inject.component.DaggerApplicationComponent;
import com.repertory.xingyao.inject.module.ApplicationModule;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

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
        initFragmentation();

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
    /**
     *
     *  功能：初始化路由Fragmentation
     *  @Link:https://github.com/YoKeyword/Fragmentation
     */
    private void initFragmentation(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式
                // SHAKE: 摇一摇唤出
                // NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.NONE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

}
