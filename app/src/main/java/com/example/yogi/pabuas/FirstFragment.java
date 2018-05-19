package com.example.yogi.pabuas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by Yogi on 03/05/2018.
 */

public class FirstFragment extends Fragment implements View.OnClickListener {
    protected Button btnNewGame;
    private FragmentListener fl;

    public FirstFragment(){

    }

    public static FirstFragment newInstance(String title){
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("WrongViewCast")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_ingame,container,false);
        this.btnNewGame = view.findViewById(R.id.btn_newGame);
        this.btnNewGame.setOnClickListener(this);
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
        if(view.getId()==this.btnNewGame.getId()){
            this.fl.changePage(2);
        }

    }
}
