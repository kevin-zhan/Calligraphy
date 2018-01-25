package tech.zymx.calligraphy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.adapter.CopybookContentAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_recycleView)
    RecyclerView mRecyclerView;
    private CopybookContentAdapter mAdapter;

    private final String DBTB_PRIFIX = "dbtb_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyvleView();
    }

    private void initRecyvleView() {
        String image_name;
        List<String> pageNames = new ArrayList<>();
        for (int i = 0; i < 208; i++) {
            image_name = DBTB_PRIFIX + String.valueOf(i + 1);
            pageNames.add(image_name);
        }
        mAdapter = new CopybookContentAdapter(this, pageNames);
        mRecyclerView.setAdapter(mAdapter);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
