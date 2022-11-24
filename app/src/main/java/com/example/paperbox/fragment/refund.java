package com.example.paperbox.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    Button solution;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_refund, container, false);

        imageButton1 = v.findViewById(R.id.imageButton1);
        imageButton2 = v.findViewById(R.id.imageButton2);
        imageButton3 = v.findViewById(R.id.imageButton3);
        imageButton4 = v.findViewById(R.id.imageButton4);
        imageButton5 = v.findViewById(R.id.imageButton5);
        imageButton6 = v.findViewById(R.id.imageButton6);
        imageButton7 = v.findViewById(R.id.imageButton7);
        imageButton8 = v.findViewById(R.id.imageButton8);
        imageButton9 = v.findViewById(R.id.imageButton9);
        solution = v.findViewById(R.id.button2);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        solution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });


        return v;
    }

    public void show() {
        AlertDialog.Builder dig = new AlertDialog.Builder(getContext());

        dig.setTitle("2300P 구매하기");
        dig.setMessage("해당 기프티콘로 교환하시겠습니까?\n 교환 후 다시 포인트로 되돌릴 수 없습니다.");
        dig.setIcon(R.drawable.ic_launcher_foreground).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"교환되었습니다.",Toast.LENGTH_SHORT).show();

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

    public void showdialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setIcon(R.mipmap.ic_launcher);//알림창 아이콘 설정
        dialog.setTitle("기프티콘 사용 방법");
        dialog.setMessage("화면에 사용자의 포인터에 맞는 기프티콘이 보여집니다. 원하는 기프티콘을 클릭하면 구매하실 수 있습니다. 한 번 구매한 기프티콘은 다시 포인터로 변환할 수 없습니다."); //알림창 메세지 설정

        //알림창의 닫기를 눌렀을때 발생 이벤트 설정
        dialog.setNeutralButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(),"창을 닫습니다",Toast.LENGTH_SHORT).show();
            } //닫기를 눌렀을때, 토스트 알림메세지 뜸.
        });
        dialog.show();
    }
}