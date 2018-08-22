package tech.zymx.calligraphy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.R;
import tech.zymx.calligraphy.activity.CopybookSinglePageActivity;
import tech.zymx.calligraphy.model.ImageUrlProvider;
import tech.zymx.calligraphy.utils.CalligraphyUtils;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class CopybookContentAdapter extends RecyclerView.Adapter<CopybookContentAdapter.InnerViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private ImageUrlProvider mImageUrlProvider;


    public CopybookContentAdapter(Context context, ImageUrlProvider provider) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mImageUrlProvider = provider;
    }

    @Override
    public void onBindViewHolder(InnerViewHolder vh, final int index) {
        CalligraphyUtils.setImageUrl(vh.mImageView, mImageUrlProvider.getImageUrl(index), mContext);
        vh.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CopybookSinglePageActivity.class);
                intent.putExtra(Constant.IMAGE_URL, mImageUrlProvider.getImageUrl(index));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrlProvider.getImageCount();
    }

    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InnerViewHolder(mLayoutInflater.inflate(R.layout.view_copybook_page, parent, false));
    }

    class InnerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_pic)
        ImageView mImageView;

        InnerViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ImageUrlProvider getImageUrlProvider() {
        return mImageUrlProvider;
    }
}


