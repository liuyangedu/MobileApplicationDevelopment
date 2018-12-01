package cn.edu.bupt.sdmda.dbdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    // variable  to access SQLite
    private MemoSQLHelper sqlHelper;
    // custom adapter for listview
    private MemoAdapter ma;

    // constant of add and modify request
    public final int REQUEST_CODE_ADD = 1;
    public final int REQUEST_CODE_MOD = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init SQLHelper
        initSQL();

        initView();
    }

    void initSQL(){
        sqlHelper = new MemoSQLHelper(this);
    }

    void initView(){
        ListView lv = findViewById(R.id.listview);
        ma = new MemoAdapter(this, sqlHelper, R.layout.memo_item);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(this);
        registerForContextMenu(lv);
    }

    // Context menu for items in listview
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context, menu);
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ListView.AdapterContextMenuInfo info =
                (ListView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.listview_delete:
                ma.deleteData(info.position);
                ma.notifyDataSetChanged();
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_add:
                // Start ContentActivity to add a new note
                Intent intent = new Intent(this, ContentActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Get result of ContentActivity
        if (resultCode == Activity.RESULT_OK) {
            // Get title, content, modtime from Intent
            String title = data.getExtras().getString(MemoContract.MemoTable.COLUMN_NAME_TITLE);
            String content  = data.getExtras().getString(MemoContract.MemoTable.COLUMN_NAME_CONTENT);
            long modtime = data.getExtras().getLong(MemoContract.MemoTable.COLUMN_NAME_MODTIME);
            // if it is a new note, just add it
            // if it is a modification of old one, get id and update it
            if(requestCode == REQUEST_CODE_ADD) {
                ma.addMemo(title, content, modtime);;
            } else if (requestCode == REQUEST_CODE_MOD){
                int id = data.getExtras().getInt(MemoContract.MemoTable._ID);
                ma.updateMemo(id, title, content, modtime);
            }
            ma.freshData();
            ma.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, ContentActivity.class);
        Map<String, Object> item = (Map) ma.getItem(position);
        // Put the title, content and id into the Intent
        intent.putExtra(MemoContract.MemoTable.COLUMN_NAME_TITLE,
                (String)item.get(MemoContract.MemoTable.COLUMN_NAME_TITLE));
        intent.putExtra(MemoContract.MemoTable.COLUMN_NAME_CONTENT,
                (String)item.get(MemoContract.MemoTable.COLUMN_NAME_CONTENT));
        intent.putExtra(MemoContract.MemoTable._ID,
                (int)item.get(MemoContract.MemoTable._ID));
        startActivityForResult(intent, REQUEST_CODE_MOD);
    }
}
