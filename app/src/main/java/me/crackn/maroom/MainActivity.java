package me.crackn.maroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email,pass;
    private TextView bDaftar;
    private Button bLogin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        bDaftar = findViewById(R.id.daftarB);
        bLogin = findViewById(R.id.loginB);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email.getText().toString(),pass.getText().toString());
            }
        });
        bDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DaftarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signIn(String ema, String pa) {
        if(!validateForm()){
            return;
        }
        mAuth.signInWithEmailAndPassword(ema,pa)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateForm() {
        boolean valid =true;


        String ss = email.getText().toString();
        if(TextUtils.isEmpty(ss)){
            email.setError("Required");
            valid=false;
        }else {
            email.setError(null);
        }

        String aa = pass.getText().toString();
        if(TextUtils.isEmpty(aa)){
            pass.setError("Required");
            valid=false;
        }else {
            pass.setError(null);
        }
        return valid;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser!=null){
            Intent intent = new Intent(MainActivity.this,DrawerActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
