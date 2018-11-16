package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;
import cn.edu.bupt.sdmda.listviewandrecycleview.adapter.StaggerAdapter;

public class StaggerRecyclerViewActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stagger_recycler_view);
        initView();
    }

    void initView(){
        rv = findViewById(R.id.rv);
        StaggeredGridLayoutManager sglm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(sglm);
        rv.setAdapter(new StaggerAdapter(this, R.layout.image_listview, 10));
    }
}
