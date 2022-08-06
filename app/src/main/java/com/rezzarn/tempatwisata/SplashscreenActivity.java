package com.rezzarn.tempatwisata;

// Nim : 10119267
// Nama : Neti Febriyani
// Kelas : IF7
// tgl pengerjaan : (5 agustus 2022)

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override            public void run() {
                startActivity(new Intent(getApplicationContext(),
                        MainActivity.class));
                finish();
            }
        }, 3000L);
        /*Mengatur durasi Splash Screen. 2000L = 2 detik*/

        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}