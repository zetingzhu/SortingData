package zhu.com.sortingdata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;

import zhu.com.sortingdata.adapter.MyFragmentPagerAdapter;
import zhu.com.sortingdata.fragment.FragmentLeftMenu;
import zhu.com.sortingdata.fragment.FragmentTab01;
import zhu.com.sortingdata.fragment.FragmentTab02;
import zhu.com.sortingdata.fragment.FragmentTab03;
import zhu.com.sortingdata.widget.CustomBar;

public class MainActivity extends SlidingFragmentActivity implements OnPageChangeListener, CustomBar.SelectListener {

    private ViewPager pager;
    private CustomBar bar;
    // 默认背景图片
    private int imgs[] = {R.drawable.icon_bar, R.drawable.icon_bar, R.drawable.icon_bar};
    // 选中时背景图片
    private int selectImgs[] = {R.drawable.icon_bar_select_01, R.drawable.icon_bar_select_02, R.drawable.icon_bar_select_03};
    // 所有fragment集合
    private ArrayList<Fragment> fragments;
    // 第一页的fragment
    private FragmentTab01 tab01;
    private FragmentTab02 tab02;
    private FragmentTab03 tab03;
    // 滑出菜单
    private SlidingMenu mSlidingMenu;
    // 侧边栏按钮
    private Button bt_menu;
    private FragmentLeftMenu leftMenu;
    TextView textView1, textView2, textView3;
    Button bt_menu_show;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        setSlidingMenu();

    }

    /**
     * 设置一个左边划出菜单
     */
    private void setSlidingMenu() {

        leftMenu = new FragmentLeftMenu();
        setBehindContentView(R.layout.layout_left_nemu);
        getSupportFragmentManager().beginTransaction().replace(R.id.ll_left_menu, leftMenu).commit();

        mSlidingMenu = getSlidingMenu();
        mSlidingMenu.setMode(SlidingMenu.LEFT);//设置左滑菜单
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
        mSlidingMenu.setShadowDrawable(R.mipmap.shadow);//设置阴影图片
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);//设置阴影图片的宽度
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidingMenu划出时主页面显示的剩余宽度
        mSlidingMenu.setFadeDegree(0.35f);//SlidingMenu滑动时的渐变程度
        mSlidingMenu.setBehindWidth(400);//设置SlidingMenu菜单的宽度


    }


    private void initView() {
        pager = (ViewPager) findViewById(R.id.main_viewPager);
        bar = (CustomBar) findViewById(R.id.main_customBar);
        // 设置选中监听
        bar.setSelectListener(this);
        // 设置菜单图片和选中图片
        bar.setImageViewBg(imgs, selectImgs);

        fragments = new ArrayList<Fragment>();
        tab01 = new FragmentTab01();
        fragments.add(tab01);
        tab02 = new FragmentTab02();
        fragments.add(tab02);
        tab03 = new FragmentTab03();
        fragments.add(tab03);
        // 设置Adapter
        pager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments));
        // 设置监听，主要是设置点点的背景
        pager.setOnPageChangeListener(this);

        bt_menu = (Button) findViewById(R.id.bt_menu);

    }

    private void initClick() {
        bt_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.showMenu();
            }
        });
    }

    public void menuOrContent(int i) {
        bar.setChange(i);
        pager.setCurrentItem(i, false);
        mSlidingMenu.showContent();
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int arg0) {
        // 改变底部状态栏背景和字体颜色
        bar.setChange(arg0);
    }

    @Override
    public void getOnclick(int i) {
        // 选中状态栏中的功能键切换viewpager的页面
        pager.setCurrentItem(i, false);
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
