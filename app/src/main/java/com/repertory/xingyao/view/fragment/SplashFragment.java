package com.repertory.xingyao.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import com.repertory.xingyao.AndroidApplication;
import com.repertory.xingyao.R;
import com.repertory.xingyao.utils.Constant;
import com.repertory.xingyao.utils.RxHelper;
import com.repertory.xingyao.utils.SharedPreferencesHelper;
import com.repertory.xingyao.view.base.BaseFragmentationFragment;
import com.repertory.xingyao.view.home.HomeActivity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
/**
 * @Title:HomeActivity
 * @Package:com.repertory.xingyao.view.home
 * @Description: app-SplashFragment 启动页 停留2秒钟
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1811:40
 */
public class SplashFragment extends BaseFragmentationFragment {

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void loadingDataOnViewDidMount() {
        RxHelper.countdown(2)
                .compose(this.<Integer>bindToLife())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Object o) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //记录用户首次进入
                        SharedPreferencesHelper.putSharedPreferences(AndroidApplication.getInstance(), Constant.APP_USED, Constant.APP_USED);
                        getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                        getActivity().finish();
                    }
                });

    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_splash;
    }

}
