package cn.edu.bupt.sdmda.dbdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class ContentActivity extends AppCompatActivity {

    private EditText title, content;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        initView();
        readInfoFromIntent();

    }

    void initView(){
        title =  findViewById(R.id.act_content_title);
        content = findViewById(R.id.act_content_content);
    }

    void readInfoFromIntent(){
        // get Intent
        Intent intent = getIntent();
        // If Extra of Intent is null, means that we pressed "add" button
        // or we click item in the listview, so we should get data from intent
        if (intent.getExtras()!=null) {
            String strTitle = intent.getExtras().getString(
                    MemoContract.MemoTable.COLUMN_NAME_TITLE);
            title.setText(strTitle);
            String strContent = intent.getExtras().getString(
                    MemoContract.MemoTable.COLUMN_NAME_CONTENT);
            content.setText(strContent);
            id = intent.getExtras().getInt(MemoContract.MemoTable._ID);
        } else {
            id = -1;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.content_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                // prepare to return data to MainActivity
                // put data into an Intent
                Intent intent = new Intent();
                intent.putExtra(MemoContract.MemoTable.COLUMN_NAME_TITLE,
                        title.getText().toString());
                intent.putExtra(MemoContract.MemoTable.COLUMN_NAME_CONTENT,
                        content.getText().toString());
                intent.putExtra(MemoContract.MemoTable.COLUMN_NAME_MODTIME,
                        System.currentTimeMillis());
                // if id == -1 means this is a new note
                // or we modified an old one, so we put _ID in Intent
                if(-1!=id) {
                    intent.putExtra(MemoContract.MemoTable._ID, id);
                }
                // set result and finish this activity
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
        return true;
    }
}
