package cn.edu.bupt.sdmda.fragmentdemo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.edu.bupt.sdmda.fragmentdemo.activity.IChangeFragment;
import cn.edu.bupt.sdmda.fragmentdemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TVFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_DATA = "data";

    final public String TAG = getClass().getSimpleName();

    private String data;

    TextView tv;

    public TVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param data Parameter 1.
     * @return A new instance of fragment TVFragment.
     */
    public static TVFragment newInstance(String data) {
        TVFragment fragment = new TVFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString(ARG_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        tv = view.findViewById(R.id.textview);
        tv.setText(data);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IChangeFragment act = (IChangeFragment) getActivity();
                if (act == null) {
                    Log.e(TAG, "error in TVFragment onClick");
                    return;
                }
                act.changeETFragment(tv.getText().toString());
            }
        });
        return view;
    }

    public void setText(String data) {
        tv.setText(data);
    }
}