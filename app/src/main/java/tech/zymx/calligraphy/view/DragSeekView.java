package tech.zymx.calligraphy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import tech.zymx.calligraphy.Constant;

/**
 * Created by kevinzhan on 2018/2/2.
 */


public class DragSeekView extends View {
    private OnDragListener mOnDragListener;
    private float mLastX = 0;
    private Paint mPaint;
    private List<Path> mCirclePaths;
    private float mLeft = -1;
    private float mRight = -1;
    private float mShortR = Constant.INIT_RADIUS;

    public OnDragListener getOnDragListener() {
        return mOnDragListener;
    }

    public void setOnDragListener(OnDragListener onDragListener) {
        mOnDragListener = onDragListener;
    }

    public DragSeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mCirclePaths = new ArrayList<>();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        if (mLeft < 0 || mRight < 0) {
            mLeft = getWidth() / 2 - Constant.INIT_RADIUS;
            mRight = getWidth() / 2 + Constant.INIT_RADIUS;
        }

        canvas.drawColor(Color.TRANSPARENT);

        List<Path> paths = mCirclePaths;
        if (paths == null || paths.isEmpty()) {
            paths = calcCirclePaths();
        }
        for (int i = 0; i < paths.size(); i++) {
            if (i % 2 == 0) {
                mPaint.setColor(0xFF666666);
            } else {
                mPaint.setColor(0xFFEEEEEE);
            }
            canvas.drawPath(paths.get(i), mPaint);
        }


    }

    public interface OnDragListener {
        public void onDragStart();

        public void onUpdateDragPercent(float index);

        public void onDragDone();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (mOnDragListener == null) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mLastX = event.getX();
                mOnDragListener.onDragStart();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float curX = event.getX();
                float moveLength = curX - mLastX;
                if (Math.abs(moveLength) > 2) {
                    mOnDragListener.onUpdateDragPercent(moveLength / getWidth());
                }
                if (moveLength > 0) {
                    mShortR = Constant.INIT_RADIUS / calcTransformFactor(moveLength);
                    mRight = Constant.INIT_RADIUS * (calcTransformFactor(moveLength) - 1) + getWidth() / 2 + Constant.INIT_RADIUS;
                } else {
                    mShortR = Constant.INIT_RADIUS / calcTransformFactor(-moveLength);
                    mLeft = getWidth() / 2 - Constant.INIT_RADIUS - Constant.INIT_RADIUS * (calcTransformFactor(-moveLength) - 1);
                }

                break;
            }
            case MotionEvent.ACTION_UP: {
                mOnDragListener.onDragDone();
                mLeft = getWidth() / 2 - Constant.INIT_RADIUS;
                mRight = getWidth() / 2 + Constant.INIT_RADIUS;
                mShortR = Constant.INIT_RADIUS;
                break;
            }
            default: {
            }
        }
        calcCirclePaths();
        postInvalidate();
        return true;

    }

    private float calcTransformFactor(float moveDistance) {
        float moveFactor = moveDistance / getWidth() > 1 ? 1 : moveDistance / getWidth();
        float result;
        result = 10 / (10 - 4 * moveFactor);
        return result;
    }

    private List<Path> calcCirclePaths() {
        mCirclePaths.clear();
        for (int i = 0; i < 7; i++) {
            float left = mLeft + 6 * i;
            float radius = mShortR - 6 * i;
            float right = mRight - 6 * i;

            float top = getHeight() / 2 - radius;
            float bottom = top + radius * 2;
            float center = (right + left) / 2;

            Path circlePath = new Path();
            circlePath.moveTo(left, top + radius);
            circlePath.cubicTo(left, top + radius, left, top, center, top);
            circlePath.cubicTo(center, top, right, top, right, top + radius);
            circlePath.cubicTo(right, top + radius, right, bottom, center, bottom);
            circlePath.cubicTo(center, bottom, left, bottom, left, top + radius);

            mCirclePaths.add(circlePath);
        }
        return mCirclePaths;
    }
}
