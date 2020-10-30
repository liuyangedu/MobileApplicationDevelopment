package cn.edu.bupt.sdmda.listviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import cn.edu.bupt.sdmda.listviewdemo.data.DataGenerator;
import cn.edu.bupt.sdmda.listviewdemo.data.News;

public class CustomBaseAdapterActivity extends AppCompatActivity {
    final String TAG = getClass().getSimpleName();
    Button btnAdd, btnDel;
    ListView lv;
    MyAdapter ma;
    List<News> data;

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
                News news = new News();
                news.icon_id = android.R.drawable.btn_star;
                news.title = "New Title of " + data.size();
                news.content = "New Content of " + data.size();
                data.add(news);
                ma.notifyDataSetChanged();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(data.size() - 1);
                ma.notifyDataSetChanged();
            }
        });
    }

    void setupListView() {
        data = DataGenerator.genNews(30);
        ma = new MyAdapter(this, R.layout.listview_item, data);
        lv.setAdapter(ma);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.e(TAG, parent.toString() + "\n" + view.toString()
//                        + "\n" + position + "\n" + id);
//            }
//        });
    }
}

class MyAdapter extends BaseAdapter {
    final String TAG = getClass().getSimpleName();

    Context _ctx;
    @LayoutRes
    int _layoutId;
    List<News> _data;
    LayoutInflater _inflater;

    public MyAdapter(Context context, @LayoutRes int ids, List<News> data) {
        _ctx = context;
        _inflater = LayoutInflater.from(_ctx);
        _layoutId = ids;
        _data = data;
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public Object getItem(int position) {
        return _data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = _inflater.inflate(_layoutId, parent, false);
            vh = new ViewHolder();
            vh.iv = convertView.findViewById(R.id.item_icon);
            vh.tvTitle = convertView.findViewById(R.id.item_title);
            vh.tvContent = convertView.findViewById(R.id.item_content);
            vh.btn = convertView.findViewById(R.id.item_btn);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        News news = _data.get(position);
        vh.iv.setImageResource(news.icon_id);
        vh.tvTitle.setText(news.title);
        vh.tvContent.setText(news.content);
        vh.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, String.format(
                        _ctx.getResources().getString(R.string.button_clicked)
                        , position));
            }
        });
        vh.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, String.format(
                        _ctx.getResources().getString(R.string.imageview_clicked)
                        , position)
                );
            }
        });
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, String.format(
                        _ctx.getResources().getString(R.string.item_clicked)
                        , position)
                );
            }
        });
        return convertView;

    }

    class ViewHolder {
        public ImageView iv;
        public TextView tvTitle, tvContent;
        public Button btn;

    }
}