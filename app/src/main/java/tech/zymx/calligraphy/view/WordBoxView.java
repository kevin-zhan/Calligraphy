package tech.zymx.calligraphy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class WordBoxView extends View {

    private Paint mPaint;
    private boolean mLocked = false;

    public boolean isLocked() {
        return mLocked;
    }

    public void setLocked(boolean locked) {
        mLocked = locked;
    }

    public WordBoxView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStrokeWidth(4);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Override
    protected void onDraw(Canvas canvas) {
        if (mPaint == null) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        if (isLocked()) {
            mPaint.setColor(Color.parseColor("#aaffffff"));
            canvas.drawRect(0, width, width, height, mPaint);
        } else {
            mPaint.setColor(Color.parseColor("#aaffffff"));
            canvas.drawRect(0, 0, width, width, mPaint);
        }

        mPaint.setColor(Color.RED);
        float[] pts = {0, 0, width, 0, width, width, 0, width, 0, 0};
        canvas.drawLines(pts, mPaint);
        canvas.drawLine(width / 2, 0, width / 2, width, mPaint);
        canvas.drawLine(0, width / 2, width, width / 2, mPaint);
        canvas.drawLine(0, 0, width, width, mPaint);
        canvas.drawLine(width, 0, 0, width, mPaint);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastX = x;
                mLastY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = Math.abs(mLastX - x);
                int deltaY = Math.abs(mLastY - y);
                if (deltaX > 5 || deltaY > 5) {
                    return true;
                }
                break;

            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default: {
                break;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public void onMeasure(int a, int b) {
        super.onMeasure(a, a);
    }
}
