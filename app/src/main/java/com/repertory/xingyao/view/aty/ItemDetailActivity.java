package com.repertory.xingyao.view.aty;

import com.repertory.xingyao.R;
import com.repertory.xingyao.inject.component.DaggerItemDetailComponent;
import com.repertory.xingyao.inject.module.ItemDetailModule;
import com.repertory.xingyao.presenter.item.ItemDetailPresenter;
import com.repertory.xingyao.view.base.BaseActivity;
import com.repertory.xingyao.view.baseimpl.IItemDetailView;

/**
 * @Title:ItemDetailActivity
 * @Package:com.repertory.xingyao.view.home
 * @Description: 仓库物件的信息详情页
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1815:21
 */
public class ItemDetailActivity extends BaseActivity<ItemDetailPresenter> implements IItemDetailView {

    @Override
    protected void initInjector() {
        //注入presenter实例
        DaggerItemDetailComponent.builder()
                .itemDetailModule(new ItemDetailModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadingDataOnViewDidMount() {
        //这里进行数据请求
        mPresenter.getNetData(false);
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_item_detail;
    }

    @Override
    public void updateTextContent() {
    }
}
