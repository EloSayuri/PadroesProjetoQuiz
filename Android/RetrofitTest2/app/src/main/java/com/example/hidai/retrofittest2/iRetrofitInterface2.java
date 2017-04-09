package com.example.hidai.retrofittest2;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hidai on 26/03/2017.
 */

public interface iRetrofitInterface2 {
    //https://api.myjson.com/bins/6l5aj

    @GET("/{parame}/{parame2}")
    Call<Login> getLogin(@Path("parame") String parame, @Path("parame2")String parame2);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.myjson.com/") //localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
