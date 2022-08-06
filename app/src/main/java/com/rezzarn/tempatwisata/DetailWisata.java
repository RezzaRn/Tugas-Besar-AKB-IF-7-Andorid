package com.rezzarn.tempatwisata;

// Nim                  : 10119291
// Nama                 : Rezza Ramdhani Nashrullah
// Kelas                : IF7
// Tanggal Pengerjaan   : (4-6 agustus 2022)

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailWisata extends AppCompatActivity {
    TextView vNamaWisata, vLokasiWisata, vDeskWisata;
    ImageView IvImgWisata;
    Button btnBukaMaps;

    String nama, lokasi, deskripsi, id, mapsUrl, imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        vNamaWisata = findViewById(R.id.tvNamaWisata);
        vLokasiWisata = findViewById(R.id.tvLokasiWisata);
        vDeskWisata = findViewById(R.id.tvDeskWisata);
        IvImgWisata = findViewById(R.id.IvWisata);
        btnBukaMaps = findViewById(R.id.btnBukaMaps);

        nama = getIntent().getStringExtra("NAMA_WISATA");
        lokasi = getIntent().getStringExtra("LOKASI_WISATA");
        deskripsi = getIntent().getStringExtra("DESKRIPSI_WISATA");
        mapsUrl = getIntent().getStringExtra("MAPS_URL");
        imgUrl = getIntent().getStringExtra("IMG_URL");

        vNamaWisata.setText(nama);
        vLokasiWisata.setText(lokasi);
        vDeskWisata.setText(deskripsi);

        Glide.with(this).load(imgUrl).into(IvImgWisata);

        btnBukaMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl(mapsUrl);
            }
        });
    }

    private void gotoUrl( String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

}