package com.repertory.xingyao.view.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.repertory.xingyao.presenter.base.IBasePresenter;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

/**
 * @Title:BaseFragment
 * @Package:com.repertory.xingyao.view.base
 * @Description: MVP 架构中的 V 层 fragment基类抽象层
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1817:25
 */
public abstract class BaseFragment <T extends IBasePresenter> extends RxFragment implements IBaseView{

    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
//            ButterKnife.bind(this, mRootView);
            initInjector();
            initViews();

        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
            loadingDataOnViewDidMount();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews(false);
            loadingDataOnViewDidMount();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
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
