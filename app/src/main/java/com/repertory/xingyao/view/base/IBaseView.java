package com.repertory.xingyao.view.base;


import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * @Title:IBaseView
 * @Package:com.repertory.xingyao.module.base
 * @Description:  MVP 架构中的  层 抽象层
 * @Auther:YJH
 * @Email:yuannunhua@gmail.comV
 * @Date:2018/10/1720:13
 */
public interface IBaseView {

    void showLoading();//显示加载动画

    void hideLoading();//隐藏正在加载的动画

    void showNetError();//显示网络错误

    void finishRefresh();//完成页面的数据的刷新

    <T> LifecycleTransformer<T> bindToLife();//绑定生命周期
}
