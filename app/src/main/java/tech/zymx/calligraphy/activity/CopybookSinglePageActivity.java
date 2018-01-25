package tech.zymx.calligraphy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.CalligraphyUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copybook_single_page);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("image_name");
        if (!TextUtils.isEmpty(imageName)) {
            mContentPic.setImageResource(CalligraphyUtils.getDrawableID(this, imageName));
        } else {
            Toast.makeText(this, "Can not find the image~", Toast.LENGTH_SHORT).show();
        }

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

                //动画逻辑
                if (mCoverView.isLocked()) {
                    hideOperationButtonWithAnimation();
                    mPracticeThis.setImageDrawable(roundedPracticeDoneDrawable);
                } else {
                    showOperationButtonWithAnimation();
                    mPracticeThis.setImageDrawable(roundedPracticeStartDrawable);
                }
                mCoverView.invalidate();
            }
        });

        mGoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 跳到下一个字
            }
        });

        mGoPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo 跳到上一个字
            }
        });


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
        hideAnimSet.setDuration(500);
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


}
