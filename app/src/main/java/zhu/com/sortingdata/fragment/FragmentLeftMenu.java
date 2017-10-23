package zhu.com.sortingdata.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import zhu.com.sortingdata.MainActivity;
import zhu.com.sortingdata.R;

import static zhu.com.sortingdata.R.id.bt_menu_show;
import static zhu.com.sortingdata.R.id.textView;

/**
 * Created by Administrator on 2017/5/12.
 */

public class FragmentLeftMenu extends BaseFragment {
    private View view;
    private Button bt_menu_show ; 
    private TextView textView1,textView2,textView3,textView4 ;
    MainActivity parentActivity ;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        parentActivity = (MainActivity) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.menu_layout, null);
        initView();
        initClick();
        return view;
    }

    private void initClick() {

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.menuOrContent(0);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.menuOrContent(1);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.menuOrContent(2);
            }
        });

        bt_menu_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initView() {
        bt_menu_show = (Button) view.findViewById(R.id.bt_menu_show);
        textView1 = (TextView) view.findViewById(R.id.textView);
        textView2 = (TextView) view.findViewById(R.id.textView2);
        textView3 = (TextView) view.findViewById(R.id.textView3);
    }

}
