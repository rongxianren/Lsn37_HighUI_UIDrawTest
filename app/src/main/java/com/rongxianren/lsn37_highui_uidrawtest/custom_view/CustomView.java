package com.rongxianren.lsn37_highui_uidrawtest.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wty on 2017/2/18.
 */
public class CustomView extends ViewGroup {

    private final int OFFSET = 80;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        setWillNotDraw();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int width = 0;
        int height = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = view.getLayoutParams();

            int widSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            int heighSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);

            measureChild(view, widSpec, heighSpec);
        }


        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    int tempWidth = OFFSET * i + getChildAt(i).getMeasuredWidth();
                    width = Math.max(width, tempWidth);
                }
                break;
        }

        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    height = height + getChildAt(i).getMeasuredHeight();
                }
                break;
        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int mLeft = 0;
        int mTop = 0;
        int mRight = 0;
        int mBottom = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            mLeft = i * OFFSET;
            mRight = mLeft + getChildAt(i).getMeasuredWidth();
            mBottom = mTop + getChildAt(i).getMeasuredHeight();
            getChildAt(i).layout(mLeft, mTop, mRight, mBottom);

            mTop = mTop + getChildAt(i).getMeasuredHeight();
        }
    }
}
