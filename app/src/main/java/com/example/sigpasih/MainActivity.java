package com.example.sigpasih;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnlogin;
    private EditText edtusername, edtpassword;
    String Username ="170030104", Password="123456";
    dblogin Mylogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogin=findViewById(R.id.btn_login);

        Mylogin = new dblogin(this);
        Cursor res = Mylogin.LihatData();
        if(res.moveToNext()){
            finish();
            Intent intent = new Intent(MainActivity.this,utama_activity.class);
            startActivity(intent);
        }
        edtusername=findViewById(R.id.edt_username);
        edtpassword=findViewById(R.id.edt_pass);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    private void Login() {
        String user =edtusername.getText().toString();
        String Pass= edtpassword.getText().toString();
        if (user.equals(" ")|| Pass.equals("")){
            Toast.makeText(this, "Data Masih Kosong  !", Toast.LENGTH_SHORT).show();
        }else{
            if (!user.equals(Username)||!Pass.equals(Password)){
                Toast.makeText(this, "Username atau Password SALAH !!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Login Berhasil !", Toast.LENGTH_SHORT).show();
                Mylogin.SimpanData(user,Pass);
                finish();
                Intent intent = new Intent(MainActivity.this, utama_activity.class);
                startActivity(intent);
            }
        }
    }
}
