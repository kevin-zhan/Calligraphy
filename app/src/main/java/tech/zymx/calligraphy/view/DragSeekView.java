package tech.zymx.calligraphy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by kevinzhan on 2018/2/2.
 */


public class DragSeekView extends View {
    private OnDragListener mOnDragListener;
    private float mLastX = 0;

    public OnDragListener getOnDragListener() {
        return mOnDragListener;
    }

    public void setOnDragListener(OnDragListener onDragListener) {
        mOnDragListener = onDragListener;
    }

    public DragSeekView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
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
                break;
            }
            case MotionEvent.ACTION_UP: {
                mOnDragListener.onDragDone();
                break;
            }
            default: {
            }
        }
        return true;

    }

}
