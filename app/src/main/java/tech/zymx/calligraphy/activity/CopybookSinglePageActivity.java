package tech.zymx.calligraphy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.R;
import tech.zymx.calligraphy.utils.AnimationUtils;
import tech.zymx.calligraphy.utils.CalligraphyUtils;
import tech.zymx.calligraphy.view.TapStopHorizontalScrollView;
import tech.zymx.calligraphy.view.TapStopScrollView;
import tech.zymx.calligraphy.view.WordBoxView;

public class CopybookSinglePageActivity extends AppCompatActivity {

    public enum ViewSideEnum {
        LEFT,
        RIGHT,
    }

    private static final int EXPAND_SCREEN_SIZE = 200;


    @BindView(R.id.content_pic)
    ImageView mContentPic;
    @BindView(R.id.scroll_view)
    TapStopScrollView mScrollView;
    @BindView(R.id.cover_view)
    WordBoxView mCoverView;
    @BindView(R.id.go_next)
    ImageView mGoNext;
    @BindView(R.id.go_pre)
    ImageView mGoPre;
    @BindView(R.id.practice_this)
    ImageView mPracticeThis;
    @BindView(R.id.end_foot)
    TextView mEndFootView;
    @BindView(R.id.horizontal_scroll_view)
    TapStopHorizontalScrollView mHorizontalScrollView;
    @BindView(R.id.adjust_horizontal_position)
    ImageView mAdjustHoriPosButton;

    private List<Integer> mPositionList = new ArrayList<>();
    private String mImageName = "";
    private long mLastLockTime;
    private boolean mIsInAnim = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copybook_single_page);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String imageName = intent.getStringExtra(Constant.IMAGE_NAME_SIGN);
        if (!TextUtils.isEmpty(imageName)) {
            mImageName = imageName;
        } else {
            Toast.makeText(this, R.string.image_not_found, Toast.LENGTH_SHORT).show();
            return;
        }

        initView();
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (wm != null) {
            wm.getDefaultDisplay().getSize(point);
        }
        mContentPic.getLayoutParams().width = point.x;
        mEndFootView.getLayoutParams().width = point.x + EXPAND_SCREEN_SIZE;
        mHorizontalScrollView.setCanScroll(false);

        String integerListStr = getSharedPreferences().getString(imageName, "");
        mPositionList.clear();
        mPositionList.addAll(CalligraphyUtils.parseStringToIntegerList(integerListStr));
    }

    private void initView() {

        mContentPic.setImageResource(CalligraphyUtils.getDrawableID(this, mImageName));

        mPracticeThis.setImageResource(R.drawable.practice_word);
        mPracticeThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePracticeStatus();
            }
        });

        mGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!smoothScrollToNextWord()) {
                    Toast.makeText(CopybookSinglePageActivity.this, R.string.practice_end, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGoPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!smoothScrollToPreWord()) {
                    Toast.makeText(CopybookSinglePageActivity.this, R.string.prictice_start, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAdjustHoriPosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHorizontalScrollView.setCanScroll(!mHorizontalScrollView.isCanScroll());
            }
        });
    }

    private boolean smoothScrollToNextWord() {
        int curPosition = mScrollView.getScrollY();
        for (int i = 0; i < mPositionList.size(); i++) {
            if (mPositionList.get(i) > curPosition) {
                mScrollView.smoothScrollTo(0, mPositionList.get(i));
                return true;
            }
        }
        return false;
    }

    private boolean smoothScrollToPreWord() {
        int curPosition = mScrollView.getScrollY();
        for (int i = mPositionList.size() - 1; i > -1; i--) {
            if (mPositionList.get(i) < curPosition) {
                mScrollView.smoothScrollTo(0, mPositionList.get(i));
                return true;
            }
        }
        return false;
    }

    private void recordScrollPosition(boolean isLocked) {
        if (isLocked) {
            mLastLockTime = CalligraphyUtils.getTimeStamp();
            return;
        }
        long nowTimeStamp = CalligraphyUtils.getTimeStamp();
        if (nowTimeStamp - mLastLockTime < Constant.EFFECTIVE_TIME) {
            //未到有效的记录时间
            return;
        }
        int curPostion = mScrollView.getScrollY();
        if (mPositionList.contains(curPostion)) {
            return;
        }
        for (int i = 0; i < mPositionList.size(); i++) {
            if (curPostion > mPositionList.get(i) - 300 && curPostion < mPositionList.get(i) + 300) {
                mPositionList.remove(i);
                mPositionList.add(i, curPostion);
                return;
            }
        }
        mPositionList.add(curPostion);
        Collections.sort(mPositionList);
    }

    private void changePracticeStatus() {
        mCoverView.setLocked(!mCoverView.isLocked());
        mScrollView.setCanScroll(!mCoverView.isLocked());

        recordScrollPosition(mCoverView.isLocked());

        //动画逻辑
        if (mCoverView.isLocked()) {
            hideOperationButtonWithAnimation();
            AnimationUtils.changeDrawableWithAnimation(mPracticeThis, R.drawable.practice_done);
            mAdjustHoriPosButton.setVisibility(View.VISIBLE);
        } else {
            showOperationButtonWithAnimation();
            AnimationUtils.changeDrawableWithAnimation(mPracticeThis, R.drawable.practice_word);
            mAdjustHoriPosButton.setVisibility(View.GONE);
            mHorizontalScrollView.scrollTo(EXPAND_SCREEN_SIZE / 2, mHorizontalScrollView.getScrollY());
        }
        mCoverView.invalidate();
    }

    private void hideOperationButtonWithAnimation() {
        AnimationUtils.hideViewToCenterWithAnimation(mGoNext, ViewSideEnum.RIGHT);
        AnimationUtils.hideViewToCenterWithAnimation(mGoPre, ViewSideEnum.LEFT);
    }

    private void showOperationButtonWithAnimation() {
        AnimationUtils.showViewToSideWithAnimation(mGoNext, ViewSideEnum.RIGHT);
        AnimationUtils.showViewToSideWithAnimation(mGoPre, ViewSideEnum.LEFT);
    }





    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences("scroll_offset", MODE_PRIVATE);
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        String integerListStr = CalligraphyUtils.convertIntegerListToString(mPositionList);
        getSharedPreferences().edit().putString(mImageName, integerListStr).apply();
    }

    @Override
    public void  onWindowFocusChanged(boolean focus) {
        if (focus) {
            mHorizontalScrollView.scrollTo(EXPAND_SCREEN_SIZE / 2, 0);
            smoothScrollToNextWord();
        }
    }

    @Override
    public void onBackPressed() {
        if (mCoverView.isLocked()) {
            changePracticeStatus();
            return;
        }
        super.onBackPressed();
    }

}
