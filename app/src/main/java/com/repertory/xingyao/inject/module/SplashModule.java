package com.repertory.xingyao.inject.module;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.presenter.splash.SplashPresenter;
import com.repertory.xingyao.view.home.SplashActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @Title:SplashModule
 * @Package:com.repertory.xingyao.inject.module
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1816:58
 */

@Module
public class SplashModule {

    private final SplashActivity mSplashActivity;

    public SplashModule(SplashActivity mSplashActivity) {
        this.mSplashActivity = mSplashActivity;
    }

    @PerActivity
    @Provides
    public SplashPresenter provideSplashPresenter(){
        return new SplashPresenter(mSplashActivity);
    }
}
