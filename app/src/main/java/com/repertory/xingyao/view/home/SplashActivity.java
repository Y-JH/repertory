package com.repertory.xingyao.view.home;

import com.repertory.xingyao.AndroidApplication;
import com.repertory.xingyao.R;
import com.repertory.xingyao.inject.component.DaggerSplashComponent;
import com.repertory.xingyao.inject.module.SplashModule;
import com.repertory.xingyao.presenter.splash.SplashPresenter;
import com.repertory.xingyao.utils.Constant;
import com.repertory.xingyao.utils.SharedPreferencesHelper;
import com.repertory.xingyao.utils.StringUtils;
import com.repertory.xingyao.view.base.BaseFragmentationActivity;
import com.repertory.xingyao.view.baseimpl.ISplashView;
import com.repertory.xingyao.view.fragment.SplashFragment;
import com.repertory.xingyao.view.fragment.WelcomeFragment;

/**
 * @Title:HomeActivity
 * @Package:com.repertory.xingyao.view.home
 * @Description: app 欢迎页和启动页
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1811:40
 */
public class SplashActivity extends BaseFragmentationActivity<SplashPresenter> implements ISplashView {

    @Override
    protected void initInjector() {
        DaggerSplashComponent.builder()
                .splashModule(new SplashModule(this))
                .build().inject(this);
    }

    @Override
    protected void initViews() {
        String appUsed = (String) SharedPreferencesHelper.getSharedPreferences(AndroidApplication.getInstance(), Constant.APP_USED,"");
        if(StringUtils.isEmpty(appUsed)){
            //如果用户是第一次使用app，加载欢迎页
            if (null == findFragment(WelcomeFragment.class)) {
                loadRootFragment(R.id.fl_container, WelcomeFragment.newInstance());
            }
        }else {
            //如果用户不是第一次使用app，加载闪屏页
            if (null == findFragment(SplashFragment.class)) {
                loadRootFragment(R.id.fl_container, SplashFragment.newInstance());
            }
        }


    }

    @Override
    protected void loadingDataOnViewDidMount() {

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    @Override
    public void updateTextContent() {

    }
}
