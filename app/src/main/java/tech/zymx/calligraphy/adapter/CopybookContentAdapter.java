package tech.zymx.calligraphy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.activity.CopybookSinglePageActivity;
import tech.zymx.calligraphy.CalligraphyUtils;
import tech.zymx.calligraphy.R;

/**
 * Created by kevinzhan on 2018/1/23.
 */

public class CopybookContentAdapter extends RecyclerView.Adapter<CopybookContentAdapter.InnerViewHolder> {
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private List<String> mPageNames = new ArrayList<>();


    public CopybookContentAdapter(Context context, List<String> pageNames) {
        super();
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mPageNames = pageNames;
    }

    @Override
    public void onBindViewHolder(InnerViewHolder vh, final int index) {
        final String image_name = mPageNames.get(index);
        vh.mImageView.setImageResource(CalligraphyUtils.getDrawableID(mContext, image_name));
        vh.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CopybookSinglePageActivity.class);
                intent.putExtra("image_name",image_name);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPageNames.size();
    }

    @Override
    public InnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InnerViewHolder(mLayoutInflater.inflate(R.layout.view_copybook_page, parent, false));
    }

    class InnerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_pic)
        ImageView mImageView;

        public InnerViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public List<String> getPageNames() {
        return mPageNames;
    }

    public void setPageNames(List<String> pageNames) {
        mPageNames = pageNames;
    }

}

