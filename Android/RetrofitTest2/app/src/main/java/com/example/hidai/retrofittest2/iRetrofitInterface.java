package com.example.hidai.retrofittest2;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Hidai on 26/03/2017.
 * No exemplo do professor seria a connection
 * Essa classe se conecta com a url que passa as rotas
 *
 */

public interface iRetrofitInterface {

    //https://api.myjson.com/bins/czrrf

    @GET("/users/{usuario}")
    Call<Usuario> getUsuario(@Path("usuario") String usuario);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/") //localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
