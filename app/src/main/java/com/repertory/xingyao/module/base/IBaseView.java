package com.repertory.xingyao.module.base;

/**
 * @Title:IBaseView
 * @Package:com.repertory.xingyao.module.base
 * @Description:
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1720:13
 */
public interface IBaseView {

    void showLoading();//显示加载动画

    void hideLoading();//隐藏正在加载的动画

    void showNetError();//显示网络错误

    void finishRefresh();//完成页面的数据的刷新


}
