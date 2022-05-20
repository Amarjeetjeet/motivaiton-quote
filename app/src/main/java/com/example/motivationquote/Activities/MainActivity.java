package com.example.motivationquote.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motivationquote.Fragment.Add_Quote;
import com.example.motivationquote.Fragment.Update_quote;
import com.example.motivationquote.R;
import com.example.motivationquote.adapters.MyAdapter;
import com.example.motivationquote.controller.ApiController;
import com.example.motivationquote.databinding.ActivityMainBinding;
import com.example.motivationquote.interfaces.Apisets;
import com.example.motivationquote.responce.GetQuoteResponce;
import com.example.motivationquote.responce.Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        inti();
        onclicklisner();
        processdata();
    }

    private void inti() {
    }

    private void setadapeter(List<Model> models) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        myAdapter = new MyAdapter(this, models);
        binding.incMain.rcv.setLayoutManager(layoutManager);
        binding.incMain.rcv.setAdapter(myAdapter);
    }


    private void onclicklisner() {
        binding.incMain.menuBar.navbarBtn.setOnClickListener(v -> openDrawer());
        binding.incNav.closeDrawer.setOnClickListener(v -> closeDrawer());
        binding.incMain.pullTorefresh.setOnRefreshListener(() -> {
            processdata();
        });
        binding.incMain.menuBar.btnAdd.setOnClickListener(v -> {
            openAddfragment();
        });
        binding.incNav.uploadQuote.setOnClickListener(v -> {
            openAddfragment();
        });
        binding.incNav.homeNavbar.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,MainActivity.class));
        });
        binding.incNav.photos.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,ImageActivity.class));
        });
    }

    private void openUpdatefragment() {
        FragmentManager frm = getSupportFragmentManager();
        FragmentTransaction frt = frm.beginTransaction();
        frt.add(R.id.drawerLayout,new Update_quote());
        frt.addToBackStack(null);
        frt.commit();
    }

    private void openAddfragment(){
            FragmentManager frm = getSupportFragmentManager();
            FragmentTransaction frt = frm.beginTransaction();
            frt.add(R.id.drawerLayout,new Add_Quote());
            frt.addToBackStack(null);
            frt.commit();
    }
    private void closeDrawer() {
        binding.drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START);
    }


    private void processdata() {
        if (!binding.incMain.pullTorefresh.isRefreshing()){
        binding.incMain.progressBar.setVisibility(View.GONE);
        }
        binding.incMain.pullTorefresh.setRefreshing(true);
      //  binding.incMain.progressBar.setVisibility(View.VISIBLE);
        Apisets apisets = ApiController.getClient().create(Apisets.class);
        Call<GetQuoteResponce> call = apisets.getQuote();
        call.enqueue(new Callback<GetQuoteResponce>() {
            @Override
            public void onResponse(Call<GetQuoteResponce> call, Response<GetQuoteResponce> response) {
                binding.incMain.progressBar.setVisibility(View.GONE);
                binding.incMain.pullTorefresh.setRefreshing(false);
              if (response != null){
                  assert response.body() != null;
                  if (response.body().getStatus().equals("1")){
                      setadapeter(response.body().getData());
                  }
                  else{
                      Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                  }
              }
            }

            @Override
            public void onFailure(Call<GetQuoteResponce> call, Throwable t) {
                binding.incMain.progressBar.setVisibility(View.GONE);
                binding.incMain.pullTorefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}