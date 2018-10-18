package com.repertory.xingyao.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.repertory.xingyao.presenter.base.IBasePresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

/**
 * @Title:BaseActivity
 * @Package:com.repertory.xingyao.view.base
 * @Description:  MVP 架构中的 V 层 aty基类抽象层
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:21
 */
public abstract class BaseActivity<T extends IBasePresenter> extends RxAppCompatActivity implements IBaseView  {

    @Inject
    protected T mPresenter;

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    @Override
    public void finishRefresh() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        initInjector();
        initViews();
        updateViews(false);
        loadingDataOnViewDidMount();
    }


    //dagger 注入
    protected abstract void initInjector();

    //初始化view视图控件
    protected abstract void initViews();

    //视图挂载之后，加载数据
    protected abstract void loadingDataOnViewDidMount();

    //更新view视图控件
    protected abstract void updateViews(boolean isRefresh);

    //使用support lib 系统自带资源类型注解（返回值限定为资源文件类型）
    // 绑定布局文件
    @LayoutRes
    protected abstract int attachLayoutRes();



    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
