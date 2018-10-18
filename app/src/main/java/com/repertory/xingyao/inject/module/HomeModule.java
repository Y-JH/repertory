package com.repertory.xingyao.inject.module;

import com.repertory.xingyao.presenter.home.HomePresenter;
import com.repertory.xingyao.view.home.HomeActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @Title:HomeModule
 * @Package:com.repertory.xingyao.inject.module
 * @Description: HomeActivity 使用dagger注入功能，为HomeActivity提供HomePresenter实例的module类；
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:57
 */

@Module
public class HomeModule {
    private final HomeActivity mHomeActivity;

    public HomeModule(HomeActivity homeActivity){
        mHomeActivity = homeActivity;
    }

    @Singleton
    @Provides
    public HomePresenter provideHomePresenter(){
        return new HomePresenter(mHomeActivity);
    }
}
