package com.example.hidai.retrofittest2;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hidai on 03/05/2017.
 */

public class ExamList {


    //Connection connection = new Connection();

    public void getExams(){
        Log.i("OK: ", "metodo getexams");
        try {iRetrofitInterface3 githubUser = iRetrofitInterface3.retrofit.create(iRetrofitInterface3.class);
            final Call call = githubUser.getExam(new ExamTest("Nicholas","Database Test"));
            Log.i("OK: ", "chamada de rota ok");
            call.enqueue(new Callback<List<ExamTest>>() {
                @Override
                public void onResponse(Call<List<ExamTest>> call, Response<List<ExamTest>> response) {
                    int code = response.code();
                    System.out.println(code);
                    if (code == 200) {
                        Log.i("OK", "Teste com o exame ok");

                    } else {
                        Log.i("ERRO", "Teste com o exame sem sucesso");
                        //Log.i("ERRO DOIS: ",usuario.toString());
                    }
                }

                @Override
                public void onFailure(Call<List<ExamTest>> call, Throwable t) {
                    Log.i("ERRO dois: ",t.toString());
                }
            });
            //musics = connection.sendGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
