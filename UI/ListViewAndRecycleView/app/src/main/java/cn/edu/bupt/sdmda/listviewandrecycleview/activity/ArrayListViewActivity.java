package cn.edu.bupt.sdmda.listviewandrecycleview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class ArrayListViewActivity extends AppCompatActivity {

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list_view);
        initView();
    }

    void initView(){
        lv = findViewById(R.id.array_listview);
        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getData());
        lv.setAdapter(aa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ArrayListViewActivity.this,
                        parent.toString()+"\n\n"+
                                view.toString()+"\n\n"+
                                position+"\n\n"+id+"\n\n",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    static String[] getData(){
        ArrayList<String> list = new ArrayList<>();
        int N = 100;
        for(int i=0; i<N; ++i){
            list.add("item " + i);
        }
        return list.toArray(new String[0]);
    }
}
