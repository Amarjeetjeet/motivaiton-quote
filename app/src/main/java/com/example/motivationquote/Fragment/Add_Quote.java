package com.example.motivationquote.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.example.motivationquote.R;
import com.example.motivationquote.controller.ApiController;
import com.example.motivationquote.interfaces.Apisets;
import com.example.motivationquote.responce.AddQuoteResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Add_Quote extends Fragment {

    Button submit;
    EditText quote, quoteName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__quote, container, false);
        submit = view.findViewById(R.id.submit);
        quote = view.findViewById(R.id.quote);

        quoteName = view.findViewById(R.id.quoteName);
        setup();
        onclick();
        return view;

    }

    private void onclick() {
        submit.setOnClickListener(v -> validation());
    }

    private void validation() {
        if (quote.getText().toString().trim().isEmpty()){
           quote.setError("Write something");
        }
        else if (quoteName.getText().toString().trim().isEmpty()){
            quoteName.setError("write name");
        }
        else{
            process();
        }
    }

    private void setup() {
        quote.setText("");
        quoteName.setText("");
    }


    private void process() {
        Toast.makeText(getActivity(), "Please Wait", Toast.LENGTH_SHORT).show();

        Apisets api = ApiController.getClient().create(Apisets.class);

        Call<AddQuoteResponce> call = api.adddata(quote.getText().toString(),
                quoteName.getText().toString());

        call.enqueue(new Callback<AddQuoteResponce>(){
            @Override
            public void onResponse(Call<AddQuoteResponce> call, Response<AddQuoteResponce> response) {
                Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AddQuoteResponce> call, Throwable t){
                Toast.makeText(getActivity(), "UnSuccessful", Toast.LENGTH_SHORT).show();
            }
        });

    }


}