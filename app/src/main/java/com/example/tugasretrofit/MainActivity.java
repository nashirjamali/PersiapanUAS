package com.example.tugasretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    private List<MahasiswaModel> mahasiswaModels;
    private MahasiswaAdapter mahasiswaAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData(){
        Call<List<MahasiswaModel>> call = initRetrofit.getInstance().getMahasiswa();
        call.enqueue(new Callback<List<MahasiswaModel>>() {
            @Override
            public void onResponse(Call<List<MahasiswaModel>> call, Response<List<MahasiswaModel>> response) {
                if (response.isSuccessful()){
                    mahasiswaModels = (List<MahasiswaModel>) response.body();
                    setRecyclerView(mahasiswaModels);
                }else {
                    Log.d("resErr", response.errorBody().toString());
                    Toast.makeText(MainActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MahasiswaModel>> call, Throwable t) {
                Log.d("resFail", t.toString());
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(List<MahasiswaModel> mahasiswaModels){
        mahasiswaAdapter = new MahasiswaAdapter(MainActivity.this, mahasiswaModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mahasiswaAdapter);
        mahasiswaAdapter.notifyDataSetChanged();

    }

    private void initView(){
        recyclerView = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);
    }
}
