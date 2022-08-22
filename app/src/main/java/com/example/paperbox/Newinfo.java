package com.example.paperbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Newinfo extends AppCompatActivity {

    private FirebaseAuth mAuth = null;
    private EditText phone_text;
    private EditText address_text;
    private EditText name_text;
    private Map<String, Object> data = new HashMap<>();

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newinfo);

        name_text = findViewById(R.id.NameText);
        phone_text = findViewById(R.id.PhoneText);
        address_text = findViewById(R.id.AddressText);
        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckText()) {
                    data.put("name", name_text.getText().toString());
                    data.put("phone", phone_text.getText().toString());
                    data.put("address", address_text.getText().toString());
                    data.put("point", 0);
                    WriteDB(data);

                } else {
                    //알림창 띄우기
                }
            }
        });
    }

    private boolean CheckText(){
        if(name_text.getText().length() == 0 ||
                phone_text.getText().length() == 0 ||
                address_text.getText().length() == 0){
            return false;
        }else{
            return true;
        }
    }

    private void WriteDB(Map data){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Intent login = new Intent(getApplicationContext(), MainActivity.class);
        db.collection("Users")
                .document(mAuth.getCurrentUser().getUid())
                .set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        login.putExtra("login_mode","google");
                        login.putExtra("name",data.get("name").toString());
                        login.putExtra("phone",data.get("phone").toString());
                        login.putExtra("address",data.get("address").toString());
                        login.putExtra("point",data.get("name").toString());
                        startActivity(login);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("error","msg:"+e);
                    }
                });
    }
}