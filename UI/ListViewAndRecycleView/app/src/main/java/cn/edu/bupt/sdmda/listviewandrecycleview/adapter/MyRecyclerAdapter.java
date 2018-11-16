package cn.edu.bupt.sdmda.listviewandrecycleview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private LayoutInflater _inflater;
    private List<Map<String, Object>> _data;
    private @LayoutRes
    int _layout;
    private Context _ctx;

    // interfaces to listener
    public interface OnItemClickListener {
        void onClick(View view, int pos);
    }

    public interface OnViewInItemClickListener {
        void onClick(View view, int pos);
    }

    public interface OnItemContextMenuCreateListener {
        void onContextMenuCreate(View view, Menu menu, int pos);
    }

    public interface OnViewInItemLongClickListener {
        void onLongClick(View view, int pos);
    }

    public interface OnItemContextMenuClickListener {
        void onContextMenuClick(MenuItem item, int pos);
    }
    // functions to set listeners
    public void setItemClickListener(OnItemClickListener l) {
        _itemClickListener = l;
    }

    public void setViewInItemClickListener(OnViewInItemClickListener l) {
        _viewInItemClickListener = l;
    }

    public void setItemContextMenuCreateListener(OnItemContextMenuCreateListener l) {
        _itemContextMenuCreateListener = l;
    }

    public void setViewInItemLongClickListener(OnViewInItemLongClickListener l) {
        _viewInItemLongClickListener = l;
    }

    public void setItemContextMenuClickListener(OnItemContextMenuClickListener l){
        _itemContextMenuClickListener = l;
    }
    // variables about listeners
    private OnItemClickListener _itemClickListener;
    private OnViewInItemClickListener _viewInItemClickListener;
    private OnItemContextMenuCreateListener _itemContextMenuCreateListener;
    private OnViewInItemLongClickListener _viewInItemLongClickListener;
    private OnItemContextMenuClickListener _itemContextMenuClickListener;



    public MyRecyclerAdapter(Context context, @LayoutRes int layoutId,
                             List<Map<String, Object>> data) {
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
        // All listeners are defined here
        View.OnClickListener viewClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_viewInItemClickListener == null) return;
                _viewInItemClickListener.onClick(v, viewHolder.getAdapterPosition());
            }
        };

        View.OnLongClickListener viewLongClickListener = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (_viewInItemLongClickListener == null) return false;
                _viewInItemLongClickListener.onLongClick(v, viewHolder.getAdapterPosition());
                return true;

            }
        };

        View.OnClickListener itemClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_itemClickListener == null) return;
                _itemClickListener.onClick(v, viewHolder.getAdapterPosition());
            }
        };

        View.OnCreateContextMenuListener createContextMenuListener = new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if(_itemContextMenuCreateListener==null)    return;
                _itemContextMenuCreateListener.onContextMenuCreate(v, menu, viewHolder.getAdapterPosition());

                if(_itemContextMenuClickListener==null) return;
                for(int i=0; i<menu.size();++i){
                    menu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            _itemContextMenuClickListener.onContextMenuClick(item, viewHolder.getAdapterPosition());
                            return true;
                        }
                    });
                }
            }
        };

        // views in item
        viewHolder.iv.setImageResource((int) _data.get(position).get("icon"));
        viewHolder.title.setText(_data.get(position).get("title").toString());
        viewHolder.content.setText(_data.get(position).get("content").toString());

        viewHolder.btn.setOnClickListener(viewClickListener);
        viewHolder.iv.setOnClickListener(viewClickListener);

        viewHolder.iv.setOnLongClickListener(viewLongClickListener);

        // view itself
        viewHolder.itemView.setOnClickListener(itemClickListener);
        viewHolder.itemView.setOnCreateContextMenuListener(createContextMenuListener);
    }

    public void addData() {
        addData(0);
    }

    public void addData(int position) {
        Map<String, Object> map = new HashMap<>();
        map.put("icon", android.R.drawable.star_off);
        map.put("title", "title add " + _data.size());
        map.put("content", "something new here! " + _data.size());
        _data.add(position, map);
    }

    public void delData(int position) {
        if (position < _data.size()) {
            _data.remove(position);
        }
    }


    @Override
    public int getItemCount() {
        return _data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView title;
        TextView content;
        Button btn;

        MyViewHolder(View view) {
            super(view);
            iv = view.findViewById(R.id.iv_icon);
            title = view.findViewById(R.id.lv_title);
            content = view.findViewById(R.id.lv_content);
            btn = view.findViewById(R.id.btn_in_rv);
        }
    }
}
