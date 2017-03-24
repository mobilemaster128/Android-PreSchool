package com.check.preschool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Freeware Sys on 9/8/2016.
 */
public class NumberPadButton extends LinearLayout {
    /** Backup of clickable property. Used for accessibility. */
    private boolean mWasClickable;

    public interface OnPressedListener {
        public void onPressed(View view, boolean pressed);
    }

    private OnPressedListener mOnPressedListener;

    public void setOnPressedListener(OnPressedListener onPressedListener) {
        mOnPressedListener = onPressedListener;
    }

    public NumberPadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumberPadButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if (mOnPressedListener != null) {
            mOnPressedListener.onPressed(this, pressed);
        }
    }
}
