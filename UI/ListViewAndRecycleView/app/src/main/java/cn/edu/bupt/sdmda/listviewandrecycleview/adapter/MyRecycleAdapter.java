package cn.edu.bupt.sdmda.listviewandrecycleview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class MyRecycleAdapter extends RecyclerView.Adapter {

    private LayoutInflater _inflater;
    private List<Map<String, Object>> _data;
    private @LayoutRes int _layout;
    private Context _ctx;

    public MyRecycleAdapter(Context context, @LayoutRes int layoutId,
                            List<Map<String, Object>> data){
        _ctx = context;
        _inflater = LayoutInflater.from(_ctx);
        _layout = layoutId;
        _data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(_inflater.inflate(_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder vh = (MyViewHolder) viewHolder;
        final int i = position;
        vh.iv.setImageResource((int)_data.get(position).get("icon"));
        vh.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, String.format(_ctx.getResources().
                                getString(R.string.imageview_clicked), i),
                        Toast.LENGTH_SHORT).show();
            }
        });

        vh.title.setText(_data.get(position).get("title").toString());
        vh.content.setText(_data.get(position).get("content").toString());

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, v.toString()+"\n\n"+
                                i+"\n\n", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addData(){
       addData(0);
    }

    public void addData(int position){
        Map<String, Object> map = new HashMap<>();
        map.put("icon", android.R.drawable.star_off);
        map.put("title", "title add " + _data.size());
        map.put("content", "something new here! "+_data.size());
        _data.add(position, map);
    }

    public void delData(int position){
        if(position<_data.size()){
            _data.remove(position);
        }
    }


    @Override
    public int getItemCount() {
        return _data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        TextView content;

        MyViewHolder(View view){
            super(view);
            iv = view.findViewById(R.id.lv_icon);
            title = view.findViewById(R.id.lv_title);
            content = view.findViewById(R.id.lv_content);
        }
    }


}
