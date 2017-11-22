package zhu.com.sortingdata.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import zhu.com.sortingdata.R;

public class MainActivityTest extends AppCompatActivity {

    private Button secondBtn, thirdBtn, fourBtn,fiveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_main);
        initiew();

    }

    private void initiew() {
        secondBtn = (Button) findViewById(R.id.second_btn);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });
        thirdBtn = (Button) findViewById(R.id.third_btn);
        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
            }
        });
        fourBtn = (Button) findViewById(R.id.four_btn);
        fourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FourActivity.class));
            }
        });

        fiveBtn = (Button) findViewById(R.id.five_btn);
        fiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FiveActivity.class));
            }
        });

    }
}
