package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import cn.edu.bupt.sdmda.listviewandrecycleview.adapter.MyRecycleAdapter;
import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class RecycleViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    MyRecycleAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        initView();
    }

    void initView(){
        toolbar = findViewById(R.id.rv_toolbar);
        rv = findViewById(R.id.recycleview);

        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        ma = new MyRecycleAdapter(this, R.layout.simple_listview, SimpleListViewActivity.getData());
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        switch (v.getId()){
            case R.id.custom_listview:
                getMenuInflater().inflate(R.menu.main, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        switch (item.getItemId()){
            case R.id.menu_add:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ma.addData(info.position);
                ma.notifyItemInserted(0);
                break;
            case R.id.menu_del:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ma.delData(info.position);
                ma.notifyItemRemoved(info.position);
                break;
        }
        return true;
    }
}
