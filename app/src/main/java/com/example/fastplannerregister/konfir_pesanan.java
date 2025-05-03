package com.example.fastplannerregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class konfir_pesanan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Mengganti ID dengan sesuai dengan ID yang ada di layout activity_main
        Button button = findViewById(R.id.btn_confirm);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(konfir_pesanan.this, order_stat.class);
                startActivity(intent);
            }
        });
    }
}