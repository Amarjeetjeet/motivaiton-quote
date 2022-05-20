package com.example.motivationquote.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.motivationquote.R;
import com.example.motivationquote.interfaces.Apisets;
import com.example.motivationquote.responce.UpdateQuoteResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Update_quote extends Fragment {
    public static final String BASE_URL= "https://fitmuscle12.000webhostapp.com/Motivation_Quote/";
    Button btnUpdate;
    EditText quoteData,quoteId,quoteName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_update_quote, container, false);
//        btnUpdate =view.findViewById(R.id.btnUpdate);
//        quoteData  = view.findViewById(R.id.quoteData);
//        quoteId=  view.findViewById(R.id.quoteId);
//        quoteName = view.findViewById(R.id.quoteName);

        btnUpdate.setOnClickListener(v -> {
            updatequote();
        });

        return view;
    }

    private void updatequote() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apisets api = retrofit.create(Apisets.class);

        Call<UpdateQuoteResponce> call = api.updateQuote(quoteData.getText().toString(),
                quoteName.getText().toString(),
                quoteId.getText().toString());

        call.enqueue(new Callback<UpdateQuoteResponce>() {
            @Override
            public void onResponse(Call<UpdateQuoteResponce> call, Response<UpdateQuoteResponce> response) {
                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UpdateQuoteResponce> call, Throwable t) {
                Toast.makeText(getActivity(), "UnSuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}