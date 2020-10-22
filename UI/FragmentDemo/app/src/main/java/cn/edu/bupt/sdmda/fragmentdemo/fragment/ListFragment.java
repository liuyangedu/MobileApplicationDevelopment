package cn.edu.bupt.sdmda.fragmentdemo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.edu.bupt.sdmda.fragmentdemo.activity.IChangeFragment;
import cn.edu.bupt.sdmda.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment
        implements View.OnClickListener {

    Button btn_et, btn_tv;
    final public String TAG = getClass().getSimpleName();

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        btn_et = view.findViewById(R.id.btn_et_frag);
        btn_tv = view.findViewById(R.id.btn_tv_frag);
        btn_et.setOnClickListener(this);
        btn_tv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        IChangeFragment act = (IChangeFragment) getActivity();
        if (act ==null) {
            Log.e(TAG, "error in ListFragment onClick");
            return;
        }
        switch (v.getId()) {
            case R.id.btn_et_frag:
                act.changeETFragment("Start From ListFragment!");
                break;
            case R.id.btn_tv_frag:
                act.changeTVFragment("Start From ListFragment!");
                break;
        }
    }
}