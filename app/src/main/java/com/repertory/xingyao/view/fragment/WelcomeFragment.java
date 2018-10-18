package com.repertory.xingyao.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.repertory.xingyao.R;
import com.repertory.xingyao.utils.Constant;
import com.repertory.xingyao.view.event.IWelcomeEvent;
import com.repertory.xingyao.wiget.ColorAnimationView;

import org.greenrobot.eventbus.Subscribe;

import me.yokeyword.eventbusactivityscope.EventBusActivityScope;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Auther: YJH
 * @Package: Bulo-com.buluedu
 * @Created time: 2018/8/15-15:22
 * @description: 3张欢迎图
 */
public class WelcomeFragment extends SupportFragment {

    private final String TAG = "WelcomeFragment";
    private static final int[] resource = new int[]{R.drawable.welcome_easy, R.drawable.welcome_usefully, R.drawable.welcome_good};

    public static WelcomeFragment newInstance() {
        Bundle args = new Bundle();
        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBusActivityScope.getDefault(getActivity()).register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusActivityScope.getDefault(getActivity()).unregister(this);
    }

    @Subscribe
    public void welToXX(IWelcomeEvent.Experience event) {
        if (Constant.WELCOME_EXPERIENCE.equals(event.WELCOME_EXPERIENCE)) {
            //点击立即体验进入启动页
            start(SplashFragment.newInstance());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        startWelcome();
    }


    private void startWelcome() {
        View rootView = getView();
        MyFragmentStatePager adpter = new MyFragmentStatePager(getActivity().getSupportFragmentManager());
        ColorAnimationView colorAnimationView = (ColorAnimationView) rootView.findViewById(R.id.ColorAnimationView);
        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(adpter);
        /**
         *  首先，你必须在 设置 Viewpager的 adapter 之后在调用这个方法
         *  第二点，setmViewPager(ViewPager mViewPager,Object obj, int count, int... colors)
         *         第一个参数 是 你需要传人的 viewpager
         *         第二个参数 是 一个实现了ColorAnimationView.OnPageChangeListener接口的Object,用来实现回调
         *         第三个参数 是 viewpager 的 孩子数量
         *         第四个参数 int... colors ，你需要设置的颜色变化值~~ 如何你传人 空，那么触发默认设置的颜色动画
         * */
        colorAnimationView.setmViewPager(viewPager, resource.length);
        colorAnimationView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("TAG", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    public class MyFragmentStatePager extends FragmentStatePagerAdapter {

        public MyFragmentStatePager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(position);
        }

        @Override
        public int getCount() {
            return resource.length;
        }
    }

    @SuppressLint("ValidFragment")
    public static class MyFragment extends Fragment {
        private int position;

        /**
         * 质量压缩方法          * @param image          * @return
         */
//        private Bitmap compressImage(int resImg) {
//            Resources res = getActivity().getResources();
//            Bitmap bmp = BitmapFactory.decodeResource(res, resImg);
//
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//            int options = 90;
//            while (baos.toByteArray().length / 1024 > 100) {
//            // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
//                baos.reset(); // 重置baos即清空baos
//                bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);
//            // 这里压缩options%，把压缩后的数据存放到baos中
//                options -= 10;// 每次都减少10
//            }
//            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
//            // 把压缩后的数据baos存放到ByteArrayInputStream中
//            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
//            // 把ByteArrayInputStream数据生成图片
//            return bitmap;
//
//
//        }

        public MyFragment(int position) {
            this.position = position;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(resource[position]);
//            imageView.setImageBitmap(compressImage(resource[position]));
            if (Constant.POSITION_CLICK == position) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击最后一张欢迎图，进入"立即体验"
                        EventBusActivityScope.getDefault(getActivity())
                                .post(new IWelcomeEvent.Experience());

                    }
                });
            }
            return imageView;
        }
    }


}
