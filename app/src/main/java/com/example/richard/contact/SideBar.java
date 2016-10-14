package com.example.richard.contact;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SideBar extends View {

    private LetterChangedListener mLetterChangedListener;
    private static final String[] LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "#"};

    private int mChooseIndex = -1;
    private Paint mPaint = new Paint();

    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //can be better
    public void setChoosedIndex(String letter) {
        for (int i = 0; i < LETTERS.length; i++) {
            if (LETTERS[i].equals(letter)) {
                mChooseIndex = i;
                invalidate();
                break;
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / LETTERS.length;

        for (int i = 0; i < LETTERS.length; i++) {
            mPaint.setColor(Color.BLACK);
            mPaint.setTypeface(Typeface.DEFAULT);
            mPaint.setAntiAlias(true);
            mPaint.setTextSize(UiUtil.dip2px(getContext(), 10));
            if (i == mChooseIndex) {
                mPaint.setColor(Color.RED);
                mPaint.setFakeBoldText(true);
            }
            int xPos = (int) (width / 2 - mPaint.measureText(LETTERS[i]) / 2);
            int yPos = (i + 1) * singleHeight;
            canvas.drawText(LETTERS[i], xPos, yPos, mPaint);
            mPaint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        // according to getY() to get index
        final int index = (int) (event.getY() / getHeight() * LETTERS.length);
        final int oldChooseIndex = mChooseIndex;
        final LetterChangedListener letterChangeListener = mLetterChangedListener;
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                break;
            default:
                if (index >= 0 && index < LETTERS.length) {
                    if (oldChooseIndex != index) {
                        if (letterChangeListener != null) {
                            letterChangeListener.onLetterChanged(LETTERS[index]);
                        }
                    }
                    mChooseIndex = index;
                    invalidate();
                }
                break;
        }
        return true;
    }

    public void setLetterChangedListener(LetterChangedListener lcListener) {
        this.mLetterChangedListener = lcListener;
    }

    public interface LetterChangedListener {
        void onLetterChanged(String s);
    }
}
