package cn.edu.bupt.sdmda.listviewandrecycleview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class MyAdapter extends BaseAdapter {

    private LayoutInflater _inflater;
    private List<Map<String, Object>> _data;
    private @LayoutRes int _layout;
    private Context _ctx;

    public MyAdapter(Context context, @LayoutRes int layoutId, List<Map<String, Object>> data){
        _ctx = context;
        _inflater = LayoutInflater.from(_ctx);
        _layout = layoutId;
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

    // TODO: getView WITHOUT ViewHolder
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        if(convertView==null){
//            convertView = _inflater.inflate(_layout, parent, false);
//
//        }
//
//        ImageView iv = convertView.findViewById(R.id.lv_icon);
//        iv.setImageResource((int)_data.get(position).get("icon"));
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(_ctx, String.format(_ctx.getResources().
//                                getString(R.string.imageview_clicked), position),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        TextView title = convertView.findViewById(R.id.lv_title);
//        title.setText(_data.get(position).get("title").toString());
//
//        TextView content = convertView.findViewById(R.id.lv_content);
//        content.setText(_data.get(position).get("content").toString());
//
//        return convertView;
//    }

    // TODO: getView WITH ViewHolder
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if(convertView==null){
            convertView = _inflater.inflate(_layout, parent, false);
            vh = new ViewHolder();
            vh.iv =  convertView.findViewById(R.id.lv_icon);
            vh.title = convertView.findViewById(R.id.lv_title);
            vh.content = convertView.findViewById(R.id.lv_content);
            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();

        vh.iv.setImageResource((int)_data.get(position).get("icon"));
        vh.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, String.format(_ctx.getResources().getString(R.string.imageview_clicked), position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        vh.title.setText(_data.get(position).get("title").toString());
        vh.content.setText(_data.get(position).get("content").toString());

        return convertView;
    }

    public void addData(int position){
        Map<String, Object> map = new HashMap<>();
        map.put("icon", android.R.drawable.star_off);
        map.put("title", "title add " + _data.size());
        map.put("content", "something new here! "+_data.size());
        _data.add(position, map);
    }

    public void addData(){
        addData(0);
    }

    public void delData(int position){
        if(position<_data.size()){
            _data.remove(position);
        }
    }

    class ViewHolder {
        ImageView iv;
        TextView title;
        TextView content;
    }
}
