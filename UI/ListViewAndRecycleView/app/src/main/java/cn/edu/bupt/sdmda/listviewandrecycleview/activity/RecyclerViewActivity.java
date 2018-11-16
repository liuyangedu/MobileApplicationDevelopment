package cn.edu.bupt.sdmda.listviewandrecycleview.activity;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import cn.edu.bupt.sdmda.listviewandrecycleview.adapter.MyRecyclerAdapter;
import cn.edu.bupt.sdmda.listviewandrecycleview.R;

public class RecyclerViewActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    MyRecyclerAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
    }

    void initView() {
        toolbar = findViewById(R.id.rv_toolbar);
        rv = findViewById(R.id.recycleview);

        toolbar.setTitle(R.string.title_toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        setSupportActionBar(toolbar);

        ma = new MyRecyclerAdapter(this, R.layout.simple_listview,
                SimpleListViewActivity.getData());
        ma.setViewInItemClickListener(new MyRecyclerAdapter.OnViewInItemClickListener() {
            @Override
            public void onClick(View v, int p) {
                switch (v.getId()) {
                    case R.id.iv_icon:
                        Toast.makeText(RecyclerViewActivity.this, String.format(
                                getResources().getString(R.string.imageview_clicked), p)
                                , Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_in_rv:
                        Toast.makeText(RecyclerViewActivity.this, String.format(
                                getResources().getString(R.string.txt_toast_button_click), p)
                                , Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        ma.setItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(RecyclerViewActivity.this, String.format(
                        getResources().getString(R.string.txt_toast_item_click), pos),
                        Toast.LENGTH_SHORT).show();
            }
        });

        ma.setViewInItemLongClickListener(
                new MyRecyclerAdapter.OnViewInItemLongClickListener() {
            @Override
            public void onLongClick(View view, int pos) {
                switch (view.getId()) {
                    case R.id.iv_icon:
                        Toast.makeText(RecyclerViewActivity.this, String.format(
                                getResources().getString(R.string.imageview_longclicked),
                                pos), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        ma.setItemContextMenuCreateListener(
                new MyRecyclerAdapter.OnItemContextMenuCreateListener() {
            @Override
            public void onContextMenuCreate(View view, Menu menu, int pos) {
                getMenuInflater().inflate(R.menu.main, menu);
            }
        });
        ma.setItemContextMenuClickListener(
                new MyRecyclerAdapter.OnItemContextMenuClickListener() {
            @Override
            public void onContextMenuClick(MenuItem item, int pos) {
                switch (item.getItemId()) {
                    case R.id.menu_add:
                        ma.addData(pos);
                        ma.notifyItemInserted(pos);
                        break;
                    case R.id.menu_del:
                        ma.delData(pos);
                        ma.notifyItemRemoved(pos);
                        break;
                }
            }
        });

        rv.setAdapter(ma);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        rv.setItemAnimator(defaultItemAnimator);
        rv.addItemDecoration(new MyItemDecoration(
                getResources().getDrawable(R.drawable.divider)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                ma.addData();
                ma.notifyItemInserted(0);
                break;
            case R.id.menu_del:
                ma.delData(0);
                ma.notifyItemRemoved(0);
                break;
        }
        return true;
    }

    // a decoration to add divider to recyclerview
    // the code can be in onDraw or onDrawOver
    class MyItemDecoration extends RecyclerView.ItemDecoration{

        Drawable _divider;

        MyItemDecoration(Drawable drawable) {
            super();
            _divider = drawable;
        }

        // draw before item
        @Override
        public void onDraw(@NonNull Canvas c,
                           @NonNull RecyclerView parent,
                           @NonNull RecyclerView.State state) {
            super.onDraw(c, parent, state);
            // get left of item
            int left = parent.getPaddingStart();
            // get right of item
            int right = parent.getWidth() - parent.getPaddingEnd();

            // draw for every visible children
            for(int i=0; i<parent.getChildCount()-1; ++i){
                View child = parent.getChildAt(i);
                RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                // get bottom of current item as the top of divider
                int top = child.getBottom() + params.bottomMargin;
                // calc the bottom of divider
                int bottom = top + _divider.getIntrinsicHeight();
                // draw divider
                _divider.setBounds(left, top, right, bottom);
                _divider.draw(c);
            }
        }

        // draw after item
        @Override
        public void onDrawOver(@NonNull Canvas c,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
//            int left = parent.getPaddingStart();
//            int right = parent.getWidth() - parent.getPaddingEnd();
//
//            int childCnt = parent.getChildCount();
//            for(int i=0; i<childCnt; ++i){
//                View child = parent.getChildAt(i);
//                RecyclerView.LayoutParams params =
//                        (RecyclerView.LayoutParams) child.getLayoutParams();
//                int top = child.getBottom() + params.bottomMargin;
//                int bottom = top + _divider.getIntrinsicHeight();
//                _divider.setBounds(left, top, right, bottom);
//                _divider.draw(c);
//            }
        }

        // set offset
        @Override
        public void getItemOffsets(@NonNull Rect outRect,
                                   @NonNull View view, @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = _divider.getIntrinsicHeight();
        }
    }

}
