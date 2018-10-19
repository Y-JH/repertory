package com.repertory.xingyao.presenter.home;

import com.orhanobut.logger.Logger;
import com.repertory.xingyao.presenter.base.IBasePresenter;
import com.repertory.xingyao.view.aty.HomeActivity;

/**
 * @Title:HomePresenter
 * @Package:com.repertory.xingyao.presenter.home
 * @Description: HomeActivity 的 P层 逻辑处理
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1814:04
 */
public class HomePresenter implements IBasePresenter {
    private HomeActivity mHomeActivity;
    private final String TAG = "HomePresenter";

    public HomePresenter(HomeActivity homeActivity){
        mHomeActivity = homeActivity;
    }

    @Override
    public void getNetData(boolean isRefresh) {
        Logger.d(TAG, "HomePresenter-getNetData-false");
        mHomeActivity.updateTextContent();
    }

    @Override
    public void getNetMoreData(boolean isMore) {
        Logger.d(TAG, "HomePresenter-getNetMoreData-false");
    }
}
