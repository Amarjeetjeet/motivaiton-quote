package com.example.motivationquote.interfaces;

import com.example.motivationquote.responce.AddQuoteResponce;
import com.example.motivationquote.responce.DeleteQuoteResponce;
import com.example.motivationquote.responce.GetImageResponce;
import com.example.motivationquote.responce.GetQuoteResponce;
import com.example.motivationquote.responce.ImageModel;
import com.example.motivationquote.responce.UpdateQuoteResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apisets {


    @POST("getQuote.php")
     Call<GetQuoteResponce> getQuote();


    @FormUrlEncoded
    @POST("deleteQuote.php")
    Call<DeleteQuoteResponce> deleteQuote(@Field("id") String id);

    @FormUrlEncoded
    @POST("updateQuote.php")
    Call<UpdateQuoteResponce> updateQuote(@Field("quote_data") String quote_data,
                                          @Field("quote_name") String quote_name,
                                          @Field("id")String id);

    @FormUrlEncoded
    @POST("addQuote.php")
    Call<AddQuoteResponce> adddata(@Field("quote") String quote,
                        @Field("quotename") String quotename);

    @POST("getImage.php")
    Call<GetImageResponce> getQuoteImage();
}
