package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cn.edu.bupt.sdmda.listviewandrecycleview.adapter.MyAdapter;
import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class CustomListViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView lv;
    MyAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_view);
        initView();
    }

    void initView(){
        toolbar = findViewById(R.id.lv_toolbar);
        lv = findViewById(R.id.custom_listview);


        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        ma = new MyAdapter(this, R.layout.simple_listview, SimpleListViewActivity.getData());
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CustomListViewActivity.this,
                        parent.toString()+"\n\n"+
                                view.toString()+"\n\n"+
                                position+"\n\n"+id+"\n\n",
                        Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(lv);

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
                ma.notifyDataSetChanged();
                break;
            case R.id.menu_del:
                ma.delData(0);
                ma.notifyDataSetChanged();
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
                ma.notifyDataSetChanged();
                break;
            case R.id.menu_del:
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                ma.delData(info.position);
                ma.notifyDataSetChanged();
                break;
        }
        return true;
    }
}
