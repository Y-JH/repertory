package com.repertory.xingyao.inject.component;

import android.content.Context;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.inject.module.ApplicationModule;

import dagger.Component;

/**
 * @Title:ApplicationComponent
 * @Package:com.repertory.xingyao.inject.component
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:39
 */
//@Singleton
@PerActivity
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();
}
