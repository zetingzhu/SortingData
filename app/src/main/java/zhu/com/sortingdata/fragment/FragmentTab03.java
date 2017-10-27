package zhu.com.sortingdata.fragment;


import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import zhu.com.sortingdata.R;

public class FragmentTab03 extends BaseFragment {

    private View view;
    private TextView mTv;
    private AnimationDrawable animationDrawable;
    private ImageView mIv;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_tab03, null);
        initView();
        return view;
    }

    private void initView() {

        mTv = (TextView) view.findViewById(R.id.textViewAm);

        view.findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRotateAnimation();
            }
        });
        view.findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createScaleAnimation();
            }
        });
        view.findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlphaAnimation();
            }
        });
        view.findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTranslateAnimation();
            }
        });
        view.findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAnimationSet();
            }
        });
        view.findViewById(R.id.button16).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.set_demo);
                mTv.startAnimation(hyperspaceJumpAnimation);
            }
        });

        mIv = (ImageView) view.findViewById(R.id.iv_frame);
        mIv.setImageResource(R.drawable.frame_animation);
        animationDrawable = (AnimationDrawable) mIv.getDrawable();

        view.findViewById(R.id.button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**特别注意，AnimationDrawable的start()方法不能在Activity的onCreate方法中调运，因为AnimationDrawable还未完全附着到window上，所以最好的调运机是onWindowFocusChanged()方法中。*/
                animationDrawable.start();
            }
        });
        view.findViewById(R.id.button22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });

        view.findViewById(R.id.button31).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createValueAnimator();
            }
        });

        view.findViewById(R.id.button32).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vreateValueAnimatorArgb();
            }
        });

    }

    /**
     * 创建旋转动画
     */
    private void createRotateAnimation() {
/**
 * 构造方法如下
 *fromDegrees、toDegrees表示开始、结束的角度(0度为水平方向右侧的开始角度)，pivotXValue、pivotYValue代表旋转的中心位置，[0.0f-1.0f],
 *pivotXType、pivotYType表示旋转的类型(Animation.ABSOLUTE,、Animation.RELATIVE_TO_SELF、Animation.RELATIVE_TO_PARENT)
 *当type为Animation.ABSOLUTE时，这个个值为具体的像素值，当type为Animation.RELATIVE_TO_SELF或Animation.RELATIVE_TO_PARENT，这个个值为比例值，取值范围是[0f, 1.0f]
 *public RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,int pivotYType, float pivotYValue) {     }
 */
//初始化RotateAnimation
        RotateAnimation animation11 = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//动画执行时间
        animation11.setDuration(4000);
//动画重复次数-1表示不停重复
        animation11.setRepeatCount(-1);
// 添加插值器
        animation11.setInterpolator(new AccelerateInterpolator(2f));

//给控件设置动画
        mTv.startAnimation(animation11);
    }


    /**
     * 创建缩放动画
     */
    private void createScaleAnimation() {
/**
 * 构造方法如下
 * fromX、toX 开始结束的X轴缩放比率[0.0f-1.0f]，fromY、toYtoY开始结束的Y轴缩放比率[0.0f-1.0f]，pivotXValue、pivotYValue代表旋转的中心位置，[0.0f-1.0f],
 * pivotXType、pivotYType表示旋转的类型(Animation.ABSOLUTE,、Animation.RELATIVE_TO_SELF、Animation.RELATIVE_TO_PARENT)
 * 当type为Animation.ABSOLUTE时，这个个值为具体的像素值，当type为Animation.RELATIVE_TO_SELF或Animation.RELATIVE_TO_PARENT，这个个值为比例值，取值范围是[0f, 1.0f]
 *  public ScaleAnimation(float fromX, float toX, float fromY, float toY,int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {}
 */
//初始化ScaleAnimation
        ScaleAnimation animation12 = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//动画执行时间
        animation12.setDuration(2000);
//动画重复次数-1表示不停重复
//animation.setRepeatCount(-1);
        // 添加插值器
        animation12.setInterpolator(new AnticipateInterpolator(2f));
//给控件设置动画
        mTv.startAnimation(animation12);
    }

    /**
     * 创建透明度渐变动画
     */
    private void createAlphaAnimation() {
/***
 * 构造方法如下
 * fromAlpha、toAlpha表示透明度的起始值和结束值，0.0f表示全透明，1.0f表示不透明。
 * public AlphaAnimation(float fromAlpha, float toAlpha) {}
 */
//初始化AlphaAnimation
        AlphaAnimation animation13 = new AlphaAnimation(1.0f, 0.0f);
//动画执行时间
        animation13.setDuration(2000);
//动画重复次数-1表示不停重复
        animation13.setRepeatCount(-1);
//给控件设置动画
        mTv.startAnimation(animation13);
    }

    /**
     * 创建位移动画
     */
    private void createTranslateAnimation() {
/**
 * 构造方法如下
 * fromXType、toXType、fromYType、toYType(Animation.ABSOLUTE,、Animation.RELATIVE_TO_SELF、Animation.RELATIVE_TO_PARENT)
 * 当type为Animation.ABSOLUTE时，这个个值为具体的像素值，当type为Animation.RELATIVE_TO_SELF或Animation.RELATIVE_TO_PARENT，这个个值为比例值，取值范围是[0f, 1.0f]
 *public TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue,int fromYType, float fromYValue, int toYType, float toYValue) {}
 */
        TranslateAnimation animation14 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);
        animation14.setDuration(2000);
//动画重复次数-1表示不停重复
//        animation14.setRepeatCount(-1);
//动画结束后View停留在结束位置
        animation14.setFillAfter(true);
        // 添加插值器
        animation14.setInterpolator(new BounceInterpolator());

        mTv.startAnimation(animation14);
    }

    /**
     * 创建组合动画
     */
    private void createAnimationSet() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f, Animation.ABSOLUTE, 100);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.setDuration(2000);
        animationSet.setRepeatCount(-1);
        animationSet.setFillAfter(true);
        mTv.startAnimation(animationSet);
    }


    private void createValueAnimator() {
//初始化ValueAnimator
        ValueAnimator valueAnimator = ValueAnimator.ofInt(100, 80);
//监听动画
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();//当前的值
                mIv.getLayoutParams().width = value;
                mIv.getLayoutParams().height = value;
                mIv.requestLayout();
            }
        });
        valueAnimator.setDuration(2000);//动画时长
        valueAnimator.start();//启动动画
         }

    private void vreateValueAnimatorArgb(){
//ValueAnimator.ofArgb()方法是在API Level 21中才加入的
        if(Build.VERSION.SDK_INT >= 21){
            ValueAnimator valueAnimator = ValueAnimator.ofArgb(R.color.red,  android.R.color.holo_blue_bright);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int color = (int) animation.getAnimatedValue();
                    mTv.setBackgroundResource(color);
                    mTv.requestLayout();
                    Log.d("AAA",color+"");
                }
            });
            valueAnimator.setDuration(2000);//动画时长
            valueAnimator.start();//启动动画
        }else {
            Log.d("AAA","没有到" + Build.VERSION.SDK_INT );
        }

    }



}
