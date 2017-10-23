package zhu.com.sortingdata.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import zhu.com.sortingdata.R;

public class FragmentTab03 extends BaseFragment {

	private View view;
	private TextView tv_context;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_main, null);
		initView();
		return view;
	}

	private void initView() {
		tv_context = (TextView) view.findViewById(R.id.tv_context);
		tv_context.setText("222222222这还是啥也没有");
	}

}
