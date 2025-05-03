package com.example.fastplannerregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class schedule_start20 extends AppCompatActivity {

    private ProgressBar timerProgressBar;
    private CountDownTimer countDownTimer;
    private TextView timerTextView;
    private long timeLeftInMillis; // Waktu dalam milidetik
    private boolean timerRunning; // Menandakan apakah timer sedang berjalan
    private long durationInMillis = 20 * 60 * 60 * 1000; // 20 jam


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_start_20);
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(schedule_start20.this, schedule_page_20.class);
                startActivity(intent);
            }
        });

        timerTextView = findViewById(R.id.timer);
        timerProgressBar = findViewById(R.id.timer1);
        Button endButton = findViewById(R.id.btn_end);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showStopConfirmationDialog();
            }
        });

        startTimer();

        String selectedDateTime = getIntent().getStringExtra("selectedDateTime");
        String selectedEndDateTime = getIntent().getStringExtra("selectedEndDateTime");

        // Gunakan nilai waktu yang diterima sesuai kebutuhan di sini
        // Contoh: menetapkan waktu ke TextView
        TextView waktu = findViewById(R.id.waktu);
        waktu.setText(selectedDateTime);

        TextView waktu2 = findViewById(R.id.waktu2);
        waktu2.setText(selectedEndDateTime);
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(durationInMillis, 1000) { // 1000 adalah interval setiap detik
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
                updateProgressBar();
            }

            public void onFinish() {
                timerRunning = false;
                timerTextView.setText("Selesai!");
                timerProgressBar.setProgress(0);
            }
        }.start();

        timerRunning = true;
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            timerRunning = false;
        }
    }

    private void updateTimer() {
        long hours = timeLeftInMillis / (1000 * 60 * 60);
        long minutes = (timeLeftInMillis % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (timeLeftInMillis % (1000 * 60)) / 1000;

        // Format waktu dalam jam:menit:detik
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds);

        timerTextView.setText(timeLeftFormatted);
    }

    private void updateProgressBar() {
        int progressValue = (int) ((timeLeftInMillis * 100) / durationInMillis);
        timerProgressBar.setProgress(progressValue);
    }

    private void showStopConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("FastPlanner");
        builder.setMessage("Apakah Anda ingin Menyelesaikan Puasa?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopTimer();
                // Mengambil data yang telah disimpan di Intent sebelumnya
                String selectedDateTime = getIntent().getStringExtra("selectedDateTime");
                String selectedEndDateTime = getIntent().getStringExtra("selectedEndDateTime");

                // Mengirim data ke finish_20
                Intent intent = new Intent(schedule_start20.this, finish_20.class);
                intent.putExtra("selectedDateTime", selectedDateTime);
                intent.putExtra("selectedEndDateTime", selectedEndDateTime);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Tidak", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

}
