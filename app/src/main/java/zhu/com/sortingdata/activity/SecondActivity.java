package zhu.com.sortingdata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhu.com.sortingdata.R;

public class SecondActivity extends AppCompatActivity {

    private MySportView view;

    private Button sportBtn, sportDownBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_second);
        initview();
    }

    private void initview() {
        view = (MySportView) findViewById(R.id.sport_view);
        view.setmTargetPercent(30);
        sportBtn = (Button) findViewById(R.id.sport_btn);
        sportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setNumber(40);
            }
        });
        sportDownBtn = (Button) findViewById(R.id.sport_down_btn);
        sportDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setNumber(20);
            }
        });

    }
}
