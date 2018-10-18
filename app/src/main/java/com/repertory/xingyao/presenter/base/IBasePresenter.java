package com.repertory.xingyao.presenter.base;

/**
 * @Title:IBasePresener
 * @Package:com.repertory.xingyao.view.base
 * @Description: MVP 架构中的 P 层 抽象层
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1813:22
 */
public interface IBasePresenter {

    void getNetData(boolean isRefresh);//获取网络数据（可用作下拉刷新）

    void getNetMoreData(boolean isMore);//获取网络数据（可用作加载跟多）
}
