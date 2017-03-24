package com.check.preschool;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NumberPadButton.OnPressedListener {

    @BindView(R.id.date) TextView txtDate;
    @BindView(R.id.time) TextView txtTime;
    @BindView(R.id.pinnumber) TextView txtPinNumber;
    @BindView(R.id.submit) TextView btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ActionBar ab = getSupportActionBar();
        setupNumpad();
        mClockHandler.sendEmptyMessage(0);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPinNumber.setText("");
                Intent i = new Intent(MainActivity.this, SignActivity.class);
                startActivity(i);
            }
        });
    }

    private void setupNumpad() {
        final String[] letterIds = new String[] {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ", "", ""};

        final int[] mNumpadIds = new int[] {R.id.zero, R.id.one, R.id.two, R.id.three, R.id.four, R.id.five,
                R.id.six, R.id.seven, R.id.eight, R.id.nine, R.id.leftblank,  R.id.right_blank};

        NumberPadButton numpad_Key;
        TextView numberView;
        TextView lettersView;

        String numberString;

        for (int i = 0; i < mNumpadIds.length; i++) {
            numpad_Key = (NumberPadButton) findViewById(mNumpadIds[i]);
            numpad_Key.setOnPressedListener(this);
            numberView = (TextView) numpad_Key.findViewById(R.id.numpad_key_number);
            lettersView = (TextView) numpad_Key.findViewById(R.id.numpad_key_letters);

            if (mNumpadIds[i] == R.id.leftblank || mNumpadIds[i] == R.id.right_blank) {

            }
            else {
                numberString = String.format("%d", i);
                numberView.setText(numberString);
                lettersView.setText(letterIds[i]);
            }
        }
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

    @Override
    public void onPressed(View view, boolean pressed) {
        if (pressed) {
            int keyCode;
            int id = view.getId();
            if (id == R.id.one) {
                keyCode = KeyEvent.KEYCODE_1;
            } else if (id == R.id.two) {
                keyCode = KeyEvent.KEYCODE_2;
            } else if (id == R.id.three) {
                keyCode = KeyEvent.KEYCODE_3;
            } else if (id == R.id.four) {
                keyCode = KeyEvent.KEYCODE_4;
            } else if (id == R.id.five) {
                keyCode = KeyEvent.KEYCODE_5;
            } else if (id == R.id.six) {
                keyCode = KeyEvent.KEYCODE_6;
            } else if (id == R.id.seven) {
                keyCode = KeyEvent.KEYCODE_7;
            } else if (id == R.id.eight) {
                keyCode = KeyEvent.KEYCODE_8;
            } else if (id == R.id.nine) {
                keyCode = KeyEvent.KEYCODE_9;
            } else if (id == R.id.zero) {
                keyCode = KeyEvent.KEYCODE_0;
            } else {
                return;
            }

            if (txtPinNumber.length() == 5)
                txtPinNumber.setText("");
            KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
            txtPinNumber.onKeyDown(keyCode, event);

            // If the cursor is at the end of the text we hide it.
            final int length = txtPinNumber.length();
            if (length == txtPinNumber.getSelectionStart() && length == txtPinNumber.getSelectionEnd()) {
                txtPinNumber.setCursorVisible(false);
            }
        }
    }
}
