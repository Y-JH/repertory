package com.repertory.xingyao.inject.module;

import com.repertory.xingyao.inject.PerActivity;
import com.repertory.xingyao.presenter.item.ItemDetailPresenter;
import com.repertory.xingyao.view.aty.ItemDetailActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @Title:ItemDetailModule
 * @Package:com.repertory.xingyao.inject.module
 * @Description:仓库物件的信息详情页-dagger注入-提供实例的module类
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1815:23
 */
@Module
public class ItemDetailModule {
    private final ItemDetailActivity mItemDetailActivity;
    public ItemDetailModule(ItemDetailActivity itemDetailActivity){
        mItemDetailActivity = itemDetailActivity;
    }


    @PerActivity
    @Provides
    public ItemDetailPresenter provideItemDetailPresenter(){
        return new ItemDetailPresenter(mItemDetailActivity);
    }

}
