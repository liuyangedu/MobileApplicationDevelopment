package cn.edu.bupt.sdmda.viewpagerandtablayout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MyFragment extends Fragment {

    public static String KEY_MSG = "MSG";
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv = view.findViewById(R.id.textview);
        tv.setText(getArguments().getString(KEY_MSG, "error"));
    }

    public MyFragment(){}

    public static MyFragment newInstance(String data){
        MyFragment ret = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MSG, data);
        ret.setArguments(bundle);
        return ret;
    }
}
