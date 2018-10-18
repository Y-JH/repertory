package com.repertory.xingyao.inject.module;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.presenter.home.HomePresenter;
import com.repertory.xingyao.view.home.HomeActivity;

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
    private final HomeActivity mHomeActivity;//为P层提供HomeActivity引用，业务逻辑使用上层抽象接口的交互；

    public HomeModule(HomeActivity homeActivity){
        mHomeActivity = homeActivity;
    }


    /**
     * 提供P层实例
     * @return
     */
//    @Singleton
    @PerActivity
    @Provides
    public HomePresenter provideHomePresenter(){
        return new HomePresenter(mHomeActivity);
    }
}
