package zhu.com.sortingdata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import zhu.com.sortingdata.R;

public class FourActivity extends AppCompatActivity {
    private MyBesselView besselView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_four);
        initview();
    }

    private void initview() {
        besselView = (MyBesselView) findViewById(R.id.my_besselview);
    }
}
