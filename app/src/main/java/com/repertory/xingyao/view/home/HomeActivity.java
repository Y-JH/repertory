package com.repertory.xingyao.view.home;

import android.widget.TextView;

import com.repertory.xingyao.R;
import com.repertory.xingyao.inject.component.DaggerHomeComponent;
import com.repertory.xingyao.inject.module.HomeModule;
import com.repertory.xingyao.presenter.home.HomePresenter;
import com.repertory.xingyao.view.base.BaseActivity;
import com.repertory.xingyao.view.baseimpl.IHomeView;

/**
 * @Title:HomeActivity
 * @Package:com.repertory.xingyao.view.home
 * @Description: app 首页
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1811:40
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements IHomeView {

    private TextView textView;

    @Override
    protected void initInjector() {
        //注入presenter实例
        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        textView = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    protected void loadingDataOnViewDidMount() {
        mPresenter.getNetData(false);//视图挂载之后，加载第一波数据
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    public void updateTextContent() {
        textView.setText("这里使用mvp架构进行数据更新-成功");
    }
}
