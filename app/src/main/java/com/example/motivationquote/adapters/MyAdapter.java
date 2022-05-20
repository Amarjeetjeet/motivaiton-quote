package com.example.motivationquote.adapters;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.motivationquote.R;
import com.example.motivationquote.controller.ApiController;
import com.example.motivationquote.databinding.RcvlayoutBinding;
import com.example.motivationquote.interfaces.Apisets;
import com.example.motivationquote.interfaces.UserCardClick;
import com.example.motivationquote.responce.DeleteQuoteResponce;
import com.example.motivationquote.responce.Model;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static androidx.core.content.ContextCompat.getSystemService;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.holder> {
    Context context;
    List<Model> data;
    Apisets apisets;
    public MyAdapter(Context context, List<Model> data) {
        this.context = context;
        this.data = data;
        Retrofit retrofit = ApiController.getClient();
        apisets = retrofit.create(Apisets.class);
    }

    public void setData(List<Model> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcvlayout,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.holder holder, int position) {
        holder.rcvlayoutBinding.quoteId.setText(data.get(position).getId());
        holder.rcvlayoutBinding.quoteData.setText(data.get(position).getQuote_data());
        holder.rcvlayoutBinding.quoteAuth.setText(data.get(position).getQuote_name());
        holder.rcvlayoutBinding.quoteTime.setText(data.get(position).getTime());
        holder.rcvlayoutBinding.btnDelete.setVisibility(View.GONE);
        holder.rcvlayoutBinding.btnDelete.setOnClickListener(v ->
        {

            deletequote(data.get(position).getId(),position);
        });

        holder.rcvlayoutBinding.shareBtn.setOnClickListener(v ->
        {

        });

    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }
        return 0;
    }

    public static class holder extends RecyclerView.ViewHolder {

        RcvlayoutBinding rcvlayoutBinding;

        public holder(@NonNull  View itemView) {
            super(itemView);
            rcvlayoutBinding = RcvlayoutBinding.bind(itemView);
        }
    }
    private void deletequote(String id,int pose) {
        apisets.deleteQuote(id).enqueue(new Callback<DeleteQuoteResponce>() {
            @Override
            public void onResponse(Call<DeleteQuoteResponce> call, Response<DeleteQuoteResponce> response) {

                if (response != null) {
                    assert response.body() != null;
                    Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if (response.body().getStatus().equals("1")) {
                        data.remove(pose);
                        notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<DeleteQuoteResponce> call, Throwable t) {
                Toast.makeText(context, "Unsucessful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}







