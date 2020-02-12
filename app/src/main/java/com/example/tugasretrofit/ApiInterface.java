package com.example.tugasretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    @GET("index.php?action=getmahasiswa")
    Call<List<MahasiswaModel>> getMahasiswa();
}
