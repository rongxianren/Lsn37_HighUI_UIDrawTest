package com.rongxianren.lsn37_highui_uidrawtest.flow_cutomview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wty on 2017/2/22.
 */

public class FlowCustomView extends ViewGroup {


    public FlowCustomView(Context context) {
        this(context, null);
    }

    public FlowCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        int width = 0;
        int totalHeight = 0;

        int maxLineHeight = 0;
        int maxLineWidth = 0;
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();

            int childWidthSpace = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            int childHeightSpace = child.getMeasuredHeight() + params.bottomMargin + params.topMargin;

            if (width + childWidthSpace <= widthSize) {
                maxLineHeight = Math.max(childHeightSpace, maxLineHeight);
                width += childWidthSpace;
            } else {///换行
                totalHeight = totalHeight + maxLineHeight;
                maxLineHeight = childHeightSpace;
                maxLineWidth = Math.max(width, childWidthSpace);
                width = childWidthSpace;
            }
        }

        totalHeight = totalHeight + maxLineHeight;
        maxLineWidth = Math.max(width, maxLineWidth);


        if (widthMode == MeasureSpec.EXACTLY) {
            maxLineWidth = widthSize;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            totalHeight = heightSize;
        }

        setMeasuredDimension(maxLineWidth, totalHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        int viewWidth = getMeasuredWidth();

        int childCount = getChildCount();
        int maxLineHeight = 0;
        top = 20;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            int childWidthSpace = childView.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (left + childWidthSpace <= viewWidth) {
                left = left + params.leftMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                maxLineHeight = Math.max(maxLineHeight, childView.getMeasuredHeight());
            } else {////换行
                left = params.leftMargin;
                top = top + maxLineHeight + params.topMargin;
                right = left + childView.getMeasuredWidth();
                bottom = top + childView.getMeasuredHeight();
                maxLineHeight = childView.getMeasuredHeight();
            }
            childView.layout(left, top, right, bottom);
            left = left + childView.getMeasuredWidth() + params.rightMargin;
        }

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
