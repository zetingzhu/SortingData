package zhu.com.sortingdata.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;
import zhu.com.sortingdata.R;

public class FragmentTab02 extends BaseFragment {
    private View view;
    private PtrFrameLayout mPtrFrame;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab02, null);


        initView();
        initViewPUsh();
        return view;
    }

    private void initView() {

    }

    private void initViewPUsh() {

        mPtrFrame = (PtrFrameLayout) view.findViewById(R.id.ptr);
        /**
         * 在 xml 中配置过了，就不要在这里配置了。
         */
            /*mPtrFrame.setResistance(1.7f); //阻尼系数 默认: 1.7f，越大，感觉下拉时越吃力。
            mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f); //触发刷新时移动的位置比例 默认，1.2f，移动达到头部高度1.2倍时可触发刷新操作。
	        mPtrFrame.setDurationToClose(200);//回弹延时 默认 200ms，回弹到刷新高度所用时间
	        mPtrFrame.setDurationToCloseHeader(1000);//头部回弹时间 默认1000ms
	        mPtrFrame.setPullToRefresh(false);// 刷新是保持头部 默认值 true.
	        mPtrFrame.setKeepHeaderWhenRefresh(true);//下拉刷新 / 释放刷新 默认为释放刷新*/

        /**
         * 经典 风格的头部实现

        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(getActivity());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
         */

        /**
         * StoreHouse风格的头部实现

            final StoreHouseHeader header = new StoreHouseHeader(getActivity());
            header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);
         */
        /**
         * using a string, support: A-Z 0-9 - .
         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        // header.initWithString("Alibaba");


        /**
         * Material Design风格的头部实现
         */
         final MaterialHeader header = new MaterialHeader(getActivity());
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);//显示相关工具类，用于获取用户屏幕宽度、高度以及屏幕密度。同时提供了dp和px的转化方法。


        /**
         * Rentals Style风格的头部实现
         * 这个需要引入这两个类RentalsSunDrawable.java ; RentalsSunHeaderView.java
         * 在人家git上的daemon中能找到
         */
           /* final RentalsSunHeaderView header = new RentalsSunHeaderView(this);

	        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
	        header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
	        header.setUp(mPtrFrame);
	        mPtrFrame.setLoadingMinTime(1000);
	        mPtrFrame.setDurationToCloseHeader(1500);*/


        // mPtrFrame = (PtrFrameLayout) findViewById(R.id.ptr);
        mPtrFrame.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        mPtrFrame.setPtrHandler(new PtrHandler() {

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("MainActivity.onRefreshBegin");
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.autoRefresh();//自动刷新

                        mPtrFrame.refreshComplete();
                    }
                }, 1800);

            }

            /**
             * 检查是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("MainActivity.checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
