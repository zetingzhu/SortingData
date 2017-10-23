package zhu.com.sortingdata.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhu.com.sortingdata.R;


public class CustomBar extends LinearLayout implements OnClickListener {
	private Context context;
	private View View;
	private LayoutInflater inflater;
	private LinearLayout layout;
	//选中的功能
	private int index = 0;
	//默认背景图片
	private int mImageViews[] = null ;
	//选中时背景图片
	private int selectViews[] = null ;
	//选中是的接口
	SelectListener mlistener;

	public CustomBar(Context context) {
		super(context, null);
	}

	public CustomBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;

	}

	private void initView() {
		inflater = LayoutInflater.from(context);
		View = inflater.inflate(R.layout.custom_bar, null);
		layout = (LinearLayout) View.findViewById(R.id.custom_bar_layout);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		//循环设置监听
		for (int i = 0; i < layout.getChildCount(); i++) {
			layout.getChildAt(i).setOnClickListener(this);
		}
		setChange(0);
	}

	/**
	 * 设置显示背景图片和选中图片
	 */
	public void setImageViewBg(int imgs[] , int selectImgs[]){
		this.mImageViews = imgs ;
		this.selectViews = selectImgs ;
		initView();
		addView(View);
	}

	//改变背景图片和字体颜色(表示选中)
	public void setChange(int i) {
		LinearLayout linearLayout;
		ImageView imageView;
		TextView textView;
		for (int j = 0; j < layout.getChildCount(); j++) {
			linearLayout = (LinearLayout) layout.getChildAt(j);
			imageView = (ImageView) linearLayout.getChildAt(0);
			textView = (TextView) linearLayout.getChildAt(1);
			if (j == i) {
				imageView.setImageResource(selectViews[i]);
				textView.setTextColor(getResources().getColor(R.color.red));
			} else {
				imageView.setImageResource(mImageViews[i]);
				textView.setTextColor(getResources().getColor(R.color.white));
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.custom_bar_home:
				index = 0;
				break;
			case R.id.custom_bar_antenna:
				index = 1;
				break;
			case R.id.custom_bar_stting:
				index = 2;
				break;
		}
		setChange(index);
		//接口触发
		if (mlistener != null) {
			mlistener.getOnclick(index);
		}

	}
	//接口调用
	public void setSelectListener(SelectListener mlistener) {
		this.mlistener = mlistener;
	}
	//初始化接口
	public interface SelectListener {
		void getOnclick(int i);
	}


}
