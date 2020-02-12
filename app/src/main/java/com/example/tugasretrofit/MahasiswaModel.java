package com.example.tugasretrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MahasiswaModel {

    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("tanggal_lahir")
    @Expose
    private String tanggalLahir;
    @SerializedName("umur")
    @Expose
    private String umur;

    public MahasiswaModel(String nim, String nama, String tanggalLahir, String umur) {
        this.nim = nim;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.umur = umur;
    }

    public MahasiswaModel() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

}
