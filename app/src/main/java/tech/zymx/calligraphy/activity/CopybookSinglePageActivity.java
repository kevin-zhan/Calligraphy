package tech.zymx.calligraphy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.CalligraphyUtils;
import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.R;
import tech.zymx.calligraphy.view.TapStopScrollView;
import tech.zymx.calligraphy.view.WordBoxView;

public class CopybookSinglePageActivity extends AppCompatActivity {
    private enum ViewSideEnum {
        LEFT,
        RIGHT,
    }

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

        String integerListStr = getSharedPreferences().getString(imageName, "");
        mPositionList.clear();
        mPositionList.addAll(CalligraphyUtils.parseStringToIntegerList(integerListStr));
    }

    private void initView() {
        mContentPic.setImageResource(CalligraphyUtils.getDrawableID(this, mImageName));

        final RoundedBitmapDrawable roundedPracticeStartDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.practice_word));
        roundedPracticeStartDrawable.setCornerRadius(50);
        final RoundedBitmapDrawable roundedPracticeDoneDrawable = RoundedBitmapDrawableFactory.create(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.practice_done));
        roundedPracticeDoneDrawable.setCornerRadius(50);

        mPracticeThis.setImageDrawable(roundedPracticeStartDrawable);
        mPracticeThis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCoverView.setLocked(!mCoverView.isLocked());
                mScrollView.setCanScroll(!mCoverView.isLocked());

                recordScrollPosition(mCoverView.isLocked());

                //动画逻辑
                if (mCoverView.isLocked()) {
                    hideOperationButtonWithAnimation();
                    changeDrawableWithAnimation(mPracticeThis, roundedPracticeDoneDrawable);
                } else {
                    showOperationButtonWithAnimation();
                    changeDrawableWithAnimation(mPracticeThis, roundedPracticeStartDrawable);
                }
                mCoverView.invalidate();
            }
        });

        mGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curPosition = mScrollView.getScrollY();
                for (int i = 0; i < mPositionList.size(); i++) {
                    if (mPositionList.get(i) > curPosition) {
                        mScrollView.smoothScrollTo(0, mPositionList.get(i));
                        return;
                    }
                }
                Toast.makeText(CopybookSinglePageActivity.this, "此后暂无尔习过之字", Toast.LENGTH_SHORT).show();
            }
        });

        mGoPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int curPosition = mScrollView.getScrollY();
                for (int i = mPositionList.size() - 1; i > -1; i--) {
                    if (mPositionList.get(i) < curPosition) {
                        mScrollView.smoothScrollTo(0, mPositionList.get(i));
                        return;
                    }
                }
                Toast.makeText(CopybookSinglePageActivity.this, "此即该篇尔所习首字矣", Toast.LENGTH_SHORT).show();
            }
        });
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
            }
        }
        Collections.sort(mPositionList);
    }


    private void hideOperationButtonWithAnimation() {
        hideViewToCenterWithAnimation(mGoNext, ViewSideEnum.RIGHT);
        hideViewToCenterWithAnimation(mGoPre, ViewSideEnum.LEFT);
    }

    private void showOperationButtonWithAnimation() {
        showViewToSideWithAnimation(mGoNext, ViewSideEnum.RIGHT);
        showViewToSideWithAnimation(mGoPre, ViewSideEnum.LEFT);
    }

    private void hideViewToCenterWithAnimation(final View view, ViewSideEnum viewPlace) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotation", 0f, viewPlace == ViewSideEnum.LEFT ? 180f : -180f);
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), viewPlace == ViewSideEnum.LEFT ? 150f : -150f);
        AnimatorSet hideAnimSet = new AnimatorSet();
        hideAnimSet.play(alphaAnim)
                   .with(rotationAnim)
                   .with(transAnim);
        hideAnimSet.setDuration(600);
        hideAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setVisibility(View.GONE);
            }
        });
        hideAnimSet.start();
    }

    private void showViewToSideWithAnimation(final View view, ViewSideEnum viewPlace) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotation", viewPlace == ViewSideEnum.LEFT ? 180f : -180f, 0);
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), 0);
        AnimatorSet showAnimSet = new AnimatorSet();
        showAnimSet.play(alphaAnim)
                   .with(rotationAnim)
                   .with(transAnim);
        showAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                view.setVisibility(View.VISIBLE);
            }
        });
        showAnimSet.setDuration(500);
        showAnimSet.start();
    }

    private void changeDrawableWithAnimation(final ImageView view, final Drawable drawable) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotationY", 0f, 180);
        ObjectAnimator alphaFadeOutAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator alphaFadeInAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaFadeInAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setRotationY(0);
                view.setImageDrawable(drawable);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(rotationAnim)
                   .with(alphaFadeOutAnim)
                   .before(alphaFadeInAnim);
        animatorSet.setDuration(600);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                unblockTouchEvent();
            }
        });
        animatorSet.start();
        blockTouchEvent();
    }

    private SharedPreferences getSharedPreferences() {
        return getSharedPreferences("scroll_offset", MODE_PRIVATE);
    }

    private void blockTouchEvent() {
        mGoPre.setClickable(false);
        mGoNext.setClickable(false);
        mPracticeThis.setClickable(false);
    }

    private void unblockTouchEvent() {
        mGoPre.setClickable(true);
        mGoNext.setClickable(true);
        mPracticeThis.setClickable(true);
    }

    public void onPause() {
        super.onPause();
        String integerListStr = CalligraphyUtils.convertIntegerListToString(mPositionList);
        getSharedPreferences().edit().putString(mImageName, integerListStr).apply();
    }

}
