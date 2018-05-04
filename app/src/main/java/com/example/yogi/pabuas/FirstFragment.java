package com.example.yogi.pabuas;

import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
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
    protected Button btnExitGame;
    private FragmentListener fl;

    public FirstFragment(){

    }

    public static FirstFragment newInstance(String title){
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString("Game",title);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_ingame,container,false);
        this.btnNewGame = view.findViewById(R.id.btn_newGame);
        this.btnExitGame = view.findViewById(R.id.btn_exit);
        return view;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==this.btnNewGame.getId()){
            this.fl.changePage(2);
        }
        else if(view.getId()==this.btnExitGame.getId()){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}