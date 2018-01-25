package tech.zymx.calligraphy.activity;

import android.content.Intent;
import android.os.Bundle;
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
    @BindView(R.id.content_pic)
    ImageView mContentPic;
    @BindView(R.id.scroll_view)
    TapStopScrollView mScrollView;
    @BindView(R.id.cover_view)
    WordBoxView mCoverView;

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
//
//        mCoverView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                if (mCoverView.isLocked()) {
//                    mCoverView.setLocked(false);
//                    mScrollView.setCanScroll(true);
//                } else {
//                    mScrollView.setCanScroll(false);
//                    mCoverView.setLocked(true);
//                }
//                mCoverView.invalidate();
//                return true;
//            }
//        });

        mCoverView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCoverView.isLocked()) {
                    mCoverView.setLocked(false);
                    mScrollView.setCanScroll(true);
                } else {
                    mScrollView.setCanScroll(false);
                    mCoverView.setLocked(true);
                }
                mCoverView.invalidate();
            }
        });


    }


}
