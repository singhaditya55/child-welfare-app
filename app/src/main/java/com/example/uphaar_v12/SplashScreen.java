package com.example.uphaar_v12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


                        Intent intent = new Intent(SplashScreen.this,Login.class);
                        startActivity(intent);
                        finish();
            }
        }