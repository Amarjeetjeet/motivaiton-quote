package com.example.motivationquote.adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.motivationquote.R;
import com.example.motivationquote.databinding.ImageRcvLayoutBinding;
import com.example.motivationquote.responce.ImageModel;
import com.example.motivationquote.responce.Model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.imageholder>  {

  List<ImageModel> localdata;


    public ImageAdapter(List<ImageModel> localdata) {
        this.localdata = localdata;
    }

    @NonNull
    @Override
    public ImageAdapter.imageholder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_rcv_layout,parent,false);
        return new ImageAdapter.imageholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageAdapter.imageholder holder, int position) {
        holder.imageRcvLayoutBinding.imageId.setText(localdata.get(position).getId());
        Glide.with(holder.imageRcvLayoutBinding.imageId.getContext())
                .load(localdata.get(position)
                        .getImage())
                .into(holder.imageRcvLayoutBinding.quoteImage);
    }

    @Override
    public int getItemCount() {
        return localdata.size();
    }

    public class imageholder extends RecyclerView.ViewHolder {
        ImageRcvLayoutBinding imageRcvLayoutBinding;
        public imageholder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageRcvLayoutBinding = ImageRcvLayoutBinding.bind(itemView);
        }
    }
}
