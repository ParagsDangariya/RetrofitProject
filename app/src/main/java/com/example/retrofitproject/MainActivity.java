package com.example.retrofitproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Recycleadapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Getdataservice service = RetrofitInstance.getRetrofitInstance().create(Getdataservice.class);

        Call<List<Pokemon>> call = service.getPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {

                System.out.println(response.body());
                generateData(response.body());

            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Something went wrong!!!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void generateData(List<Pokemon> poklist){
        ArrayList<Pokemon> pokes = (ArrayList<Pokemon>) poklist;

        adapt = new Recycleadapter(pokes,getApplicationContext());

        @SuppressLint("WrongConstant") LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);

        RecyclerView recyclerView = findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapt);

    }
}
