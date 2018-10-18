package com.repertory.xingyao.presenter.item;

import com.repertory.xingyao.presenter.base.IBasePresenter;
import com.repertory.xingyao.view.home.ItemDetailActivity;

/**
 * @Title:ItemDetailPresenter
 * @Package:com.repertory.xingyao.presenter.item
 * @Description:仓库物件的信息详情页 P层
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1815:27
 */
public class ItemDetailPresenter implements IBasePresenter{
    private final ItemDetailActivity mItemDetailActivity;

    public ItemDetailPresenter(ItemDetailActivity mItemDetailActivity) {
        this.mItemDetailActivity = mItemDetailActivity;
    }

    @Override
    public void getNetData(boolean isRefresh) {

    }

    @Override
    public void getNetMoreData(boolean isMore) {

    }
}
