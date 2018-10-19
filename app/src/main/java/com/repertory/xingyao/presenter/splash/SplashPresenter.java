package com.repertory.xingyao.presenter.splash;

import com.repertory.xingyao.presenter.base.IBasePresenter;
import com.repertory.xingyao.view.aty.SplashActivity;

/**
 * @Title:SplashPresenter
 * @Package:com.repertory.xingyao.presenter.splash
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1816:59
 */
public class SplashPresenter implements IBasePresenter {
    private final SplashActivity mSplashActivity;

    public SplashPresenter(SplashActivity mSplashActivity) {
        this.mSplashActivity = mSplashActivity;
    }

    @Override
    public void getNetData(boolean isRefresh) {

    }

    @Override
    public void getNetMoreData(boolean isMore) {

    }
}
