package cn.edu.bupt.sdmda.fragmentdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MomenetFragment extends Fragment {

    OnETChangedListener mListener;
    Button btn;
    EditText et;

    public MomenetFragment(){
    }

    public static MomenetFragment newInstance() {

        Bundle args = new Bundle();
        MomenetFragment fragment = new MomenetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_click, container, false);
        initView(view);
        return view;
    }

    void initView(View view){
        btn = view.findViewById(R.id.btn);
        et = view.findViewById(R.id.edittext);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onETChanged(et.getText().toString());
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        mListener = (OnETChangedListener)context;
        super.onAttach(context);
    }

    public interface OnETChangedListener{
        void onETChanged(String txt);
    }

}
