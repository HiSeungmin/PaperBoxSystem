package com.example.paperbox;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paperbox.ui.main.SectionsPagerAdapter;
import com.example.paperbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login_Button = findViewById(R.id.button_login);
        Button register_Button = findViewById(R.id.button_register);
        EditText edit_id = findViewById(R.id.editText_id);
        EditText edit_pw = findViewById(R.id.editText_pw);

//        String id = edit_id.getText().toString();
//        String pw = edit_pw.getText().toString();

        login_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (edit_id.getText().toString().equals("") || edit_pw.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"아이디 혹은 비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SubMain.class);
                    startActivity(intent);
                }
            }
        });
        //oseungmin babo
        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent2);
            }
        });
    }
}