package com.example.fastplannerregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //ke page personal data
        TextView perdat = findViewById(R.id.personaldata);

        perdat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, personal_data.class);
                startActivity(intent);
            }
        });

        //ke page informasi akun
        TextView info = findViewById(R.id.account);

        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(settings.this, acc_informasi.class);
                startActivity(intent);
            }
        });
    }
}