package com.example.paperbox;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    // 회원가입
    private EditText name_text;
    private EditText id_text;
    private EditText password_text;
    private EditText pass_confirm_text;
    private Map<String, Object> data = new HashMap<>();
    private Spinner spinner;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Button okay_btn = findViewById(R.id.button_okay);
        name_text = findViewById(R.id.RegisterNameText);
        id_text = findViewById(R.id.IdText);
        password_text = findViewById(R.id.PasswordText);
        pass_confirm_text = findViewById(R.id.PassConfirmText);

        //거주지 spinner
        arrayList = new ArrayList<>();
        arrayList.add("서울");
        arrayList.add("인천");
        arrayList.add("경기");
        arrayList.add("충북");
        arrayList.add("충남");
        arrayList.add("대전");
        arrayList.add("경북");
        arrayList.add("경남");
        arrayList.add("광주");
        arrayList.add("대구");
        arrayList.add("부산");
        arrayList.add("울산");
        arrayList.add("제주");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arrayList);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),arrayList.get(i)+"가 선택되었습니다.",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        okay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.put("name",name_text.getText().toString());
                data.put("ID",id_text.getText().toString());
                data.put("password",password_text.getText().toString());
                data.put("point",0);
                Upload_Info(data);
            }
        });

    }

    private void Upload_Info(Map data){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        db.collection("RegisterUsers").add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        startActivity(intent);
                    }
                });
    }
}
