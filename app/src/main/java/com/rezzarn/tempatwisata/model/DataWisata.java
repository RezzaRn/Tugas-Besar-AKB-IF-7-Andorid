package com.rezzarn.tempatwisata.model;

// Nim                  : 10119291
// Nama                 : Rezza Ramdhani Nashrullah
// Kelas                : IF7
// Tanggal Pengerjaan   : (4-6 agustus 2022)

public class DataWisata {
    String Nama, Lokasi, id, Deskripsi, MapsUrl, ImgUrl;

    public DataWisata(){}

    public DataWisata(String nama, String lokasi) {
        this.Nama = nama;
        this.Lokasi = lokasi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        this.Nama = nama;
    }

    public String getLokasi() {
        return Lokasi;
    }

    public void setLokasi(String lokasi) {
        this.Lokasi = lokasi;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getMapsUrl() {
        return MapsUrl;
    }

    public void setMapsUrl(String mapsUrl) {
        MapsUrl = mapsUrl;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }
}
