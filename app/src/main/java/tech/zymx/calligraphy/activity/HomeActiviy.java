package tech.zymx.calligraphy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.R;

/**
 * Created by kevinzhan on 2018/2/1.
 */

public class HomeActiviy extends AppCompatActivity {
    private List<String> mCopybookList = new ArrayList<>();

    @BindView(R.id.home_recycleView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mCopybookList.add("多宝塔碑");
        mCopybookList.add("九成宫醴泉");
        initViews();
    }

    private void initViews() {
        SimpleAdapter adapter = new SimpleAdapter();
        mRecyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }

    class SimpleAdapter extends RecyclerView.Adapter<SimpleViewHolder> {
        public SimpleAdapter() {

        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new SimpleViewHolder(getLayoutInflater().inflate(R.layout.item_copybook_name, parent, false));
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder vh, final int index) {
            vh.mTextView.setText(mCopybookList.get(index));
            vh.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActiviy.this, CopybookActivity.class);
                    intent.putExtra("index", index);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mCopybookList.size();
        }
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.copybook_name)
        TextView mTextView;
        @BindView(R.id.card_view)
        CardView mCardView;

        public SimpleViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

}
