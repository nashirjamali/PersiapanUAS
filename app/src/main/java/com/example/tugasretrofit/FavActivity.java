package com.example.tugasretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    private List<MahasiswaModel> mahasiswaModels;
    private MahasiswaFavAdapter mahasiswaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        initView();
        loadData();
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
                    Toast.makeText(FavActivity.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MahasiswaModel>> call, Throwable t) {
                Log.d("resFail", t.toString());
                Toast.makeText(FavActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecyclerView(List<MahasiswaModel> mahasiswaModels){
        mahasiswaAdapter = new MahasiswaFavAdapter(FavActivity.this, mahasiswaModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(FavActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mahasiswaAdapter);
        mahasiswaAdapter.notifyDataSetChanged();

    }

    private void initView(){
        recyclerView = findViewById(R.id.rv);
    }
}
