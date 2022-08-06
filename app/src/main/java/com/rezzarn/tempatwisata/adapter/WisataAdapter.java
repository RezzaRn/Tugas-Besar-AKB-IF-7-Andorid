package com.rezzarn.tempatwisata.adapter;

// Nim                  : 10119291
// Nama                 : Rezza Ramdhani Nashrullah
// Kelas                : IF7
// Tanggal Pengerjaan   : (4-6 agustus 2022)

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rezzarn.tempatwisata.DetailWisata;
import com.rezzarn.tempatwisata.R;
import com.rezzarn.tempatwisata.model.DataWisata;

import java.util.ArrayList;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<DataWisata> dataWisatas;

    //database storage
    private StorageReference reference;

    public WisataAdapter(Context cont, ArrayList<DataWisata> data) {
        context = cont;
        dataWisatas = data;
        reference = FirebaseStorage.getInstance().getReference();
    }

        @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wisata, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.vnama.setText(dataWisatas.get(position).getNama());
        holder.vlokasi.setText(dataWisatas.get(position).getLokasi());

        getImage("images/"+dataWisatas.get(position).getId()+".jpg", holder.vImgWisata);

        holder.vDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context.getApplicationContext(), DetailWisata.class);
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                detail.putExtra("NAMA_WISATA", dataWisatas.get(holder.getAdapterPosition()).getNama());
                detail.putExtra("LOKASI_WISATA", dataWisatas.get(holder.getAdapterPosition()).getLokasi());
                detail.putExtra("DESKRIPSI_WISATA", dataWisatas.get(holder.getAdapterPosition()).getDeskripsi());
                detail.putExtra("ID_WISATA", dataWisatas.get(holder.getAdapterPosition()).getId());
                detail.putExtra("MAPS_URL", dataWisatas.get(holder.getAdapterPosition()).getMapsUrl());
                detail.putExtra("IMG_URL", dataWisatas.get(holder.getAdapterPosition()).getImgUrl());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataWisatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vnama, vlokasi, vdeskripsi, vDetail;
        ImageView vImgWisata;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            vnama = itemView.findViewById(R.id.txtNama);
            vlokasi = itemView.findViewById(R.id.txtLokasi);
            vImgWisata = itemView.findViewById(R.id.imgWisata);
            vDetail = itemView.findViewById(R.id.txtDetail);
        }
    }

    public void getImage(String data, final ImageView vImgWisata){
        reference.child(data).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(context).load(uri).into(vImgWisata);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
