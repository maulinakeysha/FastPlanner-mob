package com.example.fastplannerregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class finish_16 extends AppCompatActivity {
    private TextView waktu;
    private TextView waktu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_16);

        waktu = findViewById(R.id.waktu); // Sesuaikan dengan ID yang ada di layout Anda
        waktu2 = findViewById(R.id.waktu2); // Sesuaikan dengan ID yang ada di layout Anda

        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finish_16.this, schedule_start16.class);
                startActivity(intent);
            }
        });

        Button setButton = findViewById(R.id.btn_set);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(finish_16.this, schedule_page.class);
                startActivity(intent);
            }
        });

        String selectedDateTime = getIntent().getStringExtra("selectedDateTime");
        String selectedEndDateTime = getIntent().getStringExtra("selectedEndDateTime");

        waktu.setText(selectedDateTime != null ? selectedDateTime : "No data");
        waktu2.setText(selectedEndDateTime != null ? selectedEndDateTime : "No data");
    }
}
