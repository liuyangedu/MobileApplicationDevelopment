package cn.edu.bupt.sdmda.viewpagerandtablayout;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    MyPagerAdapter ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    void initView() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        setSupportActionBar(toolbar);

        ma = new MyPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(ma);

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorPrimary)
        );
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                ma.addData();
                ma.notifyDataSetChanged();
                break;
            case R.id.menu_del:
                int pos = viewPager.getCurrentItem();
                if (ma.getCount() <= 1) break;
                ma.delData(pos);
                ma.notifyDataSetChanged();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static List<Fragment> getFragments() {
        List<Fragment> ret = new ArrayList<>();
        int n = 3;
        for (int i = 0; i < n; ++i) {
            ret.add(MyFragment.newInstance("Frag " + i));
        }
        return ret;
    }
}
