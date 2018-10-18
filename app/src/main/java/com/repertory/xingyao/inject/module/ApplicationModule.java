package com.repertory.xingyao.inject.module;

import android.content.Context;

import com.repertory.xingyao.AndroidApplication;
import com.repertory.xingyao.inject.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @Title:ApplicationModule
 * @Package:com.repertory.xingyao.inject.module
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:39
 */
@Module
public class ApplicationModule {
    private final AndroidApplication mApplication;
    public ApplicationModule(AndroidApplication application){
        mApplication = application;
    }


//    @Singleton
    @PerActivity
    @Provides
    Context provideApplicationContext(){
        return mApplication.getApplication();
    }
}
