package com.check.preschool;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignActivity extends AppCompatActivity {

    @BindView(R.id.date) TextView txtDate;
    @BindView(R.id.time) TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);

        mClockHandler.sendEmptyMessage(0);
    }

    Handler mClockHandler = new Handler() {
        public void handleMessage(Message msg) {
            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            txtTime.setText(today.format("%l:%M %p").toUpperCase());
            txtDate.setText(today.format("%e %A, %B %Y"));
            mClockHandler.sendEmptyMessageDelayed(0, 60000);
        }
    };
}
