package com.example.fastplannerregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class order_stat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_stat);

        // Mengganti ID dengan sesuai dengan ID yang ada di layout activity_main
        Button button = findViewById(R.id.btn_back);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(order_stat.this, konfir_pesanan.class);
                startActivity(intent);
            }
        });
    }
}