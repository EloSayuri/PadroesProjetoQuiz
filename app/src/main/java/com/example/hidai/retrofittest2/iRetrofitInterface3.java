package com.example.hidai.retrofittest2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Hidai on 29/03/2017.
 */

public interface iRetrofitInterface3 {
    /*@GET("/login/{exam}")
    Call<List<Exam>> getUsuario(@Path("exam") String exam);*/

    //@FormUrlEncoded
    //@Headers("Accept: application/json")
    @POST("/login2")
    Call<Login> getL(@Body Login login);

    @GET("/getExams")
    Call<List<ExamTest>> getExams();

    @POST("/getExam")
    Call<List<ExamTest>> getExam(@Body ExamTest exam);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.16.199.177:8080/") //localhost
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}
