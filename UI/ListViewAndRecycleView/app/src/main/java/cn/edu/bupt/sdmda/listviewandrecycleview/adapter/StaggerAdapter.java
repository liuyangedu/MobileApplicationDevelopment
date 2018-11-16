package cn.edu.bupt.sdmda.listviewandrecycleview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class StaggerAdapter extends RecyclerView.Adapter<StaggerAdapter.ViewHolder> {

    private Context _ctx;
    private @LayoutRes int _layout;
    private LayoutInflater _inflater;
    private int _n;

    public StaggerAdapter(Context context, @LayoutRes int layoutId, int n){
        _ctx = context;
        _layout = layoutId;
        _inflater = LayoutInflater.from(_ctx);
        _n = n;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(_inflater.inflate(_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(i % 2 ==0) {
            viewHolder.iv.setImageResource(R.drawable.img1);
        } else {
            viewHolder.iv.setImageResource(R.drawable.img2);
        }
    }

    @Override
    public int getItemCount() {
        return _n;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
