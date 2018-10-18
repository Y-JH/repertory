package com.repertory.xingyao.inject.component;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.inject.module.ItemDetailModule;
import com.repertory.xingyao.view.home.ItemDetailActivity;

import dagger.Component;

/**
 * @Title:ItemDetailComponent
 * @Package:com.repertory.xingyao.inject.component
 * @Description:仓库物件的信息详情页-dagger注入-关联ItemDetailActivity和presenter实例的Component类
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1815:22
 */

//@Singleton
@PerActivity
@Component(modules = ItemDetailModule.class)
public interface ItemDetailComponent {
    //关联ItemDetailActivity，并注入presenter实例
    void inject(ItemDetailActivity itemDetailActivity);
}
