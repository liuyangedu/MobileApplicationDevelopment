package cn.edu.bupt.sdmda.listviewdemo;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.edu.bupt.sdmda.listviewdemo.data.DataGenerator;

public class ArrayAdapterActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    Button btnAdd, btnDel;
    ListView lv;
    ArrayAdapter<String> aa;
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        initView();
        setupListView();


    }

    void initView() {
        lv = findViewById(R.id.lv);
        btnAdd = findViewById(R.id.btn_add);
        btnDel = findViewById(R.id.btn_del);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add("New Data: " + data.size());
                aa.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(data.size() - 1);
                aa.notifyDataSetChanged();
            }
        });
    }

    void setupListView() {
        data = DataGenerator.genStrList(10);
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, data);
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, parent.toString() + "\n" + view.toString()
                        + "\n" + position + "\n" + id);

            }
        });
    }
}