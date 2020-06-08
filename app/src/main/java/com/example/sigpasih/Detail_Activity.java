package com.example.sigpasih;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView datanama, dataalamat, dataket,datalati,datalong;
    ImageView datagambar;
    public static String id,nama,alamat,keterangan,gambar;
    public static Double Latitude, Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        datanama=findViewById(R.id.tv_nama);
        datalati=findViewById(R.id.tv_lati);
        datalong=findViewById(R.id.tv_long);
        dataalamat=findViewById(R.id.tv_alamat);
        dataket=findViewById(R.id.tv_ket);
        datagambar=findViewById(R.id.img_pasih);
        ImageView imgback=findViewById(R.id.img_back);
        ImageView imgcall=findViewById(R.id.img_call);

            datanama.setText(nama);
            dataalamat.setText(alamat);
            dataket.setText(keterangan);
        String lati2=String.valueOf(Latitude);
        datalati.setText(lati2);
        String long2=String.valueOf(Longitude);
        datalong.setText(long2);

        Picasso.get().load(gambar).into(datagambar);
        imgback.setOnClickListener(this);
        imgcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"082144677539"));
                startActivity(call);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent back = new Intent(Detail_Activity.this, MapsActivity.class);
        startActivity(back);
    }

}
