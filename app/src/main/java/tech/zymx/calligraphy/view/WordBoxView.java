package tech.zymx.calligraphy.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.parseColor("#aaffffff"));
        if (isLocked()) {
            canvas.drawRect(0, width, width, height, mPaint);
        } else {
            canvas.drawRect(0, 0, width, width, mPaint);
        }

        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0, 0, width, width, mPaint);
        canvas.drawLine(width / 2, 0, width / 2, width, mPaint);
        canvas.drawLine(0, width / 2, width, width / 2, mPaint);
        canvas.drawLine(0, 0, width, width, mPaint);
        canvas.drawLine(width, 0, 0, width, mPaint);
    }

}
