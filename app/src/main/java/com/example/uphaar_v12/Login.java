package com.example.uphaar_v12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uphaar_v12.ui.home.HomeFragment;
import com.example.uphaar_v12.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText email,pass;
    Button sub;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        sub=findViewById(R.id.btn_submit);

        mAuth=FirebaseAuth.getInstance();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = email.getText().toString().trim();
                String psw = pass.getText().toString().trim();
                if (ma.isEmpty()) {
                    Toast.makeText(Login.this, "Enter username", Toast.LENGTH_LONG).show();
                    return;
                }
                if (psw.isEmpty()) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_LONG).show();
                }
                mAuth.signInWithEmailAndPassword(ma, psw)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Toast.makeText(Login.this, "User Logged in", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Login.this, "User Login Failed", Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }
    public void btn_reg(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}
