package zhu.com.sortingdata.fragment;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import java.io.File;
import java.util.ArrayList;

import zhu.com.sortingdata.R;
import zhu.com.sortingdata.imagecycle.ImageCycleInfo;
import zhu.com.sortingdata.imagecycle.ImageCycleView;

public class FragmentTab01 extends BaseFragment {

	// view对象
	private View view;
	// 下拉加载对象
	private PullToRefreshScrollView ptr_scroll;
	// 轮播控件
	private ImageCycleView mImageCycleView;
	// 轮播图片对象
	private ArrayList<ImageCycleInfo> infos;
	// 轮播图片地址
	private int[] imageUrls = { R.drawable.icon_star_01,
			R.drawable.icon_star_02, R.drawable.icon_star_03 };
	// 网络图片
	private String urlImg01 = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png";
	private String urlImg02 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=10409202,1487877148&fm=23&gp=0.jpg";
	// 显示图片的view
	private ImageView iv_net_display, iv_net_display01, iv_net_display02 , iv_net_display03;

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_tab01, null);
		initView();
		initImageCycleView(view);
		initImageLoader();

		return view;
	}

	private void initImageLoader() {
		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				// .delayBeforeLoading(int delayInMillis)//
				// delayInMillis为你设置的下载前的延迟时间
				// .preProcessor(BitmapProcessor
				// preProcessor)//设置图片加入缓存前，对bitmap进行设置
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.displayer(new RoundedBitmapDisplayer(100))// 是否设置为圆角，弧度为多少
				// .displayer(new FadeInBitmapDisplayer(20000))//
				// 是否图片加载好后渐入的动画时间
				.build();// 构建完成
		imageLoader.displayImage(urlImg02, iv_net_display, options);

		DisplayImageOptions.Builder optionsBuilder = new DisplayImageOptions.Builder();
		optionsBuilder.showImageOnLoading(R.drawable.ic_launcher); // 设置图片在下载期间显示的图片
		optionsBuilder.showImageForEmptyUri(R.drawable.ic_launcher);// 设置图片Uri为空或是错误的时候显示的图片
		optionsBuilder.showImageOnFail(R.drawable.ic_launcher); // 设置图片加载/解码过程中错误时候显示的图片
		optionsBuilder.cacheInMemory(true);// 设置下载的图片是否缓存在内存中
		optionsBuilder.cacheOnDisk(true);// 设置下载的图片是否缓存在SD卡中
		optionsBuilder.considerExifParams(true); // 是否考虑JPEG图像EXIF参数（旋转，翻转）
		optionsBuilder.imageScaleType(ImageScaleType.EXACTLY_STRETCHED);// 设置图片以如何的编码方式显示
		optionsBuilder.bitmapConfig(Bitmap.Config.RGB_565);// 设置图片的解码类型//
		optionsBuilder.resetViewBeforeLoading(true);// 设置图片在下载前是否重置，复位
		optionsBuilder.displayer(new FadeInBitmapDisplayer(10000));// 加载好后渐入的动画时间

		imageLoader.displayImage(urlImg02, iv_net_display01, optionsBuilder.build(),
				new ImageLoadingListener() {

					@Override
					public void onLoadingCancelled(String arg0, View arg1) {
						// 加载取消的时候执行
						System.out.println("onLoadingCancelled");
					}

					@Override
					public void onLoadingComplete(String arg0, View arg1,
												  Bitmap arg2) {
						// 加载成功的时候执行
						System.out.println("onLoadingComplete");
					}

					@Override
					public void onLoadingFailed(String arg0, View arg1,
												FailReason arg2) {
						// 加载失败的时候执行
						System.out.println("onLoadingFailed");
					}

					@Override
					public void onLoadingStarted(String arg0, View arg1) {
						// 开始加载的时候执行
						System.out.println("onLoadingStarted");
					}
				}, new ImageLoadingProgressListener() {

					@Override
					public void onProgressUpdate(String arg0, View arg1,
												 int arg2, int arg3) {
						System.out.println("arg0:" + arg0 + "-arg2:" + arg2 + "-arg3:" + arg3);
					}
				});
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File path = Environment.getExternalStorageDirectory();
			String imageSDCardUri = "file://" +path + File.separator + "news_article" + File.separator + "e5095e549e1f3153ef5edb10936d15cc.jpg"  ;
			imageLoader.displayImage(imageSDCardUri, iv_net_display02);
			DisplayImageOptions optionsSDCard = new DisplayImageOptions.Builder()
//	        	.displayer(new RoundedBitmapDisplayer(100))// 是否设置为圆角，弧度为多少
					.displayer(new CircleBitmapDisplayer())// 设置为圆
					.build();// 构建完成
			imageLoader.displayImage(imageSDCardUri , iv_net_display03 , optionsSDCard ,new ImageLoadingListener() {

				@Override
				public void onLoadingStarted(String arg0, View arg1) {

				}

				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {

				}

				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
//					iv_net_display03.setImageBitmap(arg2);
				}

				@Override
				public void onLoadingCancelled(String arg0, View arg1) {

				}
			});
		}
	}

	private void initView() {
		ptr_scroll = (PullToRefreshScrollView) view.findViewById(R.id.ptr_scroll);
		mImageCycleView = ( ImageCycleView) view.findViewById(R.id.imageCycle_View);
		iv_net_display = (ImageView) view.findViewById(R.id.iv_net_display);
		iv_net_display01 = (ImageView) view.findViewById(R.id.iv_net_display01);
		iv_net_display02 = (ImageView) view.findViewById(R.id.iv_net_display02);
		iv_net_display03 = (ImageView) view.findViewById(R.id.iv_net_display03);

		ptr_scroll.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
				new MyAs().execute();
			}
		});

	}

	private void initImageCycleView(View mView) {

		infos = new ArrayList<ImageCycleInfo>();
		for (int i = 0; i < imageUrls.length; i++) {
			ImageCycleInfo info = new ImageCycleInfo();
			info.setImg(imageUrls[i]);
			info.setContent("top-->" + i);
			infos.add(info);
		}

		mImageCycleView.setImageResources(infos, new ImageCycleView.ImageCycleViewListener() {

			@Override
			public void onImageClick(ImageCycleInfo info, int postion,
									 View imageView) {
				// TODO Auto-generated method stub

			}

			@Override
			public ImageView loadImageDisplay(ImageView imageView,
											  ImageCycleInfo info) {
				imageView.setImageResource(info.getImg());
				return imageView;
			}

		});
	}

	@Override
	public void onResume() {
		super.onResume();
		mImageCycleView.startImageCycle();

	}

	@Override
	public void onPause() {
		super.onPause();
		mImageCycleView.pushImageCycle();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mImageCycleView.pushImageCycle();

	}

	class MyAs extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			ptr_scroll.onRefreshComplete();
			super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}

	}
}
