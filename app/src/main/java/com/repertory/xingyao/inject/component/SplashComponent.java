package com.repertory.xingyao.inject.component;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.inject.module.SplashModule;
import com.repertory.xingyao.view.home.SplashActivity;

import dagger.Component;

/**
 * @Title:SplashComponent
 * @Package:com.repertory.xingyao.inject.component
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1817:01
 */

@PerActivity
@Component(modules = SplashModule.class)
public interface SplashComponent {

    void inject(SplashActivity splashActivity);
}
