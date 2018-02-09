package tech.zymx.calligraphy.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.CalligraphyUtils;
import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.R;
import tech.zymx.calligraphy.adapter.CopybookContentAdapter;
import tech.zymx.calligraphy.view.DragSeekView;

public class CopybookActivity extends AppCompatActivity {

    @BindView(R.id.main_recycleView)
    RecyclerView mRecyclerView;
    @BindView(R.id.drag_seek_view)
    DragSeekView mDragSeekView;
    @BindView(R.id.preview_holder)
    RelativeLayout mPreviewHolder;
    @BindView(R.id.preview_pic)
    ImageView mPreviewPic;
    @BindView(R.id.progress_text)
    TextView mProgressText;

    private CopybookContentAdapter mAdapter;
    private int mJumpPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copybook);
        ButterKnife.bind(this);
        initRecyvleView();
        mDragSeekView.setOnDragListener(new DragSeekView.OnDragListener() {
            @Override
            public void onDragStart() {
                mPreviewHolder.setVisibility(View.VISIBLE);
                mJumpPosition = getCurrentPosition() + 2;
                mPreviewPic.setImageResource(getDrawableAtPosition(mJumpPosition));
                mProgressText.setText(getResources().getString(R.string.progress_text, mJumpPosition + 1, mRecyclerView.getAdapter().getItemCount()));
                showPreview();
            }

            @Override
            public void onUpdateDragPercent(float percent) {
                int targetPosition;
                int jumpLength = (int) (mRecyclerView.getAdapter().getItemCount() * Math.abs(percent));
                if (percent > 0) {
                    targetPosition = getCurrentPosition() - jumpLength > -1 ? getCurrentPosition() - jumpLength : 0;
                    mPreviewPic.setImageResource(getDrawableAtPosition(targetPosition + 2 < mRecyclerView.getAdapter().getItemCount() ? targetPosition + 2 : targetPosition));
                } else {
                    targetPosition = getCurrentPosition() + jumpLength > mRecyclerView.getAdapter().getItemCount() - 1 ? mRecyclerView.getAdapter().getItemCount() - 1 : getCurrentPosition() +
                            jumpLength;
                    mPreviewPic.setImageResource(getDrawableAtPosition(targetPosition - 2 < 0 ? 0 : targetPosition - 2));
                }

                mJumpPosition = targetPosition;
                mProgressText.setText(getResources().getString(R.string.progress_text, mJumpPosition + 1, mRecyclerView.getAdapter().getItemCount()));
            }

            @Override
            public void onDragDone() {
                if (mJumpPosition != getCurrentPosition()) {
                    scrollRecycleViewToPosition(mJumpPosition);
                }
                hidePreview();
            }
        });
    }

    private void initRecyvleView() {
        String image_name;
        int index = getIntent().getIntExtra("index", 0);
        String prefix = Constant.PREFIXES[index];
        int num = Constant.IMAGE_NUMBER[index];
        List<String> pageNames = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            image_name = prefix + String.valueOf(i + 1);
            pageNames.add(image_name);
        }
        mAdapter = new CopybookContentAdapter(this, pageNames);
        mRecyclerView.setAdapter(mAdapter);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private int getDrawableAtPosition(int position) {
        String imageName = mAdapter.getPageNames().get(position);
        return CalligraphyUtils.getDrawableID(this, imageName);
    }

    private int getCurrentPosition() {
        LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        return llm.findFirstVisibleItemPosition();
    }

    private void scrollRecycleViewToPosition(int position) {
        RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        smoothScroller.setTargetPosition(position);
        if (Math.abs(position-getCurrentPosition())>30) {
            mRecyclerView.scrollToPosition(position);
        } else {
            mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
        }
    }

    private void showPreview() {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mPreviewHolder, "alpha", 0f, 1f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mPreviewPic, "scaleX", 0, 1);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mPreviewPic, "scaleY", 0, 1);
        AnimatorSet showAnimSet = new AnimatorSet();
        showAnimSet.play(alphaAnim)
                   .with(scaleXAnim)
                   .with(scaleYAnim);
        showAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        showAnimSet.setDuration(500);
        showAnimSet.start();
    }

    private void hidePreview() {
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mPreviewHolder, "alpha", 1f, 0f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(mPreviewPic, "scaleX", 1, 0);
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(mPreviewPic, "scaleY", 1, 0);
        AnimatorSet hideAnimSet = new AnimatorSet();
        hideAnimSet.play(alphaAnim)
                   .with(scaleXAnim)
                   .with(scaleYAnim);
        hideAnimSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationStart(animation);
                mPreviewHolder.setVisibility(View.GONE);
            }
        });
        hideAnimSet.setDuration(500);
        hideAnimSet.start();
    }
}
