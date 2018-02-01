package tech.zymx.calligraphy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tech.zymx.calligraphy.Constant;
import tech.zymx.calligraphy.R;
import tech.zymx.calligraphy.adapter.CopybookContentAdapter;

public class CopybookActivity extends AppCompatActivity {

    @BindView(R.id.main_recycleView)
    RecyclerView mRecyclerView;

    private final String DBTB_PRIFIX = "dbtb_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copybook);
        ButterKnife.bind(this);
        initRecyvleView();
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
        CopybookContentAdapter adapter = new CopybookContentAdapter(this, pageNames);
        mRecyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(layoutManager);
    }
}
