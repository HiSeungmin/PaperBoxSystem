package com.example.paperbox;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paperbox.ui.main.SectionsPagerAdapter;
import com.example.paperbox.databinding.ActivityMainBinding;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    // 로그인 화면

    //구글 로그인
    private FirebaseAuth mAuth = null;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;
    private SignInButton signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login_Button = findViewById(R.id.button_login);
        Button register_Button = findViewById(R.id.button_register);
        EditText edit_id = findViewById(R.id.editText_id);
        EditText edit_pw = findViewById(R.id.editText_pw);

        login_Button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                if (edit_id.getText().toString().equals("") || edit_pw.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(),"아이디 혹은 비밀번호를 입력하세요.",Toast.LENGTH_SHORT).show();
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), SubMain.class);
//                    startActivity(intent);
//                }
                Intent intent = new Intent(getApplicationContext(), SubMain.class);
                startActivity(intent);
            }
        });

        register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent2);
            }
        });

        signInButton = findViewById(R.id.signInButton);
        mAuth = FirebaseAuth.getInstance();
        //구글 로그인을 하기 위해 구성되어야할 코드이다.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("971509316266-7vs6qhjbj1o6d2iksdg39fphsf28hrqi.apps.googleusercontent.com")//default_web_client_id가 에러일 경우 google-services.json에서 client_id를 복사해서 넣을것
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //한번 로그인을 성공했을 때, 다음에 앱을 다시 킬 경우 로그인없이 바로 넘어가게 하기위한 코드이다.
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(getApplication(), SubMain.class);
            startActivity(intent);
            finish();
        }
        //구글 로그인 버튼을 눌렀을때 startActivityForResult(지금은 deprecated이다)로 인해 onActivityResult함수를 실행시켜주는 코드이다.
        findViewById(R.id.signInButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.e("error","errorMsg:"+e);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Snackbar.make(findViewById(R.id.layout_main), "Authentication Successed.", Snackbar.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Snackbar.make(findViewById(R.id.layout_main), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) { //update ui code here
        if (user != null) {
            readData(user);
        }
        else{
            //예외처리

        }
    }

    //첫 유저인지 아닌지 판별
    private void readData(FirebaseUser user){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference DocRef = db.collection("Users").document(user.getUid());
        Intent intent_return = new Intent(this, SubMain.class);
        Intent intent_new = new Intent(this, Newinfo.class);
        DocRef.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                startActivity(intent_return);
                                finish();
                            }
                            else{
                                startActivity(intent_new);
                                finish();
                            }

                        }else{
                            //예외처리

                        }
                    }
                });
    }
}