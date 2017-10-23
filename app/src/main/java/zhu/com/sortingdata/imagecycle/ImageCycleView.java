package zhu.com.sortingdata.imagecycle;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import zhu.com.sortingdata.R;
import zhu.com.sortingdata.imagecycle.CycleViewPager;

/**
 * 广告图片自动轮播控件</br>
 *
 * <pre>
 *   集合ViewPager和指示器的一个轮播控件，主要用于常见的广告图片轮播，具有自动轮播和手动轮播功能
 *   使用：只在xml文件中使用com.zhu.sortingdata.imagecycle.ImageCycleView
 然后在页面中调用  {@link #setImageResources(ArrayList, ImageCycleViewListener) }即可!
 *
 *   另外提供{@link #startImageCycle() } \ {@link #pushImageCycle() }两种方法，用于在Activity不可见之时节省资源；
 *   因为自动轮播开启停止进行控制，有利于内存管理
 * </pre>
 *
 */
public class ImageCycleView extends LinearLayout {

	/**
	 * 上下文
	 */
	private Context mContext;

	/**
	 * 图片轮播视图
	 */
	private CycleViewPager mBannerPager = null;

	/**
	 * 滚动图片视图适配
	 */
	private ImageCycleAdapter mAdvAdapter;

	/**
	 * 图片轮播指示器控
	 */
	private ViewGroup mGroup;

	/**
	 * 图片轮播指示个图
	 */
	private ImageView mImageView = null;

	/**
	 * 滚动图片指示视图列表
	 */
	private ImageView[] mImageViews = null;

	/**
	 * 图片滚动当前图片下标
	 */
	private int mImageIndex = 1;

	/**
	 * 手机密度
	 */
	private float mScale;

	// 图片点击监听计划
	private ImageCycleViewListener mImageCycleViewListener ;

	/**
	 * @param context
	 */
	public ImageCycleView(Context context) {
		super(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ImageCycleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		mScale = context.getResources().getDisplayMetrics().density;
		LayoutInflater.from(context).inflate(R.layout.image_cycle_layout, this);
		mBannerPager = (CycleViewPager) findViewById(R.id.pager_banner);
		mBannerPager.setOnPageChangeListener(new GuidePageChangeListener());
		mBannerPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					case MotionEvent.ACTION_UP:
						// ????图片滚动
						startImageTimerTask();
						break;
					default:
						// 停止图片滚动
						stopImageTimerTask();
						break;
				}
				return false;
			}
		});
		// 滚动图片右下指示器视??
		mGroup = (ViewGroup) findViewById(R.id.viewGroup);
	}

	/**
	 * 装填图片数据
	 *
	 * @param
	 * @param imageCycleViewListener
	 */
	public void setImageResources(ArrayList< ImageCycleInfo> infoList  , ImageCycleViewListener imageCycleViewListener) {
		this.mImageCycleViewListener = imageCycleViewListener ;
		// 清除所有子视图
		mGroup.removeAllViews();
		// 图片广告数量
		final int imageCount = infoList.size();
		mImageViews = new ImageView[imageCount];
		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(mContext);
			LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(20,20);
			layout.setMargins(3, 0, 3, 0);
			mImageView.setLayoutParams(layout);
			mImageViews[i] = mImageView;
			if (i == 0) {
				mImageViews[i].setBackgroundResource(R.drawable.point_bg_enable);
			} else {
				mImageViews[i].setBackgroundResource(R.drawable.point_bg_normal);
			}
			mGroup.addView(mImageViews[i]);
		}
		mAdvAdapter = new ImageCycleAdapter(mContext, infoList);
		mBannerPager.setAdapter(mAdvAdapter);
		startImageTimerTask();
	}

	/**
	 * 开始轮播(手动控制自动轮播与否，便于资源控??
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 定时图片滚动任务
	 */
	private void startImageTimerTask() {
		stopImageTimerTask();
		// 图片五秒滚动一次
		mHandler.postDelayed(mImageTimerTask, 5000);
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (mImageViews != null) {
				// 下标等于图片列表长度说明已滚动到第几张图片,重置下标
				if ((++mImageIndex) == mImageViews.length + 1) {
					mImageIndex = 1;
				}
				mBannerPager.setCurrentItem(mImageIndex);
			}
		}
	};

	/**
	 * 轮播图片状态监听切换
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask(); // 开始下次计时
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {

			if (index == 0 || index == mImageViews.length + 1) {
				return;
			}
			// 设置图片滚动指示器背
			mImageIndex = index;
			index -= 1;
			mImageViews[index].setBackgroundResource(R.drawable.point_bg_enable);
			for (int i = 0; i < mImageViews.length; i++) {
				if (index != i) {
					mImageViews[i].setBackgroundResource(R.drawable.point_bg_normal);
				}
			}

		}

	}

	private class ImageCycleAdapter extends PagerAdapter {

		/**
		 * 图片视图缓存列表
		 */
		private ArrayList<ImageView> mImageViewCacheList;

		/**
		 * 图片资源列表
		 */
		private ArrayList< ImageCycleInfo> mAdList = new ArrayList< ImageCycleInfo>();

		private Context mContext;

		public ImageCycleAdapter(Context context, ArrayList< ImageCycleInfo> adList ) {
			mContext = context;
			mAdList = adList;
			mImageViewCacheList = new ArrayList();
		}

		@Override
		public int getCount() {
			return mAdList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			return view == obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			ImageView imageView = null;
			if (mImageViewCacheList.isEmpty()) {
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);

			} else {
				imageView = mImageViewCacheList.remove(0);
//				System.out.println("获取到的id：" + imageView.getTag());
			}
			// 设置图片点击监听
			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mImageCycleViewListener.onImageClick(mAdList.get(position),position, v);
				}
			});
//			imageView.setImageResource(mAdList.get(position).getImg());
			mImageCycleViewListener.loadImageDisplay(imageView, mAdList.get(position));
			container.addView(imageView);

			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			ImageView view = (ImageView) object;
			container.removeView(view);
			view.setTag(new Date());
			mImageViewCacheList.add(view);
//			System.out.println("移除的id：" + view.getTag());
		}

	}

	/**
	 * 轮播控件的监听事
	 * @author minking
	 */
	public static interface ImageCycleViewListener {

		/**
		 * 单击图片事件
		 *
		 * @param
		 * @param imageView
		 */
		public void onImageClick( ImageCycleInfo info, int postion, View imageView);

		/**
		 * 将图片显示的imageView中
		 * @param info
		 * @return
		 */
		public ImageView loadImageDisplay(ImageView imageView ,  ImageCycleInfo info );
	}

}
