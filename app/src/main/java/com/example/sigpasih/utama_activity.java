package com.example.sigpasih;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class utama_activity extends AppCompatActivity implements View.OnClickListener {
    dblogin Mylogin;
    ViewFlipper v_fliper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama_activity);
        Mylogin = new dblogin(this);
        ImageView ivmap=findViewById(R.id.iv_maps);
        v_fliper=findViewById(R.id.v_fliper);

        int images[] ={R.drawable.slide1, R.drawable.slide2, R.drawable.slide3};

        for (int image :images){

            flipperImages(image);
        }

        ivmap.setOnClickListener(this);
    }
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_fliper.addView(imageView);
        v_fliper.setFlipInterval(4000);
        v_fliper.setAutoStart(true);

        v_fliper.setInAnimation(this, android.R.anim.slide_in_left
        );
        v_fliper.setOutAnimation(this,android.R.anim.slide_out_right

        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       if(id==R.id.action_logout) {
            logout();
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
    Mylogin.HapusData();
    finish();
    Intent intent=new Intent(utama_activity.this, MainActivity.class);
    startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        Intent pindah = new Intent(utama_activity.this, MapsActivity.class);
        startActivity(pindah);
    }
}
