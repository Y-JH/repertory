package com.repertory.xingyao.inject.component;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.inject.module.HomeModule;
import com.repertory.xingyao.view.aty.HomeActivity;

import dagger.Component;

/**
 * @Title:HomeComponent
 * @Package:com.repertory.xingyao.inject.component
 * @Description: 连接 HomeModule 和 HomeActivity，将HomeModule中已实例化的 HomePresenter 实例 注入到 HomeActivity中；即注入到 BaseActivity 中的 "T mPresenter";
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:58
 */
//@Singleton
@PerActivity
@Component(modules = HomeModule.class)
public interface HomeComponent {

    //注入 P层 mPresenter 实例到 HomeActivity 的属性mPresenter中
    //从而完成实例的注解赋值
    void inject(HomeActivity homeActivity);
}
