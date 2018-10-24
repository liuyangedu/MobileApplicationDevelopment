package cn.edu.bupt.sdmda.fragmentdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MYFragment extends Fragment {

    TextView tv;

    public MYFragment(){
    }

    public static MYFragment newInstance() {

        Bundle args = new Bundle();
        MYFragment fragment = new MYFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        tv = view.findViewById(R.id.textview);
        tv.setText(getResources().getString(R.string.btn_my));
        return view;
    }

}
