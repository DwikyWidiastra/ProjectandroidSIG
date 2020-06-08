package com.example.sigpasih;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {
    private final int panjang =5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tampil = new Intent(splashscreen.this, MainActivity.class);
                splashscreen.this.startActivity(tampil);
                splashscreen.this.finish();
            }
        },panjang);

    }
}
