package com.example.uphaar_v12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText name, email, password, cpassword;
    Button reg;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        cpassword = findViewById(R.id.cpass);
        reg = findViewById(R.id.register);
        mAuth = FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v) {
                String na = name.getText().toString().trim();
                String ma = email.getText().toString().trim();
                String psw = password.getText().toString().trim();
                String cpass = cpassword.getText().toString().trim();

                if (TextUtils.isEmpty(na)) {
                    Toast.makeText(Register.this, "Please enter name", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(ma)) {
                    Toast.makeText(Register.this, "Please enter email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(Register.this, "Please enter password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (psw.length() < 8) {
                    Toast.makeText(Register.this, "Please choose a bigger password", Toast.LENGTH_LONG).show();
                }
                if (TextUtils.isEmpty(cpass)) {
                    Toast.makeText(Register.this, "Please confirm your password", Toast.LENGTH_LONG).show();
                }
                if (psw.length() != cpass.length()) {
                    Toast.makeText(Register.this, "Password does not match confirm password", Toast.LENGTH_LONG).show();
                }
                mAuth.createUserWithEmailAndPassword(ma, psw)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @SuppressLint("ShowToast")
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                    Toast.makeText(Register.this, "Registered", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(Register.this, "Already Registered", Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }
}
