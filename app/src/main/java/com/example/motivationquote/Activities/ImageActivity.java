package com.example.motivationquote.Activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.example.motivationquote.R;
import com.example.motivationquote.adapters.ImageAdapter;
import com.example.motivationquote.controller.ApiController;
import com.example.motivationquote.interfaces.Apisets;
import com.example.motivationquote.responce.GetImageResponce;
import com.example.motivationquote.responce.ImageModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageActivity extends AppCompatActivity {
        ImageAdapter imageAdapter;
    RecyclerView rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        inti();
        processdata();
    }

    private void inti() {
        rcv = findViewById(R.id.rcv);
    }

    private void setadapeter(List<ImageModel> models) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(ImageActivity.this);
        imageAdapter = new ImageAdapter(models);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(imageAdapter);
    }

    private void processdata() {
        Apisets apisets = ApiController.getClient().create(Apisets.class);
        Call<GetImageResponce> call = apisets.getQuoteImage();
        call.enqueue(new Callback<GetImageResponce>() {
            @Override
            public void onResponse(Call<GetImageResponce> call, Response<GetImageResponce> response) {
                if (response != null){
                    assert response.body() != null;
                    if (response.body().getStatus().equals("1")){
                        setadapeter(response.body().getData());
                    }
                    else{
                        Toast.makeText(ImageActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetImageResponce> call, Throwable t) {

            }
        });

    }


}
