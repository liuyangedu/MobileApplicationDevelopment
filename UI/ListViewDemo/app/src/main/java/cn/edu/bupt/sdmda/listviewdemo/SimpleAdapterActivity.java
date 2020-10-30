package cn.edu.bupt.sdmda.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewdemo.data.DataGenerator;

public class SimpleAdapterActivity extends AppCompatActivity {

    final String TAG = getClass().getSimpleName();
    Button btnAdd, btnDel;
    ListView lv;
    SimpleAdapter sa;
    List<Map<String, Object>> data;

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
                Map<String, Object> item = new HashMap<>();
                item.put("icon", android.R.drawable.btn_star);
                item.put("title", "New Title of " + data.size());
                item.put("content", "New Content of " + data.size());
                data.add(item);
                sa.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(data.size() - 1);
                sa.notifyDataSetChanged();
            }
        });
    }

    void setupListView() {
        data = DataGenerator.genMapList(20);
        sa = new SimpleAdapter(this, data,
                R.layout.listview_item, new String[] {"icon", "title","content"},
                new int[]{R.id.item_icon, R.id.item_title, R.id.item_content});
        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, parent.toString() + "\n" + view.toString()
                        + "\n" + position + "\n" + id);
            }
        });
    }
}