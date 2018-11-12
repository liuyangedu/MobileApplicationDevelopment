package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class SimpleListViewActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);
        initView();
    }

    void initView(){
        lv = findViewById(R.id.simple_listview);
        SimpleAdapter sa = new SimpleAdapter(this, getData(), R.layout.simple_listview,
                new String[]{"icon", "title", "content"},
                new int[]{R.id.lv_icon, R.id.lv_title, R.id.lv_content});

        lv.setAdapter(sa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SimpleListViewActivity.this,
                        parent.toString()+"\n\n"+
                                view.toString()+"\n\n"+
                                position+"\n\n"+id+"\n\n",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    static List<Map<String, Object>> getData(){
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        int N = 10;
        for(int i=0; i<N; ++i){
            Map<String, Object> map = new HashMap<>();
            map.put("icon", android.R.drawable.btn_star);
            map.put("title", "title " + i);
            map.put("content", "this is the content of a fake news " + i);
            list.add(map);
        }
        return list;

    }
}
