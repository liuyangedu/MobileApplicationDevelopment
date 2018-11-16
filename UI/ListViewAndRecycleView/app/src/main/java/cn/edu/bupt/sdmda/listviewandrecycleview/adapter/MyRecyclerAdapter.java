package cn.edu.bupt.sdmda.listviewandrecycleview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private LayoutInflater _inflater;
    private List<Map<String, Object>> _data;
    private @LayoutRes int _layout;
    private Context _ctx;

    public MyRecyclerAdapter(Context context, @LayoutRes int layoutId,
                             List<Map<String, Object>> data){
        _ctx = context;
        _inflater = LayoutInflater.from(_ctx);
        _layout = layoutId;
        _data = data;
    }


    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(_inflater.inflate(_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRecyclerAdapter.MyViewHolder viewHolder, int position) {
        final int i = position;

        // controls in item and their listener
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.iv_icon:
                        break;
                    case R.id.btn_in_rv:
                        Toast.makeText(_ctx, String.format(
                                _ctx.getResources().getString(R.string.txt_toast_button_click),
                                viewHolder.getAdapterPosition())
                                , Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };
        viewHolder.iv.setImageResource((int)_data.get(position).get("icon"));
        // ADD some item and click on imageview to check its output
        // the position is ERROR!
        viewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, String.format(_ctx.getResources().
                                getString(R.string.imageview_clicked), i),
                        Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.title.setText(_data.get(position).get("title").toString());
        viewHolder.content.setText(_data.get(position).get("content").toString());
        viewHolder.btn.setOnClickListener(clickListener);

        // click listener of the item itself
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_ctx, String.format(
                        _ctx.getResources().getString(R.string.txt_toast_item_click),
                        viewHolder.getAdapterPosition())
                        , Toast.LENGTH_SHORT).show();
            }
        });

        // context menu of item
        final MenuItem.OnMenuItemClickListener menuItemClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.menu_add:
                    addData(viewHolder.getAdapterPosition());
                    notifyItemInserted(viewHolder.getAdapterPosition());
                    break;
                case R.id.menu_del:
                    delData(viewHolder.getAdapterPosition());
                    notifyItemRemoved(viewHolder.getAdapterPosition());
                    break;
            }
            return true;
            }
        };

        viewHolder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                ((AppCompatActivity)_ctx).getMenuInflater().inflate(R.menu.main, menu);
                MenuItem item;
                item = menu.findItem(R.id.menu_add);
                item.setOnMenuItemClickListener(menuItemClickListener);
                item = menu.findItem(R.id.menu_del);
                item.setOnMenuItemClickListener(menuItemClickListener);

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

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        TextView content;
        Button btn;

        MyViewHolder(View view){
            super(view);
            iv = view.findViewById(R.id.iv_icon);
            title = view.findViewById(R.id.lv_title);
            content = view.findViewById(R.id.lv_content);
            btn = view.findViewById(R.id.btn_in_rv);
        }
    }
}
