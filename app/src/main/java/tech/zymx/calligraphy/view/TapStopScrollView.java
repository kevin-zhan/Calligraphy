package tech.zymx.calligraphy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class TapStopScrollView extends ScrollView {
    private boolean canScroll = true;
    public TapStopScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isCanScroll() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (canScroll) {
            return super.onTouchEvent(ev);
        }
        return false;
    }
}
