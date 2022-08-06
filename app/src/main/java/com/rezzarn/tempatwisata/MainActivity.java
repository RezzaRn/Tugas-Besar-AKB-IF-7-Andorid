package com.rezzarn.tempatwisata;

// Nim                  : 10119291
// Nama                 : Rezza Ramdhani Nashrullah
// Kelas                : IF7
// Tanggal Pengerjaan   : (4-6 agustus 2022)

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rezzarn.tempatwisata.adapter.WisataAdapter;
import com.rezzarn.tempatwisata.model.DataWisata;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference reference;


    ArrayList<DataWisata> list;
    WisataAdapter adapter;

    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private DrawerLayout draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        draw = findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle t = new ActionBarDrawerToggle(this, draw,
                R.string.open,
                R.string.close);

        draw.addDrawerListener(t);
        t.syncState();

        NavigationView navigationview = findViewById(R.id.nav_view);
        navigationview.setItemIconTintList(null);

        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.item1:
                        Intent profile = new Intent(MainActivity.this, ProfilActivity.class);
                        startActivity(profile);
                        break;
                    case R.id.tentang:
                        Intent tentang = new Intent(MainActivity.this, TentangActivity.class);
                        startActivity(tentang);
                        break;
                }
                return true;
            }
        });

        mRecycler = findViewById(R.id.list_wisata);
        mRecycler.setHasFixedSize(true);

        //Contex contex;
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        reference = FirebaseDatabase.getInstance().getReference().child("wisata");

        list = new ArrayList<>();
        adapter = new WisataAdapter(this, list);
        mRecycler.setAdapter(adapter);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    DataWisata wst = dataSnapshot.getValue(DataWisata.class);
                    list.add(wst);
                }
                adapter.notifyDataSetChanged();
                adapter = new WisataAdapter(getApplicationContext(), list);
                mRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "terjadi kesalahan", Toast.LENGTH_LONG).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            openDrawer();
            if (item.getItemId() == android.R.id.home) {
                if ((draw) != null && (draw.isDrawerOpen(GravityCompat.START)))
                    closeDrawer();
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void closeDrawer() {
        draw.setDrawerListener(null);
        draw.closeDrawers();
    }
    @SuppressWarnings("deprecation")
    private void openDrawer() {
        draw.setDrawerListener(null);
        draw.openDrawer(GravityCompat.START);
    }

}