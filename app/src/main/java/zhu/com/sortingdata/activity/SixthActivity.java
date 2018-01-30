package zhu.com.sortingdata.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import zhu.com.sortingdata.R;


/**
 * 动画-进度条
 * Created by Administrator on 2017/11/15.
 */
public class SixthActivity extends Activity {

    MyChangeArcView myChangeArcView ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_six);
        initview();
    }

    private void initview() {
        myChangeArcView = (MyChangeArcView) findViewById(R.id.myChangeArcView);
        myChangeArcView.setNum(80);
    }

}
