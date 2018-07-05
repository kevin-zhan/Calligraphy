package tech.zymx.calligraphy.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

import tech.zymx.calligraphy.activity.CopybookSinglePageActivity;

/**
 * Created by kevinzhan@tencent.com on 2018/7/5
 */
public class AnimationUtils {

    public static void changeDrawableWithAnimation(final ImageView view, final int resId) {
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotationY", 0f, 180);
        ObjectAnimator alphaFadeOutAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator alphaFadeInAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        alphaFadeInAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setRotationY(0);
                view.setImageResource(resId);
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
                view.setClickable(true);
            }
        });
        animatorSet.start();
        view.setClickable(false);
    }

    public static void hideViewToCenterWithAnimation(final View view, CopybookSinglePageActivity.ViewSideEnum viewPlace) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotation", 0f, viewPlace == CopybookSinglePageActivity.ViewSideEnum.LEFT ? 180f : -180f);
        ObjectAnimator transAnim = ObjectAnimator.ofFloat(view, "translationX", view.getTranslationX(), viewPlace == CopybookSinglePageActivity.ViewSideEnum.LEFT ? 150f : -150f);
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
                view.setClickable(true);
            }
        });
        hideAnimSet.start();
        view.setClickable(false);
    }

    public static void showViewToSideWithAnimation(final View view, CopybookSinglePageActivity.ViewSideEnum viewPlace) {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(view, "rotation", viewPlace == CopybookSinglePageActivity.ViewSideEnum.LEFT ? 180f : -180f, 0);
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

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setClickable(true);
            }
        });
        showAnimSet.setDuration(500);
        showAnimSet.start();
        view.setClickable(false);
    }
}
