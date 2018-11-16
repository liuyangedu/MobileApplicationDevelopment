package cn.edu.bupt.sdmda.listviewandrecycleview.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import cn.edu.bupt.sdmda.listviewandrecycleview.adapter.MyRecyclerAdapter;
import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class RecyclerViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    MyRecyclerAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }

    void initView(){
        toolbar = findViewById(R.id.rv_toolbar);
        rv = findViewById(R.id.recycleview);

        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        ma = new MyRecyclerAdapter(this, R.layout.simple_listview,
                SimpleListViewActivity.getData());

        rv.setAdapter(ma);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        rv.setItemAnimator(defaultItemAnimator);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add:
                ma.addData();
                ma.notifyItemInserted(0);
                break;
            case R.id.menu_del:
                ma.delData(0);
                ma.notifyItemRemoved(0);
                break;
        }
        return true;
    }

}
