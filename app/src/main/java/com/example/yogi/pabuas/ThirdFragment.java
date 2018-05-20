package com.example.yogi.pabuas;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Yogi on 19/05/2018.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener {

    private TextView tvTime;
    private Button btnPlayAgain,btnExit;
    private FragmentListener fl;


    public ThirdFragment(){

    }

    public static ThirdFragment newInstance(String title){
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("WrongViewCast")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_win,container,false);
        this.btnPlayAgain = view.findViewById(R.id.btn_restart);
        this.btnExit = view.findViewById(R.id.btn_menu);
        this.tvTime = view.findViewById(R.id.tv_time);
        this.btnPlayAgain.setOnClickListener(this);

        this.btnExit.setOnClickListener(this);

        Bundle args = getArguments();
        this.tvTime.setText(args.getString(args.getString("value")));

        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fl = (FragmentListener) context;
        }
        else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnPlayAgain.getId()){
            fl.changePage(2);
        }
        else if(view.getId()==btnExit.getId()){
            fl.changePage(1);
        }
    }


}
