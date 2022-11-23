package com.example.paperbox.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.paperbox.R;


public class refund extends Fragment {

    ImageButton imageButton1;
    ImageButton imageButton2;
    ImageButton imageButton3;
    ImageButton imageButton4;
    ImageButton imageButton5;
    ImageButton imageButton6;
    ImageButton imageButton7;
    ImageButton imageButton8;
    ImageButton imageButton9;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_refund, container, false);



        return v;
    }

    public void show() {
        AlertDialog.Builder dig = new AlertDialog.Builder(getContext());

        dig.setTitle("2300₩ 구매하기");
        dig.setMessage("해당 강아지로 체험하시겠습니까?\n한 번 구매한 후에는 자동 소장됩니다.");
        dig.setIcon(R.drawable.ic_launcher_foreground).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"현재는 구매하실 수 없습니다.",Toast.LENGTH_SHORT).show();

                //displayToast("ok");
            }
        });//onClickListner : ok누르면 뭘할지,
        dig.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"취소 되었습니다.",Toast.LENGTH_SHORT).show();

                //displayToast("no");
            }
        });//no버튼 누르면 뭐할지 _토스트를 하게 했다.//두가지 버전으로 가능.~.~; .~;
        dig.show();
    }
}