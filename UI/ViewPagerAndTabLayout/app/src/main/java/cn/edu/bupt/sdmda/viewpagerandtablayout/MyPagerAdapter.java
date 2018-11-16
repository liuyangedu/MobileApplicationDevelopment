package cn.edu.bupt.sdmda.viewpagerandtablayout;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> _data;

    MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        _data = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return _data.get(i);
    }

    @Override
    public int getCount() {
        return _data.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        int pos = _data.indexOf(object);
        return pos == -1 ? PagerAdapter.POSITION_NONE : pos;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        MyFragment frag = (MyFragment) _data.get(position);
        return frag.getArguments().getString(MyFragment.KEY_MSG);
    }

    void addData() {
        _data.add(MyFragment.newInstance("New " + _data.size()));
    }

    void delData(int pos) {
        _data.remove(pos);
    }




}
